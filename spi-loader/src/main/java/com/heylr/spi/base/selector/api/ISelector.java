package com.heylr.spi.base.selector.api;

import com.heylr.spi.base.exception.NoSpiMatchException;

import java.util.Map;

public interface ISelector<T> {
    <K> K selector(Map<String, SpiImplWrapper<K>> map, T conf) throws NoSpiMatchException;
}
