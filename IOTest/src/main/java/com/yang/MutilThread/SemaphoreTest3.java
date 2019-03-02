package com.yang.MutilThread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreTest3 {

    private static int threadCount = 20;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i=0; i < threadCount; i++) {
            final int num = i;
            executorService.execute(()->{
                try {
                    if (semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)) {
                        printNum(num);
                        semaphore.release();
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            });
        }

        executorService.shutdown();
    }

    public static void printNum(int num) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("get num: " + num);
    }

}
