package ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yz on 2017/5/30.
 */
public class ConnectionPoolTest {
    static ConnectionPool pool = new ConnectionPool(10);
    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end;

    public static void main(String[] args) {
        int threadCount = 100;
        int count = 1000;

        end = new CountDownLatch(threadCount);
        AtomicInteger got = new AtomicInteger();
        AtomicInteger noGot = new AtomicInteger();

        for (int i=0; i < threadCount; i++){
            Thread thread = new Thread(new ConnectionRunner(count,got,noGot),"TestThread");
            thread.start();
        }
        start.countDown();
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection: " + noGot);
    }

    static class ConnectionRunner implements Runnable{
        private int count;
        private AtomicInteger got;
        private AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        public void run() {
            try {
                start.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            while (count > 0){
                try {
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null){
                        try {
                            connection.createStatement();
                            connection.commit();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }finally {
                            got.incrementAndGet();
                            pool.releaseConnection(connection);
                        }
                    }else {
                        notGot.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count--;
            }

            end.countDown();
        }
    }
}
