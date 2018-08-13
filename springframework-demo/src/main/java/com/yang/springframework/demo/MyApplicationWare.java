package com.yang.springframework.demo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by yz on 2018/5/25.
 */
public class MyApplicationWare implements ApplicationContextAware {

    private ApplicationContext context;

    public MyApplicationWare() {
        System.out.println("【applicationConetxtWare】初始化");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
        System.out.println("【setApplicationWare】");
    }
}
