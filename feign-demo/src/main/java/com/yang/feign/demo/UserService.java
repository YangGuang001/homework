package com.yang.feign.demo;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * Created by yz on 2018/3/6.
 */
public interface UserService {

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("GET {id}")
    User getOwer(@Param(value = "id")int id);
}
