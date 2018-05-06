package com.yang.jmx.test;

import javax.management.*;
import java.util.ArrayList;
import java.util.List;


public class DynMBeans implements DynamicMBean {
    private String beanName;

    public static List<DynMBeans> createMBeans(List<String> beans) {
        List<DynMBeans> mbeans = new ArrayList<DynMBeans>();
        createMBeans(beans, mbeans);
        return mbeans;
    }

    private static void createMBeans(List<String> beans, List<DynMBeans> mbeans) {
        for (String bean : beans) {
            mbeans.add(new DynMBeans(bean));
        }
    }

    public DynMBeans(String beanName) {
        this.beanName = beanName;
    }

    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        return this.beanName;
    }

    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        throw new UnsupportedOperationException("setAttribute is not implemented");
    }

    public AttributeList getAttributes(String[] attributes) {
        return null;
    }

    public AttributeList setAttributes(AttributeList attributes) {
        throw new UnsupportedOperationException("setAttribute is not implemented");
    }

    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        throw new UnsupportedOperationException("setAttribute is not implemented");
    }

    public MBeanInfo getMBeanInfo() {
        return null;
    }
}
