package com.heylr.entity;

/**
 * 硬盘数据信息
 *
 * @author heylr
 * @data 2020-10-30 09:45:58
 */
public class StorageInfo {

    private String path;

    private long totalSpace;

    private long usageSpace;

    private long freeSpace;

    public void setFreeSpace(long freeSpace) {
        this.freeSpace = freeSpace;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTotalSpace(long totalSpace) {
        this.totalSpace = totalSpace;
    }

    public void setUsageSpace(long usageSpace) {
        this.usageSpace = usageSpace;
    }

    public long getFreeSpace() {
        return freeSpace;
    }

    public long getTotalSpace() {
        return totalSpace;
    }

    public long getUsageSpace() {
        return usageSpace;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "StorageInfo{" +
                "path='" + path + '\'' +
                ", totalSpace=" + totalSpace +
                ", usageSpace=" + usageSpace +
                ", freeSpace=" + freeSpace +
                '}';
    }
}
