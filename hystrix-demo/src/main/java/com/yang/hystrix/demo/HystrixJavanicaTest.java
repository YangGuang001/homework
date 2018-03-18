package com.yang.hystrix.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:appplication.xml")
public class HystrixJavanicaTest {

    @HystrixCommand(groupKey = "default", commandKey = "default", defaultFallback = "defaultName")
    public String getNameByid(int id) {
        throw new RuntimeException("return fallack error");  //
    }

    private String defaultName(int id) {
        return "default";
    }


    @Test
    public void testHystrixJavanica() {
//        ApplicationContext context = new ClassPathXmlApplicationContext("appplication.xml");
//        System.out.println(context.getBean("hystrixAspect"));
        HystrixJavanicaTest test = new HystrixJavanicaTest();
        Assert.assertEquals("default", test.getNameByid(1));
    }
}
