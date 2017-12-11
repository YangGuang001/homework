package com.yang.springboot.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    ConfigBean configBean;

    @Autowired
    ConfigTestBean configTestBean;

    @Autowired
    DBConnector connector;

    @RequestMapping("/name")
    public String index(){
        return configBean.getName() + ":" + configBean.getAge() ;
    }

    @RequestMapping("/test")
    public String test(){
        return configTestBean.getTest() + ":" + configTestBean.getId() + configTestBean.getShow();
    }

    @RequestMapping("/dbtest")
    public String hellTask() {
        connector.configure();
        return "Hello task";
    }

}
