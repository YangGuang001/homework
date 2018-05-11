package com.yang.spring.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by yz on 2018/5/6.
 */
public class AutoConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        Person person = applicationContext.getBean(Person.class);
        person.sayHello();
    }
}
