package com.yang.factorybean.test;

import lombok.Data;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

@Data
public class User {
    private String name;
    private String password;



    public static void main(String[] args){
        ClassPathResource classPathResource = new ClassPathResource("properties.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(classPathResource);
        beanFactory.getBean("user");

//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("properties.xml");
//        applicationContext.getBean("user");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("properties.xml");
        User user = (User)applicationContext.getBean("user");
        System.out.println(user);
    }
}
