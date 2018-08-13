package com.yang.mutithread.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yz on 2018/7/23.
 */
public class ReentrantLockBeanchmark implements Counter{
    private volatile long count = 0;

    private Lock lock;

    public ReentrantLockBeanchmark() {
        lock = new ReentrantLock(false);
    }

    public long getValue() {
        return count;
    }

    public void increment() {
        lock.lock();
        try {
            count++;
        }finally {
            lock.unlock();
        }
    }
}
