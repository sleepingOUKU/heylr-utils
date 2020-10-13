package com.heylr.spi.base.selector.api;

import lombok.*;

import java.util.Map;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SpiImplWrapper<T> implements Comparable<SpiImplWrapper> {

    public static final int DEFAULT_ORDER = 10;

    private T spiImpl;

    private int order;


    /**
     * spiImpl 的标识name, 要求唯一
     */
    private String name;


    /**
     * 参数校验规则
     */
    private Map<String, String> paramCondition;


    @Override
    public int compareTo(SpiImplWrapper o) {
        if (o == null) {
            throw new IllegalArgumentException("compable object should not be null!");
        }

        return order - o.getOrder();
    }
}
