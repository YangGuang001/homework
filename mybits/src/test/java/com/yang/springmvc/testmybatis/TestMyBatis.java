package com.yang.springmvc.testmybatis;

import com.yang.springmvc.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestMyBatis {

//    private ApplicationContext applicationContext;
//
//    private IUserService userService;
//
//    @Before
//    public void before() {
//        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        userService = (IUserService) applicationContext.getBean("userService");
//    }
//
//    @Test
//    public void test1() {
//        System.out.printf(userService.getUserById(1).getUserName());
//    }

    @Resource
    private IUserService userService = null;



}
