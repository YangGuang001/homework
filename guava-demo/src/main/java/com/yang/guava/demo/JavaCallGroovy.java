package com.yang.guava.demo;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.IOException;

/**
 * Created by yz on 2018/9/29.
 */
public class JavaCallGroovy {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
        ClassLoader parent = ClassLoader.getSystemClassLoader();

        GroovyClassLoader loader = new GroovyClassLoader(parent);

        Class gclass = loader.parseClass(new File("foo.groovy"));

        GroovyObject groovyObject = (GroovyObject) gclass.newInstance();

        Object obj = groovyObject.invokeMethod("add", new Object[] {

                new Integer(2), new Integer(1) });

        System.out.println(obj);
    }
}
