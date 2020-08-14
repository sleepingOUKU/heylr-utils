package com.heylr.base;


import com.heylr.entity.Tuples;

import java.util.List;

/**
 * 多线程查询总接口类
 * @author heylr
 * @date 2020-8-7 17:12:24
 */
public interface MultiQueryInterface<QueryEntity,ResultEntity> {

    /**
     * 将key值进行分割，从而实现分割查询
     * @param startKey
     * @param endKey
     * @return
     */
    List<Tuples<QueryEntity>> splitKeysList(QueryEntity startKey,QueryEntity endKey);

    /**
     * 合并查询结果
     * @param mergeList
     * @return
     */
    List<ResultEntity> mergeResult(List<List<ResultEntity>> mergeList);

    /**
     * 将已经分割的查询条件List进行查询
     * @param queryList
     * @return
     */
    List<List<ResultEntity>> splitQuery(List<Tuples<QueryEntity>> queryList);



    /**
     * tuples 为未分割的查询条件，此方法会先尝试调用
     * {List<Tuples<QueryEntity>> splitKeysList(QueryEntity startKey,QueryEntity endKey)}
     * 方法，获得分条件查询列表之后调用
     * {List<List<ResultEntity>> splitQuery(List<Tuples<QueryEntity>> queryList)}
     * 方法。
     * @param tuples
     * @return
     */
    List<ResultEntity> splitQuery(Tuples<QueryEntity> tuples);


    /**
     * 具体查询方法
     * @param tuples
     * @return
     */
    List<ResultEntity> query(Tuples<QueryEntity> tuples);
}
