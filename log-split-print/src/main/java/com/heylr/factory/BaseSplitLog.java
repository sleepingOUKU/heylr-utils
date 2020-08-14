package com.heylr.factory;

import com.heylr.common.OutWriterPool;
import com.heylr.entity.Content;
import com.heylr.entity.OutWrite;

public class BaseSplitLog {

    /**
     * 打印日志,尝试3次，如果全部失败则丢弃
     *
     * @param content
     */
    public static void printLog(Content content, Integer count) {
        String key = OutWriterPool.calculateKey(content.getFullPath());

        OutWrite outWrite = OutWriterPool.getOutWrite(key);

        //将日志输出到指定文件夹中
        if (outWrite == null) {
            outWrite = new OutWrite();
            outWrite.initFile(content.getFilePath(), content.getFileName());
            try {
                OutWriterPool.putOutWrite(key, outWrite);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                //重新尝试输出到file中。
                if (count > 0) {
                    count--;
                    printLog(content, count);
                }
            }
        }

        //写失败，出现异常，从池中移除
        if (!outWrite.toFile(content.getContent())) {
            System.out.println("write error when write to [" + content.getFileName() + "],try to remove key [" + key + "] from OutWriterPool");
            OutWriterPool.removeOutWrite(key);
        }
    }


    /**
     * @param content
     */
    public static void printLog(Content content) {
        printLog(content, 3);
    }

}
