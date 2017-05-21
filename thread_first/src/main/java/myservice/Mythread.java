package myservice;

/**
 * Created by yz on 2017/5/18.
 */
public class Mythread extends Thread{
    private Main main;

    public Mythread(Main main) {
        this.main = main;
    }

    @Override
    public void run(){
        main.operateMethod(2,3);
    }
}
