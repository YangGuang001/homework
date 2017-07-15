package com.yang.yaml;

import lombok.Data;

import java.util.List;

/**
 * Created by yz on 2017/7/9.
 */
@Data
public class Person {
    private String name;
    private int age;
    private String sex;
    private List<Person> children;
}
