package com.heylr.config;


import com.heylr.util.ConfigUtil;
import com.heylr.util.StringUtil;

import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArraySet;

public class ConfigLoader {

    public static final String DEFAULT_CONFIG_ENV_KEY = "MULTI_QUERY__CONFIG_FILE";

    private static final String DEFAULT_CONFIG_FILE = "classpath:multi-thread-query.properties";

    private static final String USER_HOME = "user.home";

    private static final String DIR_NAME = "logs" + File.separator + "multiQuery";

    private static Properties properties = new Properties();

    static {
        try {
            load();
        } catch (Throwable t) {
            System.out.println("[ConfigLoader] Failed to initialize configuration items");
            t.printStackTrace();
        }
    }

    private static void load() {
        String fileName = System.getProperty(DEFAULT_CONFIG_ENV_KEY);

        if (StringUtil.isBlank(fileName)) {
            fileName = System.getenv(DEFAULT_CONFIG_ENV_KEY);
            if (StringUtil.isBlank(fileName)) {
                fileName = DEFAULT_CONFIG_FILE;
            }

            Properties p = ConfigUtil.loadProperties(fileName);

            if (p == null) {
                String path = ConfigUtil.addSeparator(System.getProperty(USER_HOME)) + DIR_NAME + File.separator;
                File file = new File(fileName);
                if (file.exists()) {
                    p = ConfigUtil.loadProperties(fileName);
                }
            }


            if (p != null && !p.isEmpty()) {
                System.out.println("[ConfigLoader] Loading config from " + fileName);
                properties.putAll(p);
            }

            for (Map.Entry<Object, Object> entry : new CopyOnWriteArraySet<>(System.getProperties().entrySet())) {
                String configKey = entry.getKey().toString();
                String newConfigValue = entry.getValue().toString();
                String oldConfigValue = properties.getProperty(configKey);
                properties.put(configKey, newConfigValue);
                if (oldConfigValue != null) {
                    System.out.println("[ConfigLoader] JVM parameter overrides {" + configKey + "}: {" + oldConfigValue + "} -> {" + newConfigValue + "}");
                }
            }
        }
    }

    public static Properties getProperties() {
        return properties;
    }

}
