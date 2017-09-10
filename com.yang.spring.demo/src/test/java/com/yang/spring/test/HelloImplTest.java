package com.yang.spring.test;

import com.yang.spring.springExtension.Animal;
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
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("extension.xml");
        Animal animal = (Animal) applicationContext.getBean("animal");
        animal.move();
    }

    @Test
     public void testLookup() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("aotowired.xml");
        System.out.println("=======singleton sayHello======");
        HelloApi helloApi1 = context.getBean("hello", HelloApi.class);
        helloApi1.say();
        helloApi1 = context.getBean("hello", HelloApi.class);
        helloApi1.say();
        System.out.println("=======prototype sayHello======");
        HelloApi helloApi2 = context.getBean("hello2", HelloApi.class);
        helloApi2.say();
        helloApi2 = context.getBean("hello2", HelloApi.class);
        helloApi2.say();
    }
}
