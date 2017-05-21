package testWaitLong;

import sun.rmi.runtime.Log;

/**
 * Created by yz on 2017/5/21.
 */
public class MyRunnable {
    static private Object object = new Object();
    static long begin = 0L;
    static long end = 0L;
    static private Runnable runnable1 = new Runnable() {
        public void run() {
            synchronized (object){
                try{
                    begin = System.currentTimeMillis();
                    System.out.println("wait begin time = " + System.currentTimeMillis());
                    object.wait(5000);
                    System.out.println("wait end time = " + System.currentTimeMillis());
                    end = System.currentTimeMillis();
                    System.out.println("runtime = " + (end - begin)/1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }
    };

    static private Runnable runnable2 = new Runnable() {
        public void run() {
            synchronized (object){
                System.out.println("notify begin time = " + System.currentTimeMillis());
                object.notify();
                System.out.println("notify end time = " + System.currentTimeMillis());
            }
        }
    };

    public static void main(String[] args) throws Exception{
        Thread thread1 = new Thread(runnable1);
        thread1.start();
        Thread.sleep(2000);
        Thread thread2 = new Thread(runnable2);
        thread2.start();

        System.out.println("main end !!!!");
    }
}
