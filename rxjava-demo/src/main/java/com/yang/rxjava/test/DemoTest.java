package com.yang.rxjava.test;

import org.junit.Test;
import rx.*;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by yz on 2018/3/29.
 */
public class DemoTest {

    @Test
    public void testHelloWorld() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello world!!!");
                subscriber.onCompleted();
                subscriber.onError(new RuntimeException("test"));
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            public void onCompleted() {
                System.out.println("completed !!!");
            }

            public void onError(Throwable throwable) {
                System.out.println(throwable.getCause());
            }

            public void onNext(String var) {
                System.out.println(var);
            }
        };

        observable.subscribe(subscriber);
    }

    @Test
    public void testNewOpertion() {
        Observable<String> observable = Observable.just("Hello world");
        Action1<String> onNextAction = new Action1<String>() {
            public void call(String s) {
                System.out.println(s);
            }
        };
        observable.subscribe(onNextAction);
    }

//    @Test
//    public void testlambda() {
//        Observable.just("Hello world").subscribe(System.out::println);
//    }

    @Test
    public void testMap() {
        Observable.just("Htllo world")
                .map(new Func1<String, String>() {

                    public String call(String s) {
                        return s + "---yang";
                    }
                }).subscribe(new Action1<String>() {

            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void testQuery() {
        Subscription subscription = Observable.from(new String[]{"url1", "url2", "url3", "url4"})
                .subscribe(new Action1<String>() {
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
        System.out.println(subscription);
    }

    @Test
    public void testSingle() {
        Single.just("Hello world").subscribe(new SingleSubscriber<String>() {
            @Override
            public void onSuccess(String value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable error) {
                System.out.println(error.getMessage());
            }
        });
    }

    @Test
    public void testSingleTimeout() {
        Single.create(new Single.OnSubscribe<String>() {
            public void call(SingleSubscriber<? super String> singleSubscriber) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                singleSubscriber.onSuccess("test");
            }
        }).timeout(1, TimeUnit.MILLISECONDS).subscribe(new SingleSubscriber<String>() {
            @Override
            public void onSuccess(String value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable error) {
                System.out.println(error.getCause().getLocalizedMessage());
            }
        });
    }

    @Test
    public void testSubject() {
        AsyncSubject as = AsyncSubject.create();
        as.onNext(1);
        as.onNext(2);
        as.onNext(3);
        as.onCompleted();
        as.subscribe(new Action1<Integer>() {
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
        // 不要这样使用Subject  因为just(T)、from(T)、create(T)会把Subject转换为Obserable
        AsyncSubject.just(1,2,3).subscribe(new Action1<Integer>() {
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });

    }

    @Test
    public void testBehaviorSubject() {
        // 注意订阅时机，以下这个例子收不到回调
        BehaviorSubject bs = BehaviorSubject.create(-1);
        // 这里订阅回调-1, 1, 2, 3
        bs.subscribe(
                new Action1<Integer>() {
                    public void call(Integer o) {
                        System.out.println(o);
                    }
                });
        bs.onNext(1);
        // 这里订阅回调1, 2, 3
        bs.onNext(2);
        // 这里订阅回调2, 3
        bs.onNext(3);
        // 这里订阅回调3
        bs.onCompleted();
        // 这里订阅没回调
    }

    @Test
    public void testSchedule() {
        Scheduler.Worker worker = Schedulers.io().createWorker();
        worker.schedule(new Action0() {
            public void call() {
                System.out.println("hello world");
            }
        });
        //worker.now();
    }
}
