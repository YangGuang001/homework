package com.yang.gradle.demo;

import org.junit.Test;

/**
 * Created by yz on 2018/3/22.
 */
public class SwtichTest {

    @Test
    public void testSwith() {
        int num = 2;
        int num2 = 3;
        switch (num) {
            case 1:
                switch (num2) {
                    case 1:
                    case 2:
                    default:
                        System.out.println(num2);
                }
                break;
            case 2:
            default:
                System.out.println(num);
        }
    }
}
