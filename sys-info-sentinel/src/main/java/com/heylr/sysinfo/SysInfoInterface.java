package com.heylr.sysinfo;

import com.heylr.entity.StorageInfo;

import java.util.List;

/**
 * 系统信息获取基础接口类
 *
 * @author heylr
 * @date 2020-10-30 09:37:21
 */
public interface SysInfoInterface {

    /**
     * 获取CPU型号
     * @return
     */
    String getCpuType();

    /**
     * 获取存储盘信息
     * @return {@link StorageInfo} 列表，包含服务器所有能够获得的存储信息。
     */
    List<StorageInfo> getStorageInfo();


}
