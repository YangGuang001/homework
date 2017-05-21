package testnotify;

/**
 * Created by yz on 2017/5/21.
 */
public class Mythread1 extends Thread {
    private Object lock;

    public Mythread1(Object lock) {
        this.lock = lock;
    }

    public void run(){
        try {
            synchronized (lock){
                System.out.println("begin  time=" + System.currentTimeMillis());
                lock.wait();
                System.out.println("end time=" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
