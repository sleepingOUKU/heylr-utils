package com.heylr.base;


import com.heylr.enums.OsEnum;
import com.heylr.sysinfo.BaseInfoFactory;
import com.heylr.sysinfo.WinSysInfoFactory;

/**
 * Sys基本信息加载类
 * @author heylr
 * @date 2020-10-10 15:16:37
 */
public class BaseSysInfoLoader {


    private static String osName = "";

    private static OsEnum osEnum = OsEnum.UNKNOWN_TYPE;

    private static String cpuType = "";

    static {
        load();
    }

    private static void load(){
        osName = BaseInfoFactory.getOsName();
        osEnum = BaseInfoFactory.getOsEnum();

        //判断类型
        if(osEnum.equals(OsEnum.WIN_TYPE)){
            //windows type
            cpuType = WinSysInfoFactory.getCPUType();
        }else if(osEnum.equals(OsEnum.LINUX_TYPE)){
            //linux type
        }else if(osEnum.equals(OsEnum.MAC_TYPE)){
            //macOs type
        }else{
            //unknown type

        }

    }

    public static OsEnum getOsEnum() {
        return osEnum;
    }

    public static String getOsName() {
        return osName;
    }

    public static String getCpuType() {
        return cpuType;
    }
}
