package reentrantrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yz on 2017/5/21.
 */
public class ReentrantLockTest {
    private Lock lock = new ReentrantLock();
    public void testMethod(){
        lock.lock();
        for (int i=0; i < 5; i++){
            System.out.println("ThreadName= " + Thread.currentThread().getName() + "   " + (i+1));
        }
        lock.unlock();
    }
}
