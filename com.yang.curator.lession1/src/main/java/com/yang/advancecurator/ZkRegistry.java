package com.yang.advancecurator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by yz on 2017/8/27.
 */
public class ZkRegistry {
    private String zkUrl = "127.0.0.1:2181";
    private int sessionTimeout = 30000;
    private CuratorFramework client;
    private String path = "/gray";
    private volatile boolean isRun = true;
    private CacheListener parentListener = new CacheListener(EventType.Parent);
    private CacheListener childListener = new CacheListener(EventType.Child);
    private ConcurrentLinkedQueue<ListernerEvent> queue = new ConcurrentLinkedQueue<ListernerEvent>();

    public void init(){
        client = CuratorFrameworkFactory.builder()
                .connectString(zkUrl).sessionTimeoutMs(sessionTimeout)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();
    }

    private void processEventQueue() {
        while (!queue.isEmpty()){
            ListernerEvent event = queue.poll();
            processChild(event.getType(), event);
        }
    }

    public void processChild(EventType eventType, ListernerEvent event){
        switch (event.getCacheEventType()){
            case CHILD_ADDED:
                System.out.println(
                        "EventType:" + event.getCacheEventType() +
                                "\tevent type:" + event.getType() +
                                "\tevent path:" + event.getCacheEvent().getData().getPath());
                onChildAdd(eventType,event);
                break;
            case CHILD_REMOVED:
                onChildRemove(eventType,event);
                System.out.println(
                        "EventType:" + event.getCacheEventType() +
                                "\tevent type:" + event.getType() +
                                "\tevent path:" + event.getCacheEvent().getData().getPath());
                break;
            case CHILD_UPDATED:
                onChildUpdate(eventType,event);
                System.out.println(
                        "EventType:" + event.getCacheEventType() +
                                "\tevent type:" + event.getType() +
                                "\tevent path:" + event.getCacheEvent().getData().getPath());
                break;
            default:
                System.out.println(
                        "EventType:" + event.getCacheEventType() +
                        "\tevent type:" + event.getType() +
                        "\tevent path:" + event.getCacheEvent().getData().getPath());
        }
    }

    private void onChildUpdate(EventType eventType, ListernerEvent event) {
        if (EventType.Child == eventType){
            //GrayCache.upDataTaskId(event.getCacheEvent().getData().getPath());
        }
    }

    private void onChildRemove(EventType eventType, ListernerEvent event) {
        if (EventType.Child == eventType){
            //GrayCache.removeTaskId(event.getCacheEvent().getData().getPath());
        }
    }

    private void onChildAdd(EventType eventType, ListernerEvent event) {
        if (EventType.Parent == eventType){
            String fullPath = event.getCacheEvent().getData().getPath();
            try {
                PathChildrenCache listener = new PathChildrenCache(client, fullPath, true);
                listener.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
                listener.getListenable().addListener(childListener);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            GrayCache.addTaskId(event.getCacheEvent().getData().getPath());
        }
    }


    @Data
    @AllArgsConstructor
    class CacheListener implements PathChildrenCacheListener {
        private EventType type;
        public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
            switch (pathChildrenCacheEvent.getType()){
                case CHILD_ADDED:
                case CHILD_UPDATED:
                case CHILD_REMOVED:
                    System.out.println(
                            "CacheEventType :" + type +
                                    "\tEvent type:" + pathChildrenCacheEvent.getType() +
                                    "\tEvent path:" + pathChildrenCacheEvent.getData().getPath());
                    ListernerEvent lister = new ListernerEvent(type, pathChildrenCacheEvent, curatorFramework, pathChildrenCacheEvent.getType());
                    queue.add(lister);
                    break;
                default:
                    System.out.println(
                            "CacheEventType :" + type +
                            "\tEvent type:" + pathChildrenCacheEvent.getType() +
                             "\tEvent path:" + pathChildrenCacheEvent.getData().getPath());
            }
        }
    }

    public void start() throws Exception {
        init();
        Thread ZkWorkThread = new Thread(new Runnable() {
            public void run() {
                while (isRun){
                    try {
                        if (!queue.isEmpty()){
                            processEventQueue();
                        }
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //获取初始化数据
        Stat stat = client.checkExists().forPath(path);
        if (stat != null){
           List<String> childs  = client.getChildren().forPath(path);
           if (!childs.isEmpty()){
               for (String taskId : childs){
                   //添加二级目录的listener
                   String fullth = path + "/" + taskId;
                   PathChildrenCache childrenCache = new PathChildrenCache(client, fullth, true);
                   childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
                   childrenCache.getListenable().addListener(childListener);
                   List<String> sons = client.getChildren().forPath(fullth);
                   if (!sons.isEmpty()){
                       GrayCache.initData(taskId, sons);
                   }
               }
           }
        } else {
            client.create().withMode(CreateMode.PERSISTENT).forPath(path);
        }
        //添加根目录监听
        PathChildrenCache childrenCache = new PathChildrenCache(client, path, true);
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        childrenCache.getListenable().addListener(parentListener);

        ZkWorkThread.start();
    }



    public void destory()
    {
        this.isRun = false;
    }
}
