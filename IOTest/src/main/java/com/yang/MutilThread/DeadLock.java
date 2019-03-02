package com.yang.MutilThread;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DeadLock implements Runnable {

    private boolean flag = true;
    //object1
    private static Object object1 = new Object();
    //object2
    private static Object object2 = new Object();

    @Override
    public void run() {
        log.info("flag {}", flag);
        if (flag) {
            synchronized (object1) {
                log.info("enter into object1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    log.info("enter into object1.object2");
                }
            }
        } else {
            synchronized (object2) {
                log.info("enter into object2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    log.info("enter into object2.object1");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock1 = new DeadLock();
        DeadLock deadLock2 = new DeadLock();
        deadLock2.flag = false;

        new Thread(deadLock1).start();
        new Thread(deadLock2).start();
        log.info("begin deadlock check");
        DeadLockChecker.check();
    }

    static class DeadLockChecker {
        private final static ThreadMXBean mbean = ManagementFactory.getThreadMXBean();

        public static void check() {
            while (true) {
                long[] deadlockedThreadIds = mbean.findDeadlockedThreads();
                if (deadlockedThreadIds != null) {;
                    ThreadInfo[] threadInfos = mbean.getThreadInfo(deadlockedThreadIds);
                    for (Thread t: Thread.getAllStackTraces().keySet()) {
                        for (int i=0; i < threadInfos.length; i++) {
                            if (t.getId() == threadInfos[i].getThreadId()) {
                                log.error("deadlock name:" + t.getName());
                                //System.out.println(t.getName());
                                t.interrupt();
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
