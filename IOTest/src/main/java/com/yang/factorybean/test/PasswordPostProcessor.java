package com.yang.factorybean.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.TypedStringValue;

public class PasswordPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        MutablePropertyValues propertyValues = configurableListableBeanFactory.getBeanDefinition("user").getPropertyValues();
        PropertyValue encryPassWordObj = propertyValues.getPropertyValue("password");
        if (encryPassWordObj == null || encryPassWordObj.getValue() == null) {
            return;
        }
        String encryPassword = ((TypedStringValue) encryPassWordObj.getValue()).getValue();
        //模拟解密
        String realPassword = encryPassword + "AAAAA";
        propertyValues.add("password", realPassword);
    }
}
