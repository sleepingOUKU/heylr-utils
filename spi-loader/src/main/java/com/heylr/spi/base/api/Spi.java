package com.heylr.spi.base.api;

import com.heylr.spi.base.selector.DefaultSelector;
import com.heylr.spi.base.selector.api.ISelector;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Spi {
    Class<? extends ISelector> selector() default DefaultSelector.class;
}
