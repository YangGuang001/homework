package testvolatile;

/**
 * Created by yz on 2017/5/20.
 */
public class Run {
    public static void main(String[] args) {
        RunTask task = new RunTask();
        task.start();
        try {
            Thread.sleep(1000);
            task.setRunning(false);
            System.out.println("线程已经停止!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
