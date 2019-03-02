package com.yang.MutilThread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ForEvenerRunningTest {
    volatile static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;) {
                    if (flag) {
                        return;
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("running this thread");
            }
        }).start();

        TimeUnit.SECONDS.sleep(10);
        flag = true;
        log.info("the thread should be shutdown");
    }

}
