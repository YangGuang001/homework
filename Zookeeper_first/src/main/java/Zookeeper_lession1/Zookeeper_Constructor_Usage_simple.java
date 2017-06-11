package Zookeeper_lession1;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yz on 2017/6/2.
 */
public class Zookeeper_Constructor_Usage_simple implements Watcher{
    private static String hostAndPort = "127.0.0.1:2181";
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception{
        ZooKeeper zooKeeper = new ZooKeeper(hostAndPort,
                5000,new Zookeeper_Constructor_Usage_simple());
        System.out.println(zooKeeper.getState());
        try {
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("zookeeper session established.");
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event: " + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()){
            latch.countDown();
        }
    }
}
