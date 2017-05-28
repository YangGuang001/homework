package condition123;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yz on 2017/5/23.
 */
public class Run123 {
    private static ReentrantLock lock = new ReentrantLock();
    volatile private static int flag = 1;
    final private static Condition condition1 = lock.newCondition();
    final private static Condition condition2 = lock.newCondition();
    private static StringBuffer stringBuilder = new StringBuffer();
    public static void main(String[] args) {
        Thread thread1 = new Thread(){
            public void run(){
                try {
                    lock.lock();
                    while (flag != 1) {
                        condition1.await();
                    }
                    stringBuilder.append("A");
                    flag = 2;
                    condition2.signalAll();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        };

        Thread thread2 = new Thread(){
            public void run(){
                try {
                    lock.lock();
                    while (flag != 2) {
                        condition2.await();
                    }
                    stringBuilder.append("B");
                    flag = 1;
                    condition1.signalAll();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        };

//        Thread[] array1 = new Thread[5];
//        Thread[] array2 = new Thread[5];

        for (int i=0; i < 5; i++){
//            array1[i] = new Thread(thread1);
//            array2[i] = new Thread(thread2);
//
//            array1[i].start();
//            array2[i].start();
              new Thread(thread1).start();
              new Thread(thread2).start();

        }

        System.out.println(stringBuilder.toString());
    }
}
