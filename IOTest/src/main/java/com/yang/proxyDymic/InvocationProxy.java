package com.yang.proxyDymic;

import sun.misc.ProxyGenerator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvocationProxy implements InvocationHandler {
    private Object target;

    public InvocationProxy(Object target) {
        this.target = target;
    }

    public <T> T getInstance() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke before" + "method: " + method);
        Object result = method.invoke(target, args);
        System.out.println("invoke after");


        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy", new Class[]{HelloImpl.class});
        File file = new File("$Proxy.class");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.close();
        return result;
    }

    public static void main(String[] args) {
        Hello hello = new HelloImpl();
        Hello hello1 = new InvocationProxy(hello).getInstance();
        hello1.hello("world");
    }
}
