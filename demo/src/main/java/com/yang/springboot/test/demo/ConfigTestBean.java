package com.yang.springboot.test.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "com.yang")
@PropertySource("classpath:test.properties")
@Data
public class ConfigTestBean {
    private String test;
    private int id;
    private String show;
}
