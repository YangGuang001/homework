package com.yang.sprinboot;

import lombok.extern.apachecommons.CommonsLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@CommonsLog
public class test {

    @Test
    public void testSequence() {

       log.info("test yangxinzhao");
    }
}
