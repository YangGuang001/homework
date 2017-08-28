package com.yang.curator.test;

import com.yang.advancecurator.GrayCache;
import com.yang.advancecurator.ZkRegistry;
import com.yang.curator.test.lession1.ZKConfig;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Assert;

public class GrayZKRigistryTest {
    private CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
            .connectString("localhost:2181").sessionTimeoutMs(30000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();
    private ZkRegistry zkRegistry;
    @Given("启动zkregistry获取数据")
    public void startZkRegistry(){
        zkRegistry = new ZkRegistry();
        try {
            zkRegistry.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @When("不断增加数据")
    public void addData() throws Exception{
        curatorFramework.start();
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/gray/default/10.10.10.10");
        Thread.sleep(1000);
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/gray/default/20.20.20.20");
        Thread.sleep(1000);
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/gray/task/20.20.20.20");
        Thread.sleep(1000);
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/gray/task/10.10.10.10");
        Thread.sleep(1000);
    }

    @Then("验证结果")
    public void VerifyResult(){
        Assert.assertEquals(2, GrayCache.hashMap.size());
        Assert.assertEquals(2, GrayCache.hashMap.keySet().size());
        Assert.assertEquals(2, GrayCache.hashMap.values().size());
        zkRegistry.destory();
    }
}
