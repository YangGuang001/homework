package TestThreadPriorities;

/**
 * Created by yz on 2017/5/29.
 */
public class ThreadState {
    public static void main(String[] args) {
        new Thread(new TimeWaiting(),"TimeWaitingThread").start();
        new Thread(new Waiting(),"WaitingThread").start();
        new Thread(new Blocked(),"BlockedThread_01").start();
        new Thread(new Blocked(),"BlockedThread_02").start();
    }

    static class TimeWaiting implements Runnable{
        public void run(){
            while (true){
                SleepUnitl.SleepSecond(200);
            }
        }
    }

    static class Waiting implements Runnable{
        public void run(){
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable{
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    SleepUnitl.SleepSecond(200);
                }
            }
        }
    }
}
