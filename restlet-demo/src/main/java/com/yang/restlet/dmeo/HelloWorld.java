package com.yang.restlet.dmeo;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Created by yz on 2018/6/16.
 */
public class HelloWorld extends ServerResource {

    @Get
    public String hello() {
        return "hello world";
    }
}
