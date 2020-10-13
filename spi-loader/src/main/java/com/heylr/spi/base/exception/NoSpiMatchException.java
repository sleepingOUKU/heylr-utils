package com.heylr.spi.base.exception;

public class NoSpiMatchException extends Exception {

    public NoSpiMatchException() {
    }

    public NoSpiMatchException(String message) {
        super(message);
    }

    public NoSpiMatchException(Throwable cause) {
        super(cause);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
