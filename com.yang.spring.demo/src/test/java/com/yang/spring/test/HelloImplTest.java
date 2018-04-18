package com.yang.spring.test;

import com.yang.spring.aop.Math;
import com.yang.spring.aop.MathIntf;
import com.yang.spring.aop02.IHelloWorldService;
import com.yang.spring.demo.ExampleInitBean;
import com.yang.spring.springExtension.Animal;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.io.File;

/**
 * Created by yz on 2017/8/17.
 */
public class HelloImplTest {

    @Test
    public void testInitBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("initBean.xml");
        ExampleInitBean exampleInitBean = (ExampleInitBean) context.getBean("exampleInitBean");
        exampleInitBean.test();
    }


    @Test
    public void testHello(){
//        File file = new File("hello.xml");
//        Resource resource = new FileSystemResource(file);
//        BeanFactory beanFactory = new XmlBeanFactory(resource);

//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("hello.xml");
//        HelloImpl hello = (HelloImpl) applicationContext.getBean("bean",HelloApi.class);
//        hello.say();

//        Resource resource = new ClassPathResource("hello.xml");
//        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource);
//        HelloImpl hello = (HelloImpl) xmlBeanFactory.getBean("bean");
//        hello.say();

        ClassPathResource resource = new ClassPathResource("hello.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);
        HelloImpl hello = (HelloImpl) factory.getBean("bean");
        hello.say();
    }

    @Test
    public void testBean(){
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("extension.xml");
//        XmlWebApplicationContext xmlWebApplicationContext = new XmlWebApplicationContext();
//        xmlWebApplicationContext.start();
//        GenericApplicationContext genericApplicationContext = new GenericApplicationContext(applicationContext);
//        GenericWebApplicationContext webApplicationContext = new GenericWebApplicationContext(genericApplicationContext.getDefaultListableBeanFactory());
//        webApplicationContext.refresh();

        XmlWebApplicationContext webApplicationContext = new XmlWebApplicationContext();
        webApplicationContext.setConfigLocation("classpath:*.xml");
        webApplicationContext.refresh();
        Animal animal = (Animal) webApplicationContext.getBean("animal");
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

    @Test
    public void testAop() {
        //XmlBeanDefinitionReader 只是加载没有aop的这一部分
//        ClassPathResource resource = new ClassPathResource("aop01.xml");
//        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
//        reader.loadBeanDefinitions(resource);

        ApplicationContext factory = new ClassPathXmlApplicationContext("aop01.xml");
        MathIntf math = (MathIntf) factory.getBean("math");
        int n1 = 5;
        int n2 = 1;
        math.add(n1, n2);
        math.sub(n1, n2);
        math.mut(n1, n2);
        math.div(n1, n2);
    }

    @Test
    public void testAop2() {
//        ClassPathResource resource = new ClassPathResource("aop02.xml");
//        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
//        reader.loadBeanDefinitions(resource);

        ApplicationContext factory = new ClassPathXmlApplicationContext("aop02.xml");

        IHelloWorldService helloWorldSerivce = ( IHelloWorldService ) factory.getBean("helloWorldSerivce");
        helloWorldSerivce.sayHello();
    }
}
