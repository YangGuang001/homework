package com.yang.spring.springExtension;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by yz on 2017/9/4.
 */
public class AwareBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware {
    private String beanName;
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactory : " + beanFactory);
        this.beanFactory = beanFactory;
    }

    public void setBeanName(String s) {
        System.out.println("BeanName : " + s);
        this.beanName = s;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Application : " + applicationContext);
        this.applicationContext = applicationContext;
    }
}
