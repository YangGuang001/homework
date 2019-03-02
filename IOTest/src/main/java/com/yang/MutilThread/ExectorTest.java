package com.yang.MutilThread;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * Created by yz on 2018/8/4.
 */
@Slf4j
public class ExectorTest {
    public static void main(String[] args) throws InterruptedException {
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 30, 5000, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<Runnable>(5000));
//        for (int i=0; i < 20; i++)
//        {
//            System.out.println("before run:" + executor.getActiveCount());
//            executor.execute(new Mytask(executor, i));
//            System.out.println("after run:" + executor.getActiveCount());
//        }
//
//        TimeUnit.SECONDS.sleep(1000);

        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("call futureTask");
                //TimeUnit.SECONDS.sleep(1);
                return "Done";
            }
        });

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        //executorService.schedule(futureTask, 1, TimeUnit.SECONDS);
        //executorService.scheduleAtFixedRate(futureTask, 1, 3,  TimeUnit.SECONDS);

        //executorService.scheduleWithFixedDelay(futureTask, 1000, 1000, TimeUnit.MILLISECONDS);

//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    if (futureTask.isDone()) {
//
//                        log.info("get result: {} and ScheduledExecutorService queue size {}" , futureTask.get());
//                    }
//                } catch (Exception e) {
//                    log.error(e.getMessage());
//                }
//            }
//        }, 1, 2, TimeUnit.SECONDS);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("test");
            }
        }, new Date(), 5*1000);

        log.info("execute finish");
    }
}
