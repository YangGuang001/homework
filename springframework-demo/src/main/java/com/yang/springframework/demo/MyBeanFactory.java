package com.yang.springframework.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * Created by yz on 2018/5/29.
 */
public class MyBeanFactory implements BeanFactoryAware {

    private BeanFactory beanFactory;

    public MyBeanFactory() {
        System.out.println("contrcation BeanFactory");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println(beanFactory.getBean("person"));
        System.out.println("BeanFactoryAware setBeanFactory");
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
