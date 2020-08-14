package com.heylr.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class ConfigUtil {

    private static final String CLASS_PATH_FILE_PATH = "classpath:";


    public static Properties loadProperties(String fileName) {
        if (StringUtil.isNotBlank(fileName)) {
            if (absolutePathStart(fileName)) {
                return loadPropertiesFromAbsoluteFile(fileName);
            }else if(fileName.startsWith(CLASS_PATH_FILE_PATH)){
                return loadPropertiesFromClasspathFile(fileName);
            }else {
                return loadPropertiesFromRelativeFile(fileName);
            }
        }else {
            return null;
        }
    }

    private static boolean absolutePathStart(String path) {
        File[] files = File.listRoots();

        for (File file : files) {
            if (path.startsWith(file.getPath())) {
                return true;
            }
        }

        return false;
    }


    private static Properties loadPropertiesFromAbsoluteFile(String fileName) {
        Properties properties = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return null;
            }

            try (BufferedReader bufferedReader
                         = new BufferedReader(new InputStreamReader(new FileInputStream(file), getCharset()))) {
                properties = new Properties();
                properties.load(bufferedReader);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    private static Properties loadPropertiesFromClasspathFile(String fileName) {
        fileName = fileName.substring(CLASS_PATH_FILE_PATH.length()).trim();

        List<URL> list = new ArrayList<>();

        try {
            Enumeration<URL> urls = getClassLoader().getResources(fileName);

            while (urls.hasMoreElements()) {
                list.add(urls.nextElement());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (list.isEmpty()) {
            return null;
        }

        Properties properties = new Properties();

        for (URL url : list) {
            try (BufferedReader bufferedReader =
                         new BufferedReader(new InputStreamReader(url.openStream(), getCharset()))) {
                Properties p = new Properties();
                p.load(bufferedReader);
                properties.putAll(p);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return properties;
    }

    private static Properties loadPropertiesFromRelativeFile(String fileName) {
        String userDir = System.getProperty("user.dir");
        String realFilePath = addSeparator(userDir) + fileName;
        return loadPropertiesFromAbsoluteFile(realFilePath);
    }

    private static Charset getCharset() {
        return Charset.forName(System.getProperty("multiquery.charset", StandardCharsets.UTF_8.name()));
    }

    private static ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = ConfigUtil.class.getClassLoader();
        }
        return classLoader;
    }

    public static String addSeparator(String dir) {
        if (!dir.endsWith(File.separator)) {
            dir += File.separator;
        }
        return dir;
    }
}
