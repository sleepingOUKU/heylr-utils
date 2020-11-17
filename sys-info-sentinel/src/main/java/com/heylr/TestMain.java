package com.heylr;

import com.heylr.base.BaseSysInfoLoader;
import com.heylr.sysinfo.BaseInfoFactory;
import com.heylr.sysinfo.WinSysInfoFactory;

import java.io.File;

public class TestMain {

    public static void main(String[] args) {
        File[] files = File.listRoots();

        for (File file : files) {
            System.out.println("----------------------------");
            System.out.println(file.getPath());
            System.out.println(file.getTotalSpace());
            System.out.println(file.getFreeSpace());
            System.out.println(file.getTotalSpace() - file.getFreeSpace());
        }
    }
}
