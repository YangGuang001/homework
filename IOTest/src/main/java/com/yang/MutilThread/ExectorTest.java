package com.yang.MutilThread;

import java.util.concurrent.*;

/**
 * Created by yz on 2018/8/4.
 */
public class ExectorTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 30, 5000, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<Runnable>(5000));
        for (int i=0; i < 20; i++)
        {
            System.out.println("before run:" + executor.getActiveCount());
            executor.execute(new Mytask(executor, i));
            System.out.println("after run:" + executor.getActiveCount());
        }

        TimeUnit.SECONDS.sleep(1000);

    }
}
