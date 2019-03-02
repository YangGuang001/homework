package com.yang.proxyDymic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationProxy implements InvocationHandler {

    private Object target;

    public MyInvocationProxy(Object target) {
        this.target = target;
    }

    public <T> T getInstance() {
        return (T) MyProxy.newProxyInstance(new MyClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("invoke before" + "method: " + method);
        Object result = method.invoke(target, args);
        System.out.println("invoke after");

        return null;
    }
}
