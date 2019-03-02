package com.yang.MutilThread;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class CopyOnWriteArrayListTest {
    //请求总数
    public static int clientTotal = 5000;
    //线程编发数
    public static int threadTotal = 200;
    //计数器
    public static int count = 0;

    private static List<Integer> list = new CopyOnWriteArrayList<>();

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
        log.info("total count: " + list.size());
        executors.shutdown();
    }

    public static void add(int i) {
        list.add(i);
    }


}
