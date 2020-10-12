package com.heylr.entity;

/**
 * 系统当前运行基本状态
 * @author heylr
 * @date 2020-10-12 15:38:41
 */
public class OsBaseData {

    private long committedVirtualMemorySize;

    private long totalSwapSpaceSize;

    private long freeSwapSpaceSize;

    private long freePhysicalMemorySize;

    private double systemCpuLoad;

    private double processCpuLoad;

    private String arch;

    private int availableProcessor;

    private String name;

    public void setCommittedVirtualMemorySize(long committedVirtualMemorySize) {
        this.committedVirtualMemorySize = committedVirtualMemorySize;
    }

    public long getCommittedVirtualMemorySize() {
        return committedVirtualMemorySize;
    }

    public void setTotalSwapSpaceSize(long totalSwapSpaceSize) {
        this.totalSwapSpaceSize = totalSwapSpaceSize;
    }

    public long getTotalSwapSpaceSize() {
        return totalSwapSpaceSize;
    }

    public void setFreeSwapSpaceSize(long freeSwapSpaceSize) {
        this.freeSwapSpaceSize = freeSwapSpaceSize;
    }

    public long getFreeSwapSpaceSize() {
        return freeSwapSpaceSize;
    }

    public void setFreePhysicalMemorySize(long freePhysicalMemorySize) {
        this.freePhysicalMemorySize = freePhysicalMemorySize;
    }

    public long getFreePhysicalMemorySize() {
        return freePhysicalMemorySize;
    }

    public void setSystemCpuLoad(double systemCpuLoad) {
        this.systemCpuLoad = systemCpuLoad;
    }

    public double getSystemCpuLoad() {
        return systemCpuLoad;
    }

    public void setProcessCpuLoad(double processCpuLoad) {
        this.processCpuLoad = processCpuLoad;
    }

    public double getProcessCpuLoad() {
        return processCpuLoad;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public String getArch() {
        return arch;
    }

    public void setAvailableProcessor(int availableProcessor) {
        this.availableProcessor = availableProcessor;
    }

    public int getAvailableProcessor() {
        return availableProcessor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "OsBaseData{" +
                "committedVirtualMemorySize=" + committedVirtualMemorySize +
                ", totalSwapSpaceSize=" + totalSwapSpaceSize +
                ", freeSwapSpaceSize=" + freeSwapSpaceSize +
                ", freePhysicalMemorySize=" + freePhysicalMemorySize +
                ", systemCpuLoad=" + systemCpuLoad +
                ", processCpuLoad=" + processCpuLoad +
                ", arch='" + arch + '\'' +
                ", availableProcessor=" + availableProcessor +
                ", name='" + name + '\'' +
                '}';
    }
}
