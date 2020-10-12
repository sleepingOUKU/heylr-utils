package com.heylr;

import com.heylr.sysinfo.BaseInfoFactory;
import com.heylr.sysinfo.WinSysInfoFactory;

public class TestMain {

    public static void main(String[] args) {
        System.out.println(WinSysInfoFactory.getCPUType());
    }
}
