package com.yang.singleton.test;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Data
@ToString
@Slf4j
@Component
public class TestBean {
    private String userName;
    private String url;
    private String password;

    public void sayHello() {
        log.info("hello-world,{}", toString());
    }

    public void startUp() {
        log.info("this is init");
    }

    public void cleanUp() {
        log.info("this is clean");
    }
}
