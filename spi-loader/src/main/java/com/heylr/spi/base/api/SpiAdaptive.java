package com.heylr.spi.base.api;

import com.heylr.spi.base.selector.DefaultSelector;
import com.heylr.spi.base.selector.api.ISelector;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SpiAdaptive {

    Class<? extends ISelector> selector() default DefaultSelector.class;

}
