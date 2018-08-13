package com.yang.IOTest;

/**
 * Created by yz on 2018/7/22.
 */
public class VolateCount implements Runnable {
    private static volatile int count = 0;

    private static synchronized void inc() throws InterruptedException {
        Thread.sleep(1);
        count++;
    }

    public void run() {
        try {
            inc();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i=0; i<1000; i++) {
            new Thread(new VolateCount()).start();
        }

        Thread.sleep(4000);
        System.out.println(count);
    }
}
