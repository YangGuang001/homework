package com.yang.vm;

import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * Created by yz on 2017/7/9.
 */
@Component(value = "helloImpl")
public class HelloImpl implements Hello{
    public void say(String word){
        System.out.println(word);
    }

    public static void main(String[] args) {
        Hello hello = new HelloImpl();

        DynamicProxy dynamicProxy = new DynamicProxy(hello);

        Hello helloProxy = dynamicProxy.getProxy();

        helloProxy.say("yangxinzhao");
    }
}
