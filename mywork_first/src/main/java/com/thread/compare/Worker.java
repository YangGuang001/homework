package com.thread.compare;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by yz on 2017/6/18.
 */

public class Worker implements Runnable {
    private volatile ConcurrentLinkedQueue<Task> linkedQueue;
    private Object object;
    private static final Statistics statistics = new Statistics();

    public Worker(ConcurrentLinkedQueue<Task> linkedQueue, Object object) {
        this.linkedQueue = linkedQueue;
        this.object = object;
        statistics.start();
    }

    public void run() {
        if (linkedQueue.isEmpty()){
            return;
        }
        Task task = linkedQueue.poll();
        task.computer();
        statistics.setCurCount(statistics.getCurCount() + 1);
    }
}
