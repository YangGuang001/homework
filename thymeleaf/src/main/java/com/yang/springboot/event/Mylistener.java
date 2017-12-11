package com.yang.springboot.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class Mylistener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.printf("application starting !!!!!");
    }
}
