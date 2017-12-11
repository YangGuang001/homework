package com.yang.springboot.test.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdDBConntector implements DBConnector {
    @Override
    public void configure() {
        System.out.printf("prod env");
    }
}
