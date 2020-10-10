package com.heylr.factory;

import com.heylr.base.MultiQueryInterface;
import com.heylr.concurrent.NamedThreadFactory;
import com.heylr.config.BaseConfig;
import com.heylr.config.ConfigLoader;
import com.heylr.entity.Tuples;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 抽象基础分线程查询类
 *
 * @author heylr
 * @date 2020-8-12 14:38:40
 */
public abstract class AbstractBaseQuery<QueryEntity, ResultEntity> implements MultiQueryInterface<QueryEntity, ResultEntity> {

    private static ScheduledThreadPoolExecutor queryPool = new ScheduledThreadPoolExecutor(BaseConfig.getQueryThreadNum(), new NamedThreadFactory(
                "multi-query", true));


    /**
     * 默认方法，不进行分割，直接返回由startKey与endKey组成的Tuples
     * @param startKey
     * @param endKey
     * @return
     */
    @Override
    public List<Tuples<QueryEntity>> splitKeysList(QueryEntity startKey, QueryEntity endKey) {
        Tuples<QueryEntity> tuples = new Tuples<>(startKey,endKey);
        return new ArrayList<Tuples<QueryEntity>>(){{add(tuples);}};
    }

    @Override
    public List<List<ResultEntity>> splitQuery(List<Tuples<QueryEntity>> queryList) {

        List<List<ResultEntity>> result = new ArrayList<>();

        if (queryList.isEmpty()) {
            return result;
        }
        CountDownLatch threadCount = new CountDownLatch(queryList.size());

        //新建查询线程
        for (int i = 0; i < queryList.size(); i++) {
            int tempI = i;
            queryPool.execute(new Thread(() -> {
                try {
                    System.out.println("currentThread start:" + Thread.currentThread().getName());
                    List<ResultEntity> splitResult = query(queryList.get(tempI));
                    synchronized (result) {
                        result.add(splitResult);
                    }
                    System.out.println("currentThread end:" + Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    threadCount.countDown();
                }
            }));
        }

        try {
            threadCount.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * baseMergeResult 将所有查询出来的数据进行统计，只是按照顺序组合到同一list中，
     *
     * @param mergeList
     * @return
     */
    @Override
    public List<ResultEntity> mergeResult(List<List<ResultEntity>> mergeList) {
        List<ResultEntity> result = new ArrayList<>();

        for (List list : mergeList) {
            result.addAll(list);
        }

        return result;
    }

    @Override
    public List<ResultEntity> splitQuery(Tuples<QueryEntity> tuples) {
        List<Tuples<QueryEntity>> queryList = splitKeysList(tuples.getFirst(), tuples.getSecond());
        List<List<ResultEntity>> result = splitQuery(queryList);

        return mergeResult(result);
    }
}
