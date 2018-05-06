package com.yang.jackson.demo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by yz on 2018/5/4.
 */
@Data
@NoArgsConstructor
@ToString
public class Student {
    private String name;
    private int age;
}
