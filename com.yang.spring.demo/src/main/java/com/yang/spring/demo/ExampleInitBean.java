package com.yang.spring.demo;

import org.springframework.beans.factory.InitializingBean;

public class ExampleInitBean {

    public void init() throws Exception {
        System.out.printf("spring begin !!!!");
    }

    public void test() {
        System.out.printf("testing !!!!");
    }
}
