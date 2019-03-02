package com.yang.MutilThread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ReentranWriteReadLock {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.readLock().lock();
                log.info("get read lock2");
                latch.countDown();
            }
        });
        thread.start();
        latch.await();
        readWriteLock.writeLock().lock();
        log.info("get read lock1");
        readWriteLock.readLock().unlock();

    }

}
