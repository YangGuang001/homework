package com.yang.MutilThread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class LockSupportTest {


    public static void main(String[] args) {
        log.info("lock support before");

        final Thread currentThread = Thread.currentThread();

        Thread thread1 = new Thread(() -> {
            log.info("lock support before");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(currentThread);
        });

        thread1.start();

        LockSupport.park(Thread.currentThread());

        log.info("lock support after");
    }
}
