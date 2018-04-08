package com.springboot.aotuconfig.hello;

/**
 * Created by yz on 2018/4/6.
 */
public class Hello {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String sayHello() {
        return "hello " + msg;
    }
}
