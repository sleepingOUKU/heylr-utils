package com.heylr.base;


/**
 * 分类打印log接口类
 * @author heylr
 * @date 2020-8-10 09:58:24
 */
public interface SplitLogInterface {

    /**
     * 将o输出到console窗口
     * @param o
     */
    void outConsole(Object o);

    /**
     * 将o输出到文件
     * @param o
     */
    void outFile(Object o);

    /**
     * 将o输出到文件
     * @param fileName
     * @param o
     */
    void outFile(String fileName,Object o);

    /**
     * 将o输出到文件以及console窗口
     * @param fileName
     * @param o
     */
    void outBoth(String fileName,Object o);

    /**
     * 直接输出o
     * @param o
     */
    void debug(Object o);

    /**
     * 将o输出到指定文件夹
     * @param fileName
     * @param o
     */
    void debug(String fileName,Object o);

    /**
     * 直接输出o
     * @param o
     */
    void info(Object o);

    /**
     * 将o输出到指定文件夹
     * @param fileName
     * @param o
     */
    void info(String fileName,Object o);

    /**
     * 直接输出o
     * @param o
     */
    void warning(Object o);

    /**
     * 将o输出到指定文件夹
     * @param fileName
     * @param o
     */
    void warning(String fileName,Object o);

    /**
     * 直接输出o
     * @param o
     */
    void error(Object o);

    /**
     * 将o输出到指定文件夹
     * @param fileName
     * @param o
     */
    void error(String fileName,Object o);
}
