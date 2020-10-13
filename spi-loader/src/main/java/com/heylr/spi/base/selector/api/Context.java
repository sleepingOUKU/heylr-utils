package com.heylr.spi.base.selector.api;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Context {

    private Map<String,String> params = new HashMap<>();

    public String getParam(String key){
        return params.get(key);
    }

    public void setParam(String key,String value){
        params.put(key, value);
    }

    public void setParam(String key){
        params.put(key, null);
    }

    public void clear(){
        params.clear();
    }

}
