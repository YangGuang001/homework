package com.yang.curator.test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Th;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yz on 2017/8/26.
 */
public class CuratorTest {
    private CuratorFramework client = null;

    private String zkUrl = "localhost:2181";

    private String rootPath = "/good";

    @Given("创建curator客户端")
    public void createZKClient(){
        client = CuratorFrameworkFactory.builder()
                .connectString(zkUrl).retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();
    }

    @When("增删改查一些基本操作")
    public void testNormalOperation() throws Exception {
        //检查接口是否存在
        Stat stat1 = client.checkExists().forPath(rootPath);
        if (stat1 == null){
            //创建节点
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                    .forPath(rootPath, "yang".getBytes());
        }

        //获取节点内容
        Stat stat = new Stat();
        System.out.println(new String(client.getData().storingStatIn(stat).forPath(rootPath)));

        client.setData().forPath(rootPath,"xin".getBytes());

        System.out.println(new String(client.getData().storingStatIn(stat).forPath(rootPath)));

        client.delete().deletingChildrenIfNeeded().forPath(rootPath);
    }

    @When("异步回调增删改查一些基本操作")
    public void BackGroundTest() throws Exception {

        final CountDownLatch latch = new CountDownLatch(2);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                        System.out.println("event code: " + event.getResultCode()
                        + "event type:" + event.getType()
                        + "event path:" + event.getPath()
                        + "event data:" + new String(event.getData()));
                        System.out.println("Thread Name: " + Thread.currentThread().getName());
                        latch.countDown();
                    }
                }, executor).forPath(rootPath, "yang".getBytes());



        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                        System.out.println("event code: " + event.getResultCode()
                                + "event type:" + event.getType()
                                + "event path:" + event.getPath()
                                + "event data:" + new String(event.getData()));
                        System.out.println("Thread Name: " + Thread.currentThread().getName());
                        latch.countDown();
                    }
                }).forPath(rootPath, "yang".getBytes());

        latch.await();
        executor.shutdown();
    }

    @When("添加事件监听获取监听数据")
    public void addPathChilrenCache() throws Exception {
        final PathChildrenCache childrenCache = new PathChildrenCache(client, rootPath, true);
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                switch (pathChildrenCacheEvent.getType()){
                    case CHILD_ADDED:
                        System.out.println("child_add:" + pathChildrenCacheEvent.getData().getPath() +
                                            "child_data:" + new String(pathChildrenCacheEvent.getData().getData()));
                        break;
                    case CHILD_REMOVED:
                        System.out.println("child_remove:" + pathChildrenCacheEvent.getData().getPath() +
                                "child_data:" + new String(pathChildrenCacheEvent.getData().getData()));
                        break;
                    case CHILD_UPDATED:
                        System.out.println("child_remove:" + pathChildrenCacheEvent.getData().getPath() +
                                "child_data:" + new String(pathChildrenCacheEvent.getData().getData()));
                        break;
                    default:
                        System.out.println(pathChildrenCacheEvent.getType());
                }
            }
        });
        client.create().withMode(CreateMode.PERSISTENT).forPath(rootPath);
        Thread.sleep(10);
        client.create().withMode(CreateMode.PERSISTENT).forPath(rootPath+ "/test");
        Thread.sleep(10);
        client.create().withMode(CreateMode.PERSISTENT).forPath(rootPath+ "/test" + "/test1");
        Thread.sleep(10);
        client.delete().forPath(rootPath+ "/test" + "/test1");
        Thread.sleep(10);
        client.delete().forPath(rootPath + "/test");
        Thread.sleep(10);
        client.delete().forPath(rootPath);
    }

    @Then("关闭zookeeper")
    public void closeZK(){
        client.close();
    }
}
