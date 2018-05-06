package com.yang.jmx.test;

/**
 * Created by yz on 2018/5/1.
 */
public class Hello implements HelloMBean {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void printHello() {
        System.out.println("Hello World, " + name);
    }
    public void printHello(String whoName) {
        System.out.println("Hello , " + whoName);
    }
}
