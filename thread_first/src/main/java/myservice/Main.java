package myservice;

/**
 * Created by yz on 2017/5/18.
 */
public class Main {
    private int result = 10;
    private String string = new String();
    public void operateMethod(int a, int b){
        try {
            synchronized (string){
                result = a + b;
                Thread.sleep(100);
                System.out.println("Main result = " + result);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
