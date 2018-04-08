package com.yang.springboot.hello.enable.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by yz on 2018/4/7.
 */
public class MyApplicationStartedEventListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        SpringApplication application = event.getSpringApplication();
        application.setBannerMode(Banner.Mode.OFF);
        System.out.println("=====MyApplicationStartedEventListener=====");
    }
}
