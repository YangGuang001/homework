package com.yang.guice.demo;

/**
 * Created by yz on 2018/3/18.
 */
public class Mobile {
    private String number;

    public Mobile(){
        this.number = "988438434";
    }

    public String toString(){
        return "[Mobile: " + number + "]";
    }
}
