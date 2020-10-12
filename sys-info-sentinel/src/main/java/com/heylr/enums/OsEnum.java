package com.heylr.enums;

/**
 * 操作系统枚举类
 * @author zhangyunlin
 * @date 2020-10-10 15:53:23
 */
public enum OsEnum {
    UNKNOWN_TYPE(0,"unknown"),
    WIN_TYPE(1,"windows"),
    LINUX_TYPE(2,"linux"),
    MAC_TYPE(3,"mac")
    ;

    private int type;

    private String name;

    OsEnum(int type,String name){
        this.type = type;
        this.name = name;
    }
}
