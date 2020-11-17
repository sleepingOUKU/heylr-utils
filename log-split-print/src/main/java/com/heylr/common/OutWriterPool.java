package com.heylr.common;

import com.heylr.entity.OutWrite;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定义写文件输出流池,用于存放输出流
 *
 * @author zhangyunlin
 * @date 2020-8-10 10:54:52
 */
public class OutWriterPool {

    private static Map<String, OutWrite> writePool = new ConcurrentHashMap<>();

    /**
     * 通过key获取OutWrite
     *
     * @param key
     * @return
     */
    public static OutWrite getOutWrite(String key) {
        if (writePool.containsKey(key)) {
            writePool.get(key).updateCallDate();
            return writePool.get(key);
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
        synchronized (writePool) {

            Set<String> keySet = writePool.keySet();

            for (String key : keySet) {
                if (writePool.get(key).getCallDate().before(date)) {
                    System.out.println("remove useless OutWrite [" + writePool.get(key) + "] from WritePool");
                    writePool.get(key).destroy();
                    writePool.remove(key);
                }
            }
        }
    }

    /**
     * 向writePool中添加OutWrite对象
     *
     * @param key
     * @param outWrite
     * @throws Exception
     */
    public static void putOutWrite(String key, OutWrite outWrite) throws Exception {
        synchronized (writePool) {
            if (writePool.containsKey(key)) {
                throw new Exception("WritePool has contains the same key [" + key + "],please remove this key first");
            }
            writePool.put(key, outWrite);
        }
    }


    /**
     * 从writePool中删除outWrite
     *
     * @param key
     */
    public static void removeOutWrite(String key) {
        synchronized (writePool) {
            if (writePool.containsKey(key)) {
                writePool.get(key).destroy();
                writePool.remove(key);
            }
        }
    }

    /**
     * 计算你文件Path的hashCode作为key值
     *
     * @param filePath
     * @return
     */
    public static String calculateKey(String filePath) {
        return Integer.toUnsignedString(filePath.hashCode());
    }

}
