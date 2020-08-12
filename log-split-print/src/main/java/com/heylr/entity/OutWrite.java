package com.heylr.entity;

import com.heylr.common.BaseLogDictionary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

/**
 * 写文件流实体类,定义流的对象。文件名等
 * @author heylr
 * @date 2020-8-10 10:56:52
 */
public class OutWrite {

    private String fileName;

    private File file;

    private BufferedWriter bw;

    private Date callDate;

    public Date getCallDate() {
        return callDate;
    }

    public void updateCallDate(){
        this.callDate = new Date();
    }

    /**
     * 设置fileName
     * @param fileName
     */
    public void initFileName(String fileName){
        this.fileName = fileName;
    }

    /**
     * 将object 写入文件
     * @param o
     * @return 写入成功则返回true，写入失败则返回false。
     */
    public boolean toFile(Object o){
        try {
            if(bw == null){
                initBw();
            }
            bw.newLine();
            bw.write(o.toString());
            bw.flush();

            return true;
        }catch (Exception e){
            try {
                bw.close();
            }catch (Exception e1){
                e1.printStackTrace();
            }
            return false;
        }
    }

    public void initBw() throws Exception {
        if(file == null){
            initFile(fileName);
        }
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, BaseLogDictionary.getCharSet());
        bw = new BufferedWriter(osw);

    }

    private void initFile(String fileName) throws Exception {
        if(fileName == null||fileName.length() == 0){
            throw new Exception("fileName is null");
        }
        file = new File(fileName);
    }

}
