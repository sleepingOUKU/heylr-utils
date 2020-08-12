package com.heylr.common;

/**
 * 定义log的级别
 */
public class BaseLogDictionary {

    /**
     * 定义输出字符格式
     */
    private static String charSet = "UTF-8";

    public static void setCharSet(String charSet) {
        BaseLogDictionary.charSet = charSet;
    }

    public static String getCharSet() {
        return charSet;
    }

    /**
     * 定义输出方式
     */
    public static final Integer OUT_CONSOLE = 1;

    public static final Integer OUT_FILE = 2;

    public static final Integer OUT_BOTH = 3;

    /**
     * 定义输出级别
     */
    public static final Integer DEBUG_LEVEL = 0;

    public static final Integer INFO_LEVEL = 1;

    public static final Integer WARN_LEVEL = 2;

    public static final Integer ERROR_LEVEL = 3;

    /**
     * 定义输出文件夹
     */
    private static String logDir = "logs";

    public static void setLogDir(String logDir) {
        BaseLogDictionary.logDir = logDir;
    }

    public static String getLogDir() {
        return logDir;
    }


    /**
     * 定义输出文件名
     */
    private static String debugFile = "debug.log";

    public static void setDebugFile(String debugFile) {
        BaseLogDictionary.debugFile = debugFile;
    }

    public static String getDebugFile() {
        return debugFile;
    }

    private static String infoFile = "info.log";

    public static void setInfoFile(String infoFile) {
        BaseLogDictionary.infoFile = infoFile;
    }

    public static String getInfoFile() {
        return infoFile;
    }

    private static String warningFile = "warning.log";

    public static void setWarningFile(String warningFile) {
        BaseLogDictionary.warningFile = warningFile;
    }

    public static String getWarningFile() {
        return warningFile;
    }

    private static String errorFile = "error.log";

    public static void setErrorFile(String errorFile) {
        BaseLogDictionary.errorFile = errorFile;
    }

    public static String getErrorFile() {
        return errorFile;
    }
}
