package com.heylr.sysinfo;

import com.heylr.entity.OsBaseData;
import com.heylr.enums.OsEnum;
import com.heylr.util.StringUtil;
import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * 获取服务器基本信息
 *
 * @author heylr
 * @date 2020-10-10 15:50:32
 */
public class BaseInfoFactory {

    /**
     * 获取当前服务器操作系统名称
     *
     * @return
     */
    public static String getOsName() {
        return System.getProperty("os.name");
    }


    /**
     * 获取操作系统类型
     *
     * @return
     */
    public static OsEnum getOsEnum() {
        OsEnum result = OsEnum.UNKNOWN_TYPE;
        String osName = getOsName();

        if (StringUtil.isNotBlank(osName) && osName.length() > 3) {
            switch (osName.substring(0, 3).toLowerCase()) {
                case "win":
                    result = OsEnum.WIN_TYPE;
                    break;
                case "lin":
                    result = OsEnum.LINUX_TYPE;
                    break;
                case "mac":
                    result = OsEnum.MAC_TYPE;
                default:
                    break;
            }
        }
        return result;
    }


    /**
     * 获取CPU处理器核数
     *
     * @return
     */
    public static int getCPUProcessor() {
        return Runtime.getRuntime().availableProcessors();
    }


    public static OsBaseData getOsBaseData() {
        OsBaseData osBaseData = new OsBaseData();
        OperatingSystemMXBean osmxb = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        osBaseData.setCommittedVirtualMemorySize(osmxb.getCommittedVirtualMemorySize());
        osBaseData.setTotalSwapSpaceSize(osmxb.getTotalSwapSpaceSize());
        osBaseData.setFreeSwapSpaceSize(osmxb.getFreeSwapSpaceSize());
        osBaseData.setFreePhysicalMemorySize(osmxb.getFreePhysicalMemorySize());

        osBaseData.setSystemCpuLoad(osmxb.getSystemCpuLoad());
        osBaseData.setProcessCpuLoad(osmxb.getProcessCpuLoad());


        osBaseData.setArch(osmxb.getArch());
        osBaseData.setAvailableProcessor(osmxb.getAvailableProcessors());
        osBaseData.setName(osmxb.getName());

        return osBaseData;
    }

    /**
     * 获取内存大小(b)单位
     *
     * @return
     */
    public static long getTotalMemory() {
        OperatingSystemMXBean osmxb = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        return osmxb.getTotalPhysicalMemorySize();
    }

    /**
     * 获取剩余内存大小（b)单位
     *
     * @return
     */
    public static long getFreeMemory() {
        OperatingSystemMXBean osmxb = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        return osmxb.getFreePhysicalMemorySize();
    }
}
