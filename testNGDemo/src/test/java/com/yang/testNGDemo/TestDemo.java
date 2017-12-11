package com.yang.testNGDemo;

import org.junit.Assert;
import org.testng.annotations.Test;

public class TestDemo {

    @Test
    public void test() {
        Demo demo = new Demo();
        String test = demo.test();

        Assert.assertNotNull(test);
        Assert.assertEquals(test, "test");
    }
}
