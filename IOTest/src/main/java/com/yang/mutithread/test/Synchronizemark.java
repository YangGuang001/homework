package com.yang.mutithread.test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by yz on 2018/7/23.
 */
public class Synchronizemark implements Counter {
    private long count = 0;

    public long getValue() {
        return count;
    }

    public synchronized void increment() {
        count++;
    }
}
