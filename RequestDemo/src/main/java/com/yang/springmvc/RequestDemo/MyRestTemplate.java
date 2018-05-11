package com.yang.springmvc.RequestDemo;

import org.springframework.web.client.RestTemplate;

/**
 * Created by yz on 2018/5/7.
 */
public class MyRestTemplate extends RestTemplate {
    private static MyRestTemplate myRestTemplate = new MyRestTemplate();

    public static RestTemplate create() {
        return myRestTemplate;
    }
}
