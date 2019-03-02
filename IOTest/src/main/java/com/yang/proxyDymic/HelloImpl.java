package com.yang.proxyDymic;

public class HelloImpl implements Hello {
    @Override
    public String hello(String str) {
        return "hello " + str;
    }
}
