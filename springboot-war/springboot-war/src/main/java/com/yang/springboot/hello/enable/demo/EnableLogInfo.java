package com.yang.springboot.hello.enable.demo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by yz on 2018/4/7.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MyImportSelector.class)
public @interface EnableLogInfo {
    String[] name();
}
