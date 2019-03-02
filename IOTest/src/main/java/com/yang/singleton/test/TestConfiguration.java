package com.yang.singleton.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@Slf4j
@ComponentScan(basePackages = "com.yang.singleton.test")
public class TestConfiguration {

    public TestConfiguration() {
        log.info("this is ctor");
    }

//    @Bean(name = "testBean", initMethod = "startUp", destroyMethod = "cleanUp")
//    @Scope(value = "prototype")
//    public TestBean testBean() {
//        TestBean testBean = new TestBean();
//        testBean.setUserName("yang");
//        testBean.setPassword("123");
//        testBean.setUrl("http://google.com");
//        return testBean;
//    }
}
