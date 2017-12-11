package com.yang.springboot.test.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevDBConntector implements DBConnector {
    @Override
    public void configure() {
        System.out.printf("Dev env");
    }
}
