package com.yang.vm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yz on 2017/7/9.
 */
public class StringTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true){
            list.add(String.valueOf(i).intern());
        }
    }
}
