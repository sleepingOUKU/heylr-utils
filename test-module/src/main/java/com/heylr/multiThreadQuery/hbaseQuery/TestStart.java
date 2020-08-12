package com.heylr.multiThreadQuery.hbaseQuery;


import com.heylr.entity.Tuples;
import com.heylr.multiThreadQuery.hbaseQuery.entity.User;
import com.heylr.multiThreadQuery.hbaseQuery.factory.HbaseQuery;

import java.util.List;

public class TestStart {
    public static void main(String[] args) {
        HbaseQuery hbaseQuery = new HbaseQuery();
        Tuples<String> queryTuple = new Tuples<String>("1000","4000");
        List<User> result = hbaseQuery.splitQuery(queryTuple);
        System.out.println(result.toString());
    }
}
