package com.yang.IteratorTest;

import java.util.*;

public class IteratorTest {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String, String>();

        map.put("yang","18");
        map.put("xin","19");
        map.put("zhao","20");
        //第一种  iterator迭代
        Set<Map.Entry<String,String>> set = map.entrySet();
        Iterator<Map.Entry<String,String>> iterator = set.iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = iterator.next();
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }

        //第二种迭代 for
        for (Map.Entry<String,String> entry : map.entrySet()){
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }

        //第三种迭代方式 整体iterator
        Iterator key = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = iterator.next();
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }

        //第四种迭代方式 单个元素的iterator
        Set<String> keys = map.keySet();
        Iterator<String> iterator1 = keys.iterator();
        while (iterator1.hasNext()){
            String temp = iterator1.next();
            String value = map.get(temp);
            System.out.println("key: " + temp + " value: " + value);
        }
    }
}
