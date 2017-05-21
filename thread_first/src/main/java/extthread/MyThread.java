package extthread;

/**
 * Created by yz on 2017/5/17.
 */
public class MyThread extends Thread {
    public MyThread() {
        System.out.println("Thread name=" + Thread.currentThread().getName());
    }

    private int i = 5;
    public void run(){
        System.out.println("i= " + (i--) + "Thread name=" + Thread.currentThread().getName());
    }
}
