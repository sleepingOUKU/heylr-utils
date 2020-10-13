package com.heylr.spi.service;

import com.heylr.spi.base.exception.NoSpiMatchException;
import com.heylr.spi.base.selector.api.ISelector;
import com.heylr.spi.base.selector.api.SpiImplWrapper;

import java.util.Map;

public class HelloSelector implements ISelector<String> {
    @Override
    public <K> K selector(Map<String, SpiImplWrapper<K>> map, String conf) throws NoSpiMatchException {

        if(conf == null || conf.length() ==0 ){
            throw new IllegalArgumentException("userDo or userDO#market should not be null!");
        }

        if(map.containsKey(conf)){
            return map.get(conf).getSpiImpl();
        }
        throw new NoSpiMatchException("no spiImp matched marked: " + conf);
    }
}
