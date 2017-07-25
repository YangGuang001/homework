package com.yang.MutilThread;

/**
 * Created by yz on 2017/7/22.
 */
public class ThreadLocalTest implements Sequence{
    private static MyThreadLocal<Integer> threadLocal = new MyThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    public int getNumber() {
        threadLocal.set(threadLocal.get() + 1);;
        return threadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();

        Thread thread1 = new ClientThread(threadLocalTest);
        Thread thread2 = new ClientThread(threadLocalTest);
        Thread thread3 = new ClientThread(threadLocalTest);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class ClientThread extends Thread {
    private ThreadLocalTest threadLocalTest;

    public ClientThread(ThreadLocalTest threadLocalTest){
        this.threadLocalTest = threadLocalTest;
    }

    @Override
    public void run() {
        for (int i=0; i < 3; i++){
            System.out.println(Thread.currentThread().getName() + "+>"
            + threadLocalTest.getNumber());
        }
    }
}
