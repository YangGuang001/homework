package com.yang.hystrix.demo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * Created by yz on 2018/2/25.
 */
public class HelloHystrixCommand extends HystrixCommand {
    private final String name;

    public HelloHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGrpup"));
        this.name = name;
    }

    protected String run() throws Exception {
        return "Hello " + name;
        //throw new Exception("call error");
    }

    @Override
    protected Object getFallback() {
        return "fallback: " + name;
    }
}
