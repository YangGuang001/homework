package com.yang.spring.aop;


import org.aspectj.lang.JoinPoint;

public class Advices {
    public void before(){
        System.out.println("----------前置通知----------");
        //System.out.println(jp.getSignature().getName());JoinPoint jp
    }

    public void after(){
        System.out.println("----------最终通知----------");
    }
}
