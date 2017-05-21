package Main;

import extthread.MyThread;

public class Run {
    public static void main(String[] args) {
        MyThread run = new MyThread();
        run.start();
        Thread t1 = new Thread(run);
        t1.start();
        t1.run();
    }
}
