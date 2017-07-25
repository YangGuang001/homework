package com.yang.vm;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yz on 2017/7/22.
 */
public class Client {

    public static void main(String[] args) {
//        ProxyFactory proxyFactory = new ProxyFactory();
//        proxyFactory.setTarget(new HelloImpl());
////        proxyFactory.addAdvice(new HelloBeforeAdvice());
////        proxyFactory.addAdvice(new HelloAfterAdvice());
//        proxyFactory.addAdvice(new HelloBeforeAndAfterAdvice());
//        Hello hello =(Hello) proxyFactory.getProxy();

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Hello hello = (Hello) context.getBean("HelloProxy");

        hello.say("Jack");
    }
}
