package com.yang.MutilThread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@Slf4j
public class FutureTaskTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something");
                TimeUnit.SECONDS.sleep(5);
                return "Done";
            }
        });

        new Thread(futureTask).start();

        log.info("do something");
        TimeUnit.SECONDS.sleep(2);
        log.info("result: " + futureTask.get());
    }
}
