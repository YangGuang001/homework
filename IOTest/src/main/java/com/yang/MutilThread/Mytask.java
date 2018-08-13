package com.yang.MutilThread;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yz on 2018/8/4.
 */
public class Mytask implements Runnable {
    private ThreadPoolExecutor executor;

    private int taskIndex;

    public Mytask(ThreadPoolExecutor executor, int taskIndex) {
        this.executor = executor;
        this.taskIndex = taskIndex;
    }

    public void run() {
        System.out.println("runing: " + executor.getActiveCount());
        System.out.println("task index: " + taskIndex);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish the work");
    }
}
