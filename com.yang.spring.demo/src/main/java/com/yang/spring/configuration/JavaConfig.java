package com.yang.spring.configuration;

import org.springframework.context.annotation.Bean;

/**
 * Created by yz on 2018/5/6.
 */
public class JavaConfig {
    @Bean
    public Hello hello() {
        return new Hello();
    }

//    @Bean
//    public Person person() {
//        return new Person(hello());
//    }

    @Bean
    public Person person(Hello hello) {
        return new Person(hello);
    }
}
