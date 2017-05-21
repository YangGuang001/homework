package myservice;

/**
 * Created by yz on 2017/5/19.
 */
public class Mythread1 extends Thread{
    private Main main;

    public Mythread1(Main main) {
        this.main = main;
    }

    public void run(){
        main.operateMethod(4,5);
    }
}
