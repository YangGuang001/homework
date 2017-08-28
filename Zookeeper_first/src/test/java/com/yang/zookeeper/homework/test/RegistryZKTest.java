package com.yang.zookeeper.homework.test;

import com.yang.zookpeer.homework.GrayCache;
import com.yang.zookpeer.homework.ZookpeerFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.zookeeper.*;
import org.junit.Assert;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yz on 2017/8/27.
 */
public class RegistryZKTest {
    private ZooKeeper client;
    private String path = "/root";
    @Given("启动zkregistry获取数据")
    public void startZkRegistry() throws Exception{
        ZookpeerFactory.init();
        ZookpeerFactory.start();
        final CountDownLatch latch = new CountDownLatch(1);
        client = new ZooKeeper("localhost:2181", 50000, new Watcher() {
            public void process(WatchedEvent event) {
                if (Event.KeeperState.SyncConnected == event.getState()){
                    latch.countDown();
                }
            }
        });
        latch.await();
    }

    @When("不断增加数据")
    public void addData() throws Exception{
        if (null == client.exists(path, null)){
            client.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }
        Thread.sleep(1000);
        if (null == client.exists(path + "/default", null)){
            client.create(path + "/default", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }
        Thread.sleep(1000);
        client.create(path + "/default" + "/10.10.10.10", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        Thread.sleep(1000);
        if (null == client.exists(path + "/taskId", null)){
            client.create(path + "/taskId", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }
        Thread.sleep(1000);
        client.create(path + "/taskId" + "/10.10.10.10", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        Thread.sleep(1000);
        client.delete(path + "/taskId" + "/10.10.10.10",-1);
        client.delete(path + "/default" + "/10.10.10.10",-1);
        client.delete(path + "/default", -1);
        client.delete(path + "/taskId", -1);
        client.delete(path, -1);;
    }

    @Then("验证结果")
    public void VerifyResult(){
        Assert.assertEquals(2, GrayCache.hashMap.size());
        Assert.assertEquals(2, GrayCache.hashMap.keySet().size());
        Assert.assertEquals(1, GrayCache.hashMap.values().size());
        ZookpeerFactory.stop();
    }
}

