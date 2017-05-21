package testnotify;

/**
 * Created by yz on 2017/5/21.
 */
public class Run {
    public static void main(String[] args) {
        try{
            Object object = new Object();
            Mythread1 mythread1 = new Mythread1(object);
            mythread1.start();
            Thread.sleep(1000);
            Mythread2 mythread12 = new Mythread2(object);
            mythread12.start();

        }catch (InterruptedException e){
            e.printStackTrace();
        }


    }
}
