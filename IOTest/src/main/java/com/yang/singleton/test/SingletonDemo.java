package com.yang.singleton.test;

/**
 * Created by yz on 2018/9/18.
 */
public class SingletonDemo {

    private static volatile SingletonDemo singletonDemo;

    private SingletonDemo() {
    }

    public static SingletonDemo getInstance() {
        if (null == singletonDemo) {
            synchronized (SingletonDemo.class) {
                if (null == singletonDemo) {
                    singletonDemo = new SingletonDemo();
                }
                return singletonDemo;
            }
        }
        return singletonDemo;
    }
}
