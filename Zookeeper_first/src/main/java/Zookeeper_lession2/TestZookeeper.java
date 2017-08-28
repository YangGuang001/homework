package Zookeeper_lession2;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yz on 2017/8/25.
 */
public class TestZookeeper implements Watcher{
    private static CountDownLatch latch = new CountDownLatch(1);
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected){
            if (watchedEvent.getType() == Event.EventType.None){
                if (latch.getCount() != 0){
                    latch.countDown();
                }
            }
        }
        System.out.println( "event type:" + watchedEvent.getType() +
                            "event path:" + watchedEvent.getPath() +
                            "event stat:" + watchedEvent.getState());
    }

    public static void main(String[] args) {
        String rootPath = "/222";
        ZooKeeper zooKeeper = null;
        Stat statParent ;
        try {
            zooKeeper = new ZooKeeper("127.0.0.1", 50000, new TestZookeeper());
            latch.await();
            if (null == zooKeeper.exists(rootPath, false)){
                zooKeeper.create(rootPath,"yang".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            zooKeeper.getChildren(rootPath, new TestZookeeper());
            System.out.println(zooKeeper.getData(rootPath,new TestZookeeper(), null));
            zooKeeper.create(rootPath + "/yang","yang".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.create(rootPath + "/yang/hehehe", "hehehe".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }


    }
}
