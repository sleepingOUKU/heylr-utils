package com.heylr.log;
/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>The base class for logging.</p>
 *
 * <p>
 * The default log base directory is {@code ${user.home}/logs/csp/}. We can use the {@link #LOG_DIR}
 * property to override it. The default log file name dose not contain pid, but if multi-instances of the same service
 * are running in the same machine, we may want to distinguish the log file by process ID number.
 * In this case, {@link #LOG_NAME_USE_PID} property could be configured as "true" to turn on this switch.
 * </p>
 *
 * @author leyou
 */
public class LogBase {

    /**
     * Output biz log (e.g. RecordLog and CommandCenterLog) to file.
     */
    public static final String LOG_OUTPUT_TYPE_FILE = "file";
    /**
     * Output biz log (e.g. RecordLog and CommandCenterLog) to console.
     */
    public static final String LOG_OUTPUT_TYPE_CONSOLE = "console";
    public static final String LOG_CHARSET_UTF8 = "utf-8";

    private static final String DIR_NAME = "logs";

    /**
     * max size of single log file(MB)
     */
    private static final int SINGLE_LOG_SIZE = 1024 * 1024 * 200;

    private static final int MAX_LOG_COUNT = 100;

    private static String logOutputType;
    private static String logBaseDir;
    private static String logCharSet;

    static {
        try {
            initialize();
            loadProperties();
        } catch (Throwable t) {
            System.err.println("[LogBase] FATAL ERROR when initializing log class");
            t.printStackTrace();
        }
    }

    private static void initialize() {
        logOutputType = LOG_OUTPUT_TYPE_FILE;
        logBaseDir = DIR_NAME + File.separator;
        logCharSet = LOG_CHARSET_UTF8;
    }

    private static void loadProperties() {

        System.out.println("INFO: log output type is: " + logOutputType);

        System.out.println("INFO: log charset is: " + logCharSet);

        logBaseDir = addSeparator(logBaseDir);
        File dir = new File(logBaseDir);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.err.println("ERROR: create log base dir error: " + logBaseDir);
            }
        }
        System.out.println("INFO: log base dir is: " + logBaseDir);
    }

    /**
     * Get the log file base directory path, which is guaranteed ended with {@link File#separator}.
     *
     * @return log file base directory path
     */
    public static String getLogBaseDir() {
        return logBaseDir;
    }

    /**
     * Get the log file output type.
     *
     * @return log output type, "file" by default
     */
    public static String getLogOutputType() {
        return logOutputType;
    }

    /**
     * Get the log file charset.
     *
     * @return the log file charset, "utf-8" by default
     */
    public static String getLogCharset() {
        return logCharSet;
    }

    protected static void log(Logger logger, Handler handler, Level level, String detail, Object... params) {
        if (detail == null) {
            return;
        }
        LoggerUtils.disableOtherHandlers(logger, handler);
        if (params.length == 0) {
            logger.log(level, detail);
        } else {
            logger.log(level, detail, params);
        }
    }

    protected static void log(Logger logger, Handler handler, Level level, String detail, Throwable throwable) {
        if (detail == null) {
            return;
        }
        LoggerUtils.disableOtherHandlers(logger, handler);
        logger.log(level, detail, throwable);
    }


    protected static Handler makeLogger(String logName, Logger heliumRecordLog) {
        LogFormatter formatter = new LogFormatter();

        Handler handler = null;

        // Create handler according to logOutputType, set formatter to CspFormatter, set encoding to LOG_CHARSET
        switch (logOutputType) {
            case LOG_OUTPUT_TYPE_FILE:
                String fileName = LogBase.getLogBaseDir() + "%d";
                try {
                    handler = new DateFileLogHandler(addSeparator(fileName) + logName, SINGLE_LOG_SIZE, MAX_LOG_COUNT, true);
                    handler.setFormatter(formatter);
                    handler.setEncoding(logCharSet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case LOG_OUTPUT_TYPE_CONSOLE:
                try {
                    handler = new ConsoleHandler();
                    handler.setFormatter(formatter);
                    handler.setEncoding(logCharSet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

        if (handler != null) {
            LoggerUtils.disableOtherHandlers(heliumRecordLog, handler);
        }
        heliumRecordLog.setLevel(Level.ALL);
        return handler;
    }

    public static String addSeparator(String dir) {
        if (!dir.endsWith(File.separator)) {
            dir += File.separator;
        }
        return dir;
    }
}

