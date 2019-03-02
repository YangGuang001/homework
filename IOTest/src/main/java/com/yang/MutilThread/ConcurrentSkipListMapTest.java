package com.yang.MutilThread;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

@Slf4j
public class ConcurrentSkipListMapTest {
    //请求总数
    public static int clientTotal = 5000;
    //线程编发数
    public static int threadTotal = 200;
    //计数器
    public static int count = 0;

    private static Map<Integer, Integer> map = new ConcurrentSkipListMap<Integer, Integer>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        Semaphore semaphore = new Semaphore(threadTotal);
        for (int i=0; i < clientTotal; i++) {
            final int num = i;
            executors.execute(() -> {
                try {
                    semaphore.acquire();
                    add(num);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception: " + e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("total count: " + map.size());

        Set<Integer> set = map.keySet();
        set.forEach(num -> {
            log.info("the num is " + num);
        });

        executors.shutdown();
    }

    public static void add(int i) {
        map.put(i,i);
    }


}
