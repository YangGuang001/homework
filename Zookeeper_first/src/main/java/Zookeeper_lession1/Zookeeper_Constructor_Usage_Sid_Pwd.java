package Zookeeper_lession1;

import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by yz on 2017/6/2.
 */
public class Zookeeper_Constructor_Usage_Sid_Pwd implements Watcher{
    private static String hostAndPort = "127.0.0.1:2181";
    private static CountDownLatch latch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;
    public static void main(String[] args) throws Exception{
        String path = "/yang";
        zooKeeper = new ZooKeeper(hostAndPort,
                5000,new Zookeeper_Constructor_Usage_Sid_Pwd());
        System.out.println(zooKeeper.getState());
        try {
            latch.await();

//            String path1 = zooKeeper.create(path,"".getBytes(),
//                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            zooKeeper.create(path + "/xin", "".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);

            List<String> child = zooKeeper.getChildren(path,true);
            System.out.println(child);

            zooKeeper.create(path + "/zhao","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL);

            zooKeeper.create(path + "/zhao1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL);

            TimeUnit.SECONDS.sleep(1);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("zookeeper session established.");
    }

    public void process(WatchedEvent watchedEvent) {
        if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()){
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                latch.countDown();
            }else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    System.out.println("Reget child:" + zooKeeper.getChildren(watchedEvent.getPath(),true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
