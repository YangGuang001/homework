package com.yang.spring.configuration;

public class Person {

    private Hello hello;

    public Person(Hello hello) {
        this.hello = hello;
    }

    public void sayHello() {
        System.out.println(this.hello.getMsg());
    }
}
