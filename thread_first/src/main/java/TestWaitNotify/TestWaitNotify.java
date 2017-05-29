package TestWaitNotify;

import TestThreadPriorities.SleepUnitl;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yz on 2017/5/29.
 */
public class TestWaitNotify {
    static volatile boolean flag = true;
    static Object lock = new Object();
    public static void main(String[] args) {
        Thread thread = new Thread(new Wait(),"waitThread");
        thread.start();
        SleepUnitl.SleepSecond(5);
        Thread thread1 = new Thread(new Notify(),"NotifyThread");
        thread1.start();
    }

    static class Wait implements Runnable{
        public void run() {
            synchronized (lock){
                while (flag){
                    try {
                        System.out.println(Thread.currentThread() +
                                "flag is true,wait@" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() +
                        "flag is false,wait@" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable{
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread() +
                        "hold lock. notify@" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUnitl.SleepSecond(5);
            }

            synchronized (lock){
                System.out.println(Thread.currentThread() +
                        "hold lock again. notify@" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUnitl.SleepSecond(5);
            }
        }
    }
}
