package com.heylr.basetest;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {


        Map<String,Integer> testMap = new HashMap<>();

//        testMap.put("a", 1);

        testMap.merge("a", 1, (integer, integer2) -> {
            return integer + integer2;


//            return integer == null? integer2:integer + integer2;
        });

        System.out.println(testMap);
    }
}
