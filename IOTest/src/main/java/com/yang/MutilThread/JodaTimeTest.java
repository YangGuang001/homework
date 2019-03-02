package com.yang.MutilThread;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.format.datetime.joda.JodaDateTimeFormatAnnotationFormatterFactory;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.joda.time.format.DateTimeFormatter;

@Slf4j
public class JodaTimeTest {
    //请求总数
    public static int clientTotal = 5000;
    //线程编发数
    public static int threadTotal = 200;
    //计数器
    public static int count = 0;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");

    //private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

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
        executors.shutdown();
    }

    public static void add(int i) {
        try {
            log.info("{}, {}", i, DateTime.parse("20190208", dateTimeFormatter));
        } catch (Exception e) {
            log.error("exception: " + e);
        }
    }


}
