package com.yang.MutilThread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class ThreadLocalDemo {

    @Test
    public void testThreadLocal() {
        List<String> list = Collections.singletonList("yang");

        List<String> list1 = Collections.synchronizedList(list);
    }

}
