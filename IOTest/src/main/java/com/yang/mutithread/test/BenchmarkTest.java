package com.yang.mutithread.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by yz on 2018/7/23.
 */
public class BenchmarkTest {
    private Counter counter;

    private CyclicBarrier barrier;

    private int threadNum;

    public BenchmarkTest(Counter counter, int threadNum) {
        this.counter = counter;
        this.threadNum = threadNum;
        barrier = new CyclicBarrier(threadNum + 1);
    }

    public static void main(String[] args) {
        new BenchmarkTest(new ReentrantLockBeanchmark(), 500).test();
    }

    public void test() {
        for (int i=0; i < threadNum; i++) {
            new TestThread(counter).start();
        }
        long start = System.currentTimeMillis();
        try {
            barrier.await();
            long end = System.currentTimeMillis();
            System.out.println("count value :" + counter.getValue());
            System.out.println("use time: " + (end - start) + "ms" );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    class TestThread extends Thread {
        private Counter counter;

        public TestThread(final Counter counter) {
            this.counter = counter;
        }

        public void run() {
            try {
                for (int i=0; i<100; i++) {
                    counter.increment();
                }
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }
}
