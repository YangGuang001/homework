package com.yang.IOTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by yz on 2018/1/24.
 */
public class HeapMapTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
                list.add(String.valueOf(i++));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
