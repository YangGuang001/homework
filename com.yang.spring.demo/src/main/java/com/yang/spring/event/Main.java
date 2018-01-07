package com.yang.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by yz on 2017/12/21.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(EventConfig.class);
        DemoPublisher demoListener = configApplicationContext.getBean(DemoPublisher.class);
        demoListener.publish("yangxinzhao");
        configApplicationContext.close();
    }
}
