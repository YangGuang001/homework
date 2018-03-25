package com.yang.java8.demo;

import lombok.Data;
import lombok.ToString;

/**
 * Created by yz on 2018/3/25.
 */
@Data
@ToString
public class Person {
    String name;
    int age;

    public void printPersion() {
        toString();
    }
}
