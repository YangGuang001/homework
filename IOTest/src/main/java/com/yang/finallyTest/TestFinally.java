package com.yang.finallyTest;

/**
 * Created by yz on 2017/12/4.
 */
public class TestFinally {
    public static void main(String[] args) {
        int i = 10;
        i = testFinallytest();
        System.out.printf("result" + i);
    }

    public static int testFinallytest() {
        try {
            System.out.printf("test try");
            return 10;
        }finally {
            return 5;
        }
    }
}
