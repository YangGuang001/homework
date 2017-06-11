package Zookeeper_lession1;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by yz on 2017/6/3.
 */
public class SetData_API_Async_Usage implements Watcher{
    private static CountDownLatch latch = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static String host = "127.0.0.1:2181";
    public static void main(String[] args) throws Exception{
        String path = "/book";
        zk = new ZooKeeper(host,5000,new SetData_API_Async_Usage());
        latch.await();

        zk.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        zk.setData(path,"456".getBytes(),-1,new IStatCallback(),null);

        TimeUnit.SECONDS.sleep(1);
    }



    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()){
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                latch.countDown();
            }
        }
    }
}

class IStatCallback implements AsyncCallback.StatCallback{
    public void processResult(int i, String s, Object o, Stat stat) {
        if (i == 0){
            System.out.println("SUCCESS!!!");
        }
    }
}
