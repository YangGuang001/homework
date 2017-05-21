package reentrantrantLock;

/**
 * Created by yz on 2017/5/21.
 */
public class Run {
    public static void main(String[] args) {
        ReentrantLockTest lockTest = new ReentrantLockTest();
        MyThread thread1 = new MyThread(lockTest);
        MyThread thread2 = new MyThread(lockTest);
        MyThread thread3 = new MyThread(lockTest);
        MyThread thread4 = new MyThread(lockTest);
        MyThread thread5 = new MyThread(lockTest);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}
