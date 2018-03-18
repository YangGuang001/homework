package com.yang.guice.demo;

import com.google.inject.Inject;

/**
 * Created by yz on 2018/3/18.
 */
public class Person {
    private Mobile mobile;
    private Laptop laptop;

    @Inject
    public Person(Mobile mobile, Laptop laptop){
        this.mobile = mobile;
        this.laptop = laptop;
    }

    public void diplayInfo(){
        System.out.println("Mobile:" + mobile);
        System.out.println("Laptop:" + laptop);
    }
}
