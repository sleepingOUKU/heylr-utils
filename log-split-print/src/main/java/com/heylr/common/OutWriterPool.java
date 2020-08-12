package com.heylr.common;


import com.heylr.entity.OutWrite;
import com.sun.istack.internal.Nullable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 定义写文件输出流池,用于存放输出流
 *
 * @author zhangyunlin
 * @date 2020-8-10 10:54:52
 */
public class OutWriterPool {

    private static Map<String, OutWrite> writePoll = new HashMap<String, OutWrite>();

    /**
     * 通过key获取SplitLog
     *
     * @param key
     * @return
     */
    @Nullable
    public static OutWrite getOutWrite(String key) {
        if (writePoll.containsKey(key)) {
            writePoll.get(key).updateCallDate();
            return writePoll.get(key);
        } else {
            return null;
        }
    }

    /**
     * 自动清除过期的OutWrite流
     *
     * @param date
     */
    public static void autoRemoveOutWrite(Date date) {
        synchronized (writePoll) {
            for (String key : writePoll.keySet()) {
                if (writePoll.get(key).getCallDate().before(date)) {
                    writePoll.remove(key);
                }
            }
        }
    }

}
