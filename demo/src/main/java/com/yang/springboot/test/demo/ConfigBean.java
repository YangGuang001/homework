package com.yang.springboot.test.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.yang")
public class ConfigBean {
    private String name;
    private int age;
}
