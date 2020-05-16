package com.yuewen.nrzx.switcher.ex;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * project : switch-server
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ErrorConstMsg {

    String value() default "";
}
