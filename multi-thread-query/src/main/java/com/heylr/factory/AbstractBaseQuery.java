package com.heylr.factory;

import com.heylr.base.MultiQueryInterface;
import com.heylr.entity.Tuples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 抽象基础分线程查询类
 * @author zhangyunlin
 */
public abstract class AbstractBaseQuery<QueryEntity,ResultEntity> implements MultiQueryInterface<QueryEntity,ResultEntity> {

    @Override
    public List<List<ResultEntity>> splitQuery(List<Tuples<QueryEntity>> queryList){

        List<List<ResultEntity>> result = new ArrayList<>();

        if(queryList.isEmpty()){
            return result;
        }
        CountDownLatch threadCount = new CountDownLatch(queryList.size());

        //新建查询线程
        for(int i = 0;i< queryList.size();i++){
            int tempI = i;
            new Thread(()->{
                try {
                    List<ResultEntity> splitResult = query(queryList.get(tempI));
                    synchronized (result){
                        result.add(splitResult);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    threadCount.countDown();
                }
            }).start();
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
     * @param mergeList
     * @return
     */
    @Override
    public List<ResultEntity> mergeResult(List<List<ResultEntity>> mergeList) {
        List<ResultEntity> result = new ArrayList<>();

        for(List list: mergeList){
            result.addAll(list);
        }

        return result;
    }

    @Override
    public List<ResultEntity> splitQuery(Tuples<QueryEntity> tuples){
        List<Tuples<QueryEntity>> queryList = splitKeysList(tuples.getFirst(), tuples.getSecond());
        List<List<ResultEntity>> result = splitQuery(queryList);

        return mergeResult(result);
    }
}
