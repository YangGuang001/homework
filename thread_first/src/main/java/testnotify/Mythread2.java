package testnotify;

/**
 * Created by yz on 2017/5/21.
 */
public class Mythread2 extends Thread {
    private Object lock;

    public Mythread2(Object lock) {
        this.lock = lock;
    }

    public void run(){
        synchronized (lock){
            System.out.println("begin notify, time = " + System.currentTimeMillis());
            lock.notify();
            System.out.println("end notify, time = " + System.currentTimeMillis());
        }
        System.out.println("i want to execute!!!");
    }
}
