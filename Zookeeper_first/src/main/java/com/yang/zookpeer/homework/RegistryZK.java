package com.yang.zookpeer.homework;

import lombok.AllArgsConstructor;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yz on 2017/8/25.
 */
public class RegistryZK {
    private ZKConfig zkConfig;

    private ZooKeeper zooKeeper;

    private ZookpeerWorkerThread workerThread;

    private ConcurrentLinkedQueue<ZKComment> eventQueue = new ConcurrentLinkedQueue<ZKComment>();

    private ZookeeperWatcher parentWacher = new ZookeeperWatcher(ZKCommentType.ParentWatcherType);

    private ZookeeperWatcher childrenWatcher = new ZookeeperWatcher(ZKCommentType.ChildWatcherType);

    @AllArgsConstructor
    class ZookeeperWatcher implements Watcher{
        private ZKCommentType zkCommentType;
        public void process(WatchedEvent watchedEvent) {
            ZKComment zkComment = new ZKComment(zkCommentType, watchedEvent);
            eventQueue.add(zkComment);
        }
    }

    class ZookpeerWorkerThread extends Thread{
        volatile boolean alive = false;
        public void run(){
            while (alive){
                if (zooKeeper == null){
                    connect();
                }
                if (zooKeeper != null){
                    processQueue();
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void processQueue() {

        while (!eventQueue.isEmpty()){

            ZKComment zkComment = eventQueue.poll();
            switch (zkComment.getWatchedEvent().getType()){
                case NodeCreated:
                    System.out.println("Event Type:" + zkComment.getZkCommentType() +
                            "\t Watcher Type:" + zkComment.getWatchedEvent().getType() +
                            "\t Watcher Path:" + zkComment.getWatchedEvent().getPath());
                    onNodeCreated(zkComment.getZkCommentType(), zkComment.getWatchedEvent().getPath());
                    break;
                case NodeChildrenChanged:
                    System.out.println("Event Type:" + zkComment.getZkCommentType() +
                            "\t Watcher Type:" + zkComment.getWatchedEvent().getType() +
                            "\t Watcher Path:" + zkComment.getWatchedEvent().getPath());
                    onNodeChildrenChanged(zkComment.getZkCommentType(), zkComment.getWatchedEvent().getPath());
                    break;
                default:
                    System.out.println("Event Type:" + zkComment.getZkCommentType() +
                            "\t Watcher Type:" + zkComment.getWatchedEvent().getType() +
                            "\t Watcher Path:" + zkComment.getWatchedEvent().getPath());
            }

        }
    }

    private void onNodeChildrenChanged(ZKCommentType zkCommentType, String path) {
        if (ZKCommentType.ParentWatcherType == zkCommentType){
            getChildWithWatcher(zkConfig.getRootDir(), parentWacher);
        } else {
//            List<String> list = getChildWithWatcher(zkConfig.getRootDir() + "/" + path, childrenWatcher);
//            GrayCache.initData(path, list);
        }
    }

    private void onNodeCreated(ZKCommentType zkCommentType, String path) {
        if (ZKCommentType.ParentWatcherType == zkCommentType){
            getChildWithWatcher(path, parentWacher);
        } else {
            List<String> list = getChildWithWatcher(zkConfig.getRootDir() + "/" + path, childrenWatcher);
            GrayCache.initData(path, list);
        }
    }

    private void connect(){
        final CountDownLatch latch = new CountDownLatch(1);
        try {
            zooKeeper = new ZooKeeper(zkConfig.getZkUrl(), zkConfig.getSessionTimeout(), new Watcher() {
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected)
                    latch.countDown();
                }
            });
            latch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Stat stat = null;
        try {
            stat = zooKeeper.exists(zkConfig.getRootDir(), null);
            if (stat == null){
                zooKeeper.create(zkConfig.getRootDir(),"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> list = getChildWithWatcher(zkConfig.getRootDir(), parentWacher);
        for (String taskId : list){
            List<String> ips = getChildWithWatcher(zkConfig.getRootDir() + "/" + taskId, childrenWatcher);
            if (!ips.isEmpty()){
                GrayCache.initData(taskId, ips);
            }
        }
    }

    public List<String> getChildWithWatcher(String path, Watcher watcher){
        List<String> childs = new ArrayList<String>();
        try {
            Stat stat = zooKeeper.exists(path, null);
            if (stat != null){
                childs = zooKeeper.getChildren(path, watcher);
            }
        } catch (KeeperException.NoNodeException e){
//            try {
//                zooKeeper.getData(path, watcher, null);
//            } catch (Exception e1){
//                e1.printStackTrace();
//            }
        }
        catch (Exception e1){
              e1.printStackTrace();
        }
        return childs;
    }

    public void init(ZKConfig zkConfig){
        this.zkConfig = zkConfig;
    }

    public void start(){
        workerThread = new ZookpeerWorkerThread();
        workerThread.setName("demo");
        workerThread.alive = true;
        workerThread.start();
    }

    public void shutdown(){
        workerThread.alive = false;
    }
}
