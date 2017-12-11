package com.yang.springboot.arguments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class MyArgumentsOption {



    @Autowired
    public MyArgumentsOption(ApplicationArguments arguments) {
        System.out.printf("arguments :" + arguments.toString());
    }
}
