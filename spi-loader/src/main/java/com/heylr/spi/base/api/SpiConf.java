package com.heylr.spi.base.api;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface SpiConf {

    String name() default "";

    String[] params() default {};

    int order() default -1;


}
