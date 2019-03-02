package com.yang.IOTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class InnerClass {

    private int a = 1;

    private int b = 2;

    private Inner inner;

    public InnerClass() {
        inner = new Inner();
    }

    class Inner {
        private int a = 1;

        private int b = 3;

        public int printNum() {
            return a + b;
        }
    }

    public int printNum() {
        return a + b;
    }

    public int innerPrintNum() {
        return inner.printNum();
    }




    @Test
    public void testInnerClass() {
        InnerClass innerClass = new InnerClass();
        log.info("inner class: " + innerClass.printNum());
        log.info("inner class: " + innerClass.innerPrintNum());
    }
}
