package myservice;

/**
 * Created by yz on 2017/5/18.
 */
public class Run {
    public static void main(String[] args) {
        Main main = new Main();
        Mythread mythread = new Mythread(main);
        mythread.start();
        Mythread1 mythread1 = new Mythread1(main);
        mythread1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
