package TestDaemon;

/**
 * Created by yz on 2017/5/29.
 */
public class TestDaemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new Daemon(),"Demo");
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ended !!!");
    }

    static class Daemon implements Runnable{
        public void run() {
            try {
                int i = 0;
                while (i < 100000) {
                    System.out.println("Demo is runing" + i++);
                }
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                System.out.println("this line is end !!!");
            }
        }
    }
}
