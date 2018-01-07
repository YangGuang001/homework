package com.yang.spring.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by yz on 2017/12/20.
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.getEnvironment().setActiveProfiles("prod");
        configApplicationContext.register(ProfileConfig.class);
        configApplicationContext.refresh();

        DemoBean demoBean = configApplicationContext.getBean(DemoBean.class);

        System.out.printf("content : " + demoBean.getContent());
        configApplicationContext.close();
    }
}
