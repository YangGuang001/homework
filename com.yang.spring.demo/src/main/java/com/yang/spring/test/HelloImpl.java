package com.yang.spring.test;

import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * Created by yz on 2017/8/17.
 */
public class HelloImpl implements HelloApi {
    @Setter
    private String name;
    @Setter
    private String age;

    public void say() {
        System.out.println("name : " + name + "age : " + age);
    }

}
