package com.yang.hystrix.demo;

import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by yz on 2018/2/25.
 */
public class Client {
    public static void main(String[] args) {
        String result = (String) new HelloHystrixCommand("YXZ").execute();
        System.out.println(result);
    }

    @Test
    public void testQueue() throws Exception {
        // queue()是异步非堵塞性执行：直接返回，同时创建一个线程运行HelloWorldHystrixCommand.run()
        // 一个对象只能queue()一次
        // queue()事实上是toObservable().toBlocking().toFuture()
        Future<String> future = new HelloHystrixCommand("YXZ").queue();

        // 使用future时会堵塞，必须等待HelloWorldHystrixCommand.run()执行完返回
        String queueResult = future.get(10000, TimeUnit.MILLISECONDS);
        System.out.println("queue异步结果：" + queueResult);
        assertEquals("Hello", queueResult.substring(0, 5));
    }

    @Test
    public void testObservable() throws IOException {
        // observe()是异步非堵塞性执行，同queue
        Observable<String> hotObservable = new HelloHystrixCommand("YXZ").observe();

        // single()是堵塞的
        System.out.println("hotObservable single结果：" + hotObservable.toBlocking().single());

        // 注册观察者事件
        // subscribe()是非堵塞的
        hotObservable.subscribe(new Observer<String>() {

            // 先执行onNext再执行onCompleted
            // @Override
            public void onCompleted() {
                System.out.println("hotObservable completed");
            }

            // @Override
            public void onError(Throwable e) {
                System.out.println("the method throw error");
                e.printStackTrace();
            }

            public void onNext(String v) {
                System.out.println("hotObservable onNext: " + v);
            }

        });

        // 非堵塞
        // - also verbose anonymous inner-class
        // - ignore errors and onCompleted signal
        hotObservable.subscribe(new Action1<String>() {

            // 相当于上面的onNext()
            // @Override
            public void call(String v) {
                System.out.println("hotObservable call: " + v);
            }

        });

        // 主线程不直接退出，在此一直等待其他线程执行
        System.in.read();
    }

    @Test
    public void testToObserable() throws Exception {
        // toObservable()是异步非堵塞性执行，同queue
        Observable<String> coldObservable = new HelloHystrixCommand("Hlx").toObservable();

        // single()是堵塞的
		//System.out.println("coldObservable single结果：" + coldObservable.toBlocking().single());

        // 注册观察者事件
        // subscribe()是非堵塞的
        // - this is a verbose anonymous inner-class approach and doesn't do assertions
        coldObservable.subscribe(new Observer<String>() {

            // 先执行onNext再执行onCompleted
            // @Override
            public void onCompleted() {
                System.out.println("coldObservable completed");
            }

            // @Override
            public void onError(Throwable e) {
                System.out.println("coldObservable error");
                e.printStackTrace();
            }

            // @Override
            public void onNext(String v) {
                System.out.println("coldObservable onNext: " + v);
            }

        });

        // 非堵塞
        // - also verbose anonymous inner-class
        // - ignore errors and onCompleted signal
//		coldObservable.subscribe(new Action1<String>() {
//
//			// 相当于上面的onNext()
//			// @Override
//			public void call(String v) {
//				System.out.println("coldObservable call: " + v);
//			}
//
//		});

        // 主线程不直接退出，在此一直等待其他线程执行
        //System.in.read();
    }
}
