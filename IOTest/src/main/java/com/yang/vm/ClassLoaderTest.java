package com.yang.vm;

/**
 * Created by yz on 2017/7/20.
 */
public class ClassLoaderTest {
    static {
        System.out.println("static init");
    }

    public ClassLoaderTest() {
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("com.yang.vm.ClassLoaderTest", true, Thread.currentThread().getContextClassLoader());
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
