package com.heylr.config;

import com.heylr.util.AssertUtil;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基础配置类，从ConfigLoader中加载配置项
 *
 * @author heylr
 * @date 2020-8-14 13:58:29
 */
public final class BaseConfig {

    private static final Map<String, String> props = new ConcurrentHashMap<>();

    private static final String QUERY_THREAD_NUM = "queryThread.num";


    static {
        try {
            initialize();
            loadProps();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    private BaseConfig() {
    }

    /**
     * initialize the props
     */
    private static void initialize() {

    }

    private static void loadProps() {
        Properties properties = ConfigLoader.getProperties();
        for (Object key : properties.keySet()) {
            setConfig((String) key, (String) properties.get(key));
        }
    }

    public static String getConfig(String key) {
        AssertUtil.notNull(key, "key cannot be null");
        return props.get(key);
    }

    public static void setConfig(String key, String value) {
        AssertUtil.notNull(key, "key cannot be null");
        AssertUtil.notNull(value, "value cannot be null");
        props.put(key, value);
    }

    public static String removeConfig(String key) {
        AssertUtil.notNull(key, "key cannot be null");
        return props.remove(key);
    }

    public static int getQueryThreadNum() {
        return Integer.parseInt(props.get(QUERY_THREAD_NUM));
    }
}
