package com.yang.vm;

import org.springframework.stereotype.Component;

/**
 * Created by yz on 2017/7/21.
 */
@Component(value = "aop.hello")
public interface Hello {
    void say(String str);
}
