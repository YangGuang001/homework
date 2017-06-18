package com.thread.compare;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yz on 2017/6/18.
 */
public class MainUser {

    public static void main(String[] args) throws Exception{
        ConcurrentLinkedQueue<Task> linkedQueue = new ConcurrentLinkedQueue<Task>();
        Object object = new Object();
        Worker worker = new Worker(linkedQueue,object);
        new Thread(worker).start();
        TimeUnit.SECONDS.sleep(1);
        int index = 0;
        while (true){
            Task task = new Task(index++);
            linkedQueue.add(task);
            if (index%200 == 0){
                TimeUnit.SECONDS.sleep(1);
            }
        }

    }
}
