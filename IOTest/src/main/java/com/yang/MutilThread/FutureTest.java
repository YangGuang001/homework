package com.yang.MutilThread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureTest {

    static class MyTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            log.info("do something");
            TimeUnit.SECONDS.sleep(5);
            return "Done";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyTask());
        log.info("do something");
        TimeUnit.SECONDS.sleep(2);
        log.info("result: " + future.get());
        executorService.shutdown();
    }

}
