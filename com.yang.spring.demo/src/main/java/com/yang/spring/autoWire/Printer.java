package com.yang.spring.autoWire;

/**
 * Created by yz on 2017/9/3.
 */
public class Printer {
    private int counter = 0;
    public void print(String type){
        System.out.println(type + " printer:" + counter++);
    }
}
