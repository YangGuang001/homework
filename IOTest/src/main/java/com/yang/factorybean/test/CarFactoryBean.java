package com.yang.factorybean.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yz on 2018/3/15.
 */
public class CarFactoryBean implements FactoryBean<Car>,ApplicationContextAware,InitializingBean {
    private ApplicationContext context;

    private String brand;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    //返回bean的对象
    public Car getObject() throws Exception {
        return new Car(brand,500000);
    }

    //返回的bean的类型
    public Class<?> getObjectType() {
        // TODO Auto-generated method stub
        return Car.class;
    }

    //是否是单例的
    public boolean isSingleton() {
        // TODO Auto-generated method stub
        return true;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.assertNotNull(brand);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Test
    public void testFactoryBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("factorytest.xml");
        System.out.println(((Car)context.getBean("car1")).getBrand());
    }
}
