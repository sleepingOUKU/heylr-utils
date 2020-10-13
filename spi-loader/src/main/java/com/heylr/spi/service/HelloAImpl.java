package com.heylr.spi.service;

import com.heylr.spi.base.api.SpiAdaptive;

public class HelloAImpl implements HelloInterface {

    @SpiAdaptive(selector = HelloSelector.class)
    public String sayHello(String str) {
        System.out.println("A:" + str);
        return "AAA";
    }
}
