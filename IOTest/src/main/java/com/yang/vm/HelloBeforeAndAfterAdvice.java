package com.yang.vm;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by yz on 2017/7/22.
 */
@Component(value = "greetingAroundAdvice")
public class HelloBeforeAndAfterAdvice  {
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("after");
    }

    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Before");
    }
}
