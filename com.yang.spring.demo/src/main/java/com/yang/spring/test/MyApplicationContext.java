package com.yang.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

/**
 * Created by yz on 2018/4/10.
 */
public class MyApplicationContext extends GenericWebApplicationContext {

    private ApplicationContext applicationContext;

    public MyApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

}
