package com.heylr.sysinfo;

import java.util.Scanner;

public class WinSysInfoFactory {

    private static String WIN_CPU_TYPE_CMD = "wmic cpu get name";

    /**
     * 获取windows CPU型号
     *
     * @return
     */
    public static String getCPUType() {
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
}
