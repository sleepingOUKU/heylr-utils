package com.heylr.factory;

import com.heylr.base.SplitLogInterface;

/**
 * 分文件log抽象实现类
 * @author heylr
 * @date 2020-8-10 10:28:31
 */
public abstract class AbstractSplitLogFactory implements SplitLogInterface {


    public void outConsole(Object o) {
        System.out.println(o.toString());
    }
}
