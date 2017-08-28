package com.yang.curator.test.lession1;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yz on 2017/8/25.
 */
public class AbstractZkRegistry {
    //zk配置文件
    private ZKConfig zkConfig;
    //zk
    private CuratorFramework curatorFramework;

    private ChildrenWatcher watcher = new ChildrenWatcher();

    public void init(ZKConfig zkConfig){
        this.zkConfig = zkConfig;
    }

    class ChildrenWatcher implements Watcher {

        public void process(WatchedEvent event) {

        }
    }

    public void start() throws Exception{
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(zkConfig.getZkUrl())
                .sessionTimeoutMs(zkConfig.getSessionTimout())
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        curatorFramework.start();
        doWork();
    }

    public List<String> getChildWithWatcher(String path, Watcher watcher) throws Exception {
        List<String> child = new ArrayList<String>();
        try {
            child = curatorFramework.getChildren().usingWatcher(watcher).forPath(zkConfig.getBaseDir());
        }catch (Exception e) {
            curatorFramework.getData().usingWatcher(watcher).forPath(zkConfig.getBaseDir());
        }
        return child;
    }

    public void doWork() throws Exception{
        Stat stat = curatorFramework.checkExists().forPath(zkConfig.getBaseDir());
        if (null == stat){
            curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath(zkConfig.getBaseDir());
        }
        List<String> child = getChildWithWatcher(zkConfig.getBaseDir(), watcher);

        if (!child.isEmpty()) {
            for (String path : child) {
                List<String> sons = getChildWithWatcher(zkConfig.getBaseDir() + "/" + path,watcher);
            }

        }


    }

}
