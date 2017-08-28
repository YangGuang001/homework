package com.yang.spring.test;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;

/**
 * Created by yz on 2017/8/17.
 */
public class HelloImplTest {

    @Test
    public void testHello(){
//        File file = new File("hello.xml");
//        Resource resource = new FileSystemResource(file);
//        BeanFactory beanFactory = new XmlBeanFactory(resource);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("hello.xml");

        HelloImpl hello = (HelloImpl) applicationContext.getBean("bean",HelloApi.class);
        hello.say();
    }

    @Test
    public void testBean(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("DependenceBean.xml");

        DependentBean bean = (DependentBean) applicationContext.getBean("dependentBean");

    }
}
