package com.yang.guava.demo;

import com.google.common.base.Splitter;
import org.junit.Test;


/**
 * Created by yz on 2018/5/3.
 */
public class testSpiltter {
    @Test
    public void testSpiltter() {
        System.out.println(Splitter.on(',').trimResults().omitEmptyStrings().split("the ,quick, , brown         , fox,              jumps, over, the, lazy, little dog."));


    }
}
