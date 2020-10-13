package com.heylr.spi;

import com.heylr.spi.base.SpiLoader;
import com.heylr.spi.base.exception.SpiProxyCompileException;
import com.heylr.spi.service.HelloInterface;

public class StartApplication {
    public static void main(String[] args) throws SpiProxyCompileException {
        HelloInterface helloInterface = SpiLoader.load(HelloInterface.class).getAdaptive();

        String a = helloInterface.sayHello("HelloAImpl");
        String b = helloInterface.sayHello("HelloBImpl");

        System.out.println("a:" + a + "  b:" + b);
    }
}
