package com.yang.spring.test;

import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Created by yz on 2017/8/17.
 */
public class HelloImpl implements HelloApi {
//    @Setter
//    private String name;
//    @Setter
//    private String age;
    @Setter
    private Date date;

    public void say() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "HelloImpl{" +
                "date=" + date +
                '}';
    }
}
