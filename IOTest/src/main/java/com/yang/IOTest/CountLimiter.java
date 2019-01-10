package com.yang.IOTest;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class CountLimiter {
    private static volatile AtomicInteger count = new AtomicInteger(0);

    private static Timer timer = new Timer(true);

    static {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("count has set 0");
                count.set(0);
            }
        }, 0,  1000);
    }

    public AtomicInteger getCount() {
        return count;
    }

    public boolean limiterExecutor(String msg) {
        if (count.get() < 1000) {
            count.incrementAndGet();
            System.out.println("发送消息:" + msg);
            return true;
        }
        return false;
    }
}
