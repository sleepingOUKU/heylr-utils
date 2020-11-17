package com.heylr.sysinfo;

import com.heylr.entity.StorageInfo;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class WinSysInfoFactory implements SysInfoInterface {

    private static String WIN_CPU_TYPE_CMD = "wmic cpu get name";

    /**
     * 获取windows CPU型号
     *
     * @return
     */
    @Override
    public String getCpuType() {
        StringBuilder cpuName = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(WIN_CPU_TYPE_CMD);

            process.getOutputStream().close();

            Scanner sc = new Scanner(process.getInputStream());

            sc.next();

            while (sc.hasNext()) {
                cpuName.append(sc.next()).append(" ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cpuName.toString();
    }


    @Override
    public List<StorageInfo> getStorageInfo() {

        List<StorageInfo> infoList = new LinkedList<>();

        File[] files = File.listRoots();

        StorageInfo storageInfo;
        for (File file : files) {
            storageInfo = new StorageInfo();
            storageInfo.setPath(file.getPath());
            storageInfo.setTotalSpace(file.getTotalSpace());
            storageInfo.setFreeSpace(file.getFreeSpace());
            storageInfo.setUsageSpace(file.getTotalSpace() - file.getFreeSpace());
            infoList.add(storageInfo);
        }

        return infoList;
    }
}
