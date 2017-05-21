package testvolatile;

/**
 * Created by yz on 2017/5/20.
 */
public class RunTask extends Thread{
    volatile private static boolean isRunning = true;
    volatile private static int count = 0;
    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    synchronized private static void addCount(){
        while (isRunning){
            System.out.println(count++);
        }
        System.out.println("线程已经停止!!!");
    }

    public void run(){
        addCount();
    }
}
