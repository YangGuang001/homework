package Zookeeper_lession1;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by yz on 2017/6/2.
 */
public class Zookpeer_Create_ASync implements Watcher {
    private static String hostAndPort = "127.0.0.1:2181";
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception{
        ZooKeeper zooKeeper = new ZooKeeper(hostAndPort,
                5000,new Zookpeer_Create_ASync());
        System.out.println(zooKeeper.getState());
        try {
            latch.await();

            zooKeeper.create("/yang","123".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                    new IStringCallback(),"yangxinzhao");

            zooKeeper.create("/yang/xin","123".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                    new IStringCallback(),"yangxinzhao");

            TimeUnit.SECONDS.sleep(1);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("zookeeper session established.");
    }

    public void process(WatchedEvent watchedEvent) {
        if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()){
            latch.countDown();
        }
    }
}

class IStringCallback implements AsyncCallback.StringCallback{
    public void processResult(int i, String s, Object o, String s1) {
        System.out.println(i + " " + s + " " + o +" "+ s1);
    }
}

class DataCallBack implements AsyncCallback.DataCallback{
    public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {

    }
}