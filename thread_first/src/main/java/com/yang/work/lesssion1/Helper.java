package com.yang.work.lesssion1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yz on 2017/9/10.
 */
public class Helper {
    private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);

    private volatile boolean flag = true;

    Thread workThread = new Thread(new Runnable() {
        public void run() {
            String task = null;
            while (flag){
                try {
                    if (0 == queue.size()) {
                        TimeUnit.SECONDS.sleep(1);
                    }
                    task = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (task != null) {
                    doProcesss(task);
                }
            }
        }
    });

    public void init() {
        workThread.start();
    }

    public void stop() {
        flag = false;
    }

    public void submit(String task) {
        queue.add(task);
    }

    private void doProcesss(String task) {
        System.out.println(task + " processed!!!");
    }
}
