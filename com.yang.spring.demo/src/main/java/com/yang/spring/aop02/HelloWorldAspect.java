package com.yang.spring.aop02;

public class HelloWorldAspect {
    public void beforeAdice() {
        System.out.printf("======before advice=====");
    }

    public void afterFinallyAdvice() {
        System.out.printf("=======after finally advice=====");
    }
}
