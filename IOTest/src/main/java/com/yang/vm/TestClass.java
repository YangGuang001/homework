package com.yang.vm;

import java.lang.reflect.Field;

/**
 * Created by yz on 2017/7/28.
 */
public class TestClass {
    public static int count = 0;
    static {
        init();
    }
    private static void init(){
        System.out.println("get static black");
        System.out.println("static variable : " + count++);
    }

    public void say(){
        System.out.println("this is void method");
    }

    public static void main(String[] args) {
        try {
            Class<?> clzz = Class.forName("com.yang.vm.TestClass");
            Field field = clzz.getField("count");
            Integer count = (Integer) field.get(clzz);
            System.out.println("get count = " + count);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}
