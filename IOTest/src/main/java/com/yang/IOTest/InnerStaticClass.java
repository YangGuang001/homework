package com.yang.IOTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class InnerStaticClass {

    private int a = 1;

    private int b = 2;

    public int print() {
        return a + b;
    }

    public InnerClass innerClass;

    public InnerStaticClass() {
        innerClass = new InnerClass();
    }

    static class InnerClass {

        private static int a = 1;

        private static int b = 3;

        public int printNum() {
            return a + b;
        }

        public static int printNum1() {
            return a + b;
        }

        public void print() {
            log.info("static inner class");
            printHello();
        }
    }

    public void printInnerClass() {
        log.info("inner static class" + innerClass.printNum());
    }

    public static void printHello() {
        log.info("print static method: {}", InnerClass.printNum1());
    }

    @Test
    public void test() {
        InnerStaticClass innerStaticClass = new InnerStaticClass();
        innerStaticClass.print();
        innerStaticClass.printInnerClass();
    }
}
