package com.yang.spring.conditional;

import com.yang.spring.event.EventConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by yz on 2017/12/21.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(ConditionConfig.class);

        ListService listService = configApplicationContext.getBean(ListService.class);
        System.out.printf("cmd : " + listService.showListCmd());
    }
}
