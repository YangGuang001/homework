package com.yang.guava.demo;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yz on 2018/5/3.
 */
public class JoinerTest {
    @Test
    public void testJoiner() {
        List<String> list = new ArrayList<String>();
        list.add("yang");
        list.add("xin");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("zhao");
        System.out.println(Joiner.on('|').appendTo(stringBuilder, list));
    }

    @Test
    public void testUseForNull() {
        System.out.println(Joiner.on("||").useForNull("yang").join(new String[]{"yang","xin","zhao",null}));
    }
}
