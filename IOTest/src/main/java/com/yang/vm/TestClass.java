package com.yang.vm;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    @Test
    public void testBootstrapClassLoaderTest() {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }


    @Test
    public void testBootstrapClassLoader() {
        TestClass testClass = new TestClass();
        System.out.println(testClass.getClass().getClassLoader().toString());
        System.out.println(testClass.getClass().getClassLoader().getParent().toString());
        System.out.println(int.class.getClassLoader().toString());
    }

    @Test
    public void testDiskClassLoader() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        DiskClassLoader diskClassLoader = new DiskClassLoader("D:\\java\\test");
        Class c = diskClassLoader.loadClass("Hello");

        if (null != c) {
            Object obj = c.newInstance();
            Method method = c.getDeclaredMethod("say", null);
            method.invoke(obj, null);
        }
    }
}
