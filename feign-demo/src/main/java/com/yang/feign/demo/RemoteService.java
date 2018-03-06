package com.yang.feign.demo;

import feign.Param;
import feign.RequestLine;

/**
 * Created by yz on 2018/3/6.
 */
public interface RemoteService {
    @RequestLine("GET /list?name={name}")
    String getOwner(@Param(value = "name") String name);
}
