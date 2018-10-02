package com.yang.singleton.test;

/**
 * Created by yz on 2018/9/18.
 */
public class SingletonDemoTwo {

    private static class SingletonInstance {
        private static final SingletonDemoTwo INSTANCE = new SingletonDemoTwo();
    }

    public static SingletonDemoTwo getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
