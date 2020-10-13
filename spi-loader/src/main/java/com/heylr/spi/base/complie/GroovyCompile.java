package com.heylr.spi.base.complie;

import com.heylr.spi.base.exception.SpiProxyCompileException;
import groovy.lang.GroovyClassLoader;

public class GroovyCompile {

    @SuppressWarnings("unchecked")
    public static <T> T compile(String code, Class<T> interfaceType, ClassLoader classLoader) throws SpiProxyCompileException {
        GroovyClassLoader loader = new GroovyClassLoader(classLoader);

        Class clz = loader.parseClass(code);

        if (!interfaceType.isAssignableFrom(clz)) {
            throw new IllegalStateException("illegal proxy type!");
        }

        try {
            return (T) clz.newInstance();
        } catch (Exception e) {
            throw new SpiProxyCompileException("init spiProxy error! msg:" + e.getMessage());
        }

    }
}
