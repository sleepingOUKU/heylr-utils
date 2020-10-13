package com.heylr.spi.base.selector;

import com.heylr.spi.base.exception.NoSpiMatchException;
import com.heylr.spi.base.selector.api.ISelector;
import com.heylr.spi.base.selector.api.SpiImplWrapper;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class DefaultSelector implements ISelector<String> {


    @Override
    public <K> K selector(Map<String, SpiImplWrapper<K>> map, String name) throws NoSpiMatchException {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("spiName should not be empty!");
        }

        if (map == null || map.size() == 0) {
            throw new IllegalArgumentException("no impl spi!");
        }


        if (!map.containsKey(name)) {
            throw new NoSpiMatchException("no spiImpl match the name you choose! your choose is: " + name);
        }

        return map.get(name).getSpiImpl();
    }

}
