package reentrantrantLock;

/**
 * Created by yz on 2017/5/21.
 */
public class MyThread extends Thread {
    private ReentrantLockTest lockTest;

    public MyThread(ReentrantLockTest lockTest) {
        this.lockTest = lockTest;
    }

    public void run(){
        lockTest.testMethod();
    }
}
