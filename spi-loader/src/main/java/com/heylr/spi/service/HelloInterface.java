package com.heylr.spi.service;


import com.heylr.spi.base.api.Spi;

@Spi
public interface HelloInterface {
    String sayHello(String str);
}
