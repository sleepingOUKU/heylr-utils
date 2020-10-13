package com.heylr.spi.base.exception;

public class SpiProxyCompileException extends Exception {

    public SpiProxyCompileException(String message){
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace(){
        return this;
    }

}
