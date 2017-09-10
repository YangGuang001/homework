package com.yang.spring.springExtension;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by yz on 2017/9/4.
 */
public class LifecycleBean implements InitializingBean, DisposableBean {
    private String lifeCycleBeanName;

    public void setLifeCycleBeanName(String lifeCycleBeanName)
    {
        System.out.println("Enter LifecycleBean.setLifeCycleBeanName(), lifeCycleBeanName = " + lifeCycleBeanName);
        this.lifeCycleBeanName = lifeCycleBeanName;
    }

    public void destroy() throws Exception {
        System.out.println("Enter LifecycleBean.destroy()");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("Enter LifecycleBean.afterPropertiesSet()");
    }

    public void beanStart()
    {
        System.out.println("Enter LifecycleBean.beanStart()");
    }

    public void beanEnd()
    {
        System.out.println("Enter LifecycleBean.beanEnd()");
    }
}
