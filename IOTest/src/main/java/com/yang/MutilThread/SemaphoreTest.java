package com.yang.MutilThread;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

@Slf4j
public class SemaphoreTest {

    private static int threadCount = 20;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i=0; i < threadCount; i++) {
            final int num = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    printNum(num);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }finally {
                    semaphore.release();
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
