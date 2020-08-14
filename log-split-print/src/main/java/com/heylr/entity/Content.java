package com.heylr.entity;



public class Content {

    private String fileName;

    private String filePath;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFullPath(){
        return filePath + "/" + fileName;
    }
}
