package com.heylr.entity;


import com.heylr.common.BaseLogDictionary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

/**
 * 写文件流实体类,定义流的对象。文件名等
 *
 * @author heylr
 * @date 2020-8-10 10:56:52
 */
public class OutWrite {

    private String filePath;

    private String fileName;

    private File file;

    private BufferedWriter bw;

    private Date callDate;

    public Date getCallDate() {
        return callDate;
    }

    public void updateCallDate() {
        this.callDate = new Date();
    }

    /**
     * 设置fileName
     *
     * @param fileName
     */
    public void initFile(String filePath, String fileName) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    /**
     * 将object 写入文件
     *
     * @param o
     * @return 写入成功则返回true，写入失败则返回false。
     */
    public synchronized boolean toFile(Object o) {
        try {
            if (bw == null) {
                initBw();
            }
            bw.write(o.toString());
            bw.flush();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void initBw() throws Exception {
        if (file == null) {
            initFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, BaseLogDictionary.getCharSet());
        bw = new BufferedWriter(osw);

    }

    private void initFile() throws Exception {
        //判断是否存在文件夹,不存在则创建文件夹
        if (filePath != null && filePath.length() != 0) {
            File path = new File(filePath);
            if (!path.exists()) {
                if (!path.mkdir()) {
                    path.mkdirs();
                }
            }
        }

        //判断文件是否存在
        if (fileName == null || fileName.length() == 0) {
            throw new Exception("fileName is null");
        }
        file = new File(filePath + "/" + fileName);
        if(!file.exists()){
            file.createNewFile();
        }
    }

    /**
     * 调用此方法关闭对应输出流
     */
    public void destroy() {
        try {
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return "OutWrite{" +
                "filePath='" + filePath + '\'' +
                ", fileName='" + fileName + "'}";
    }
}
