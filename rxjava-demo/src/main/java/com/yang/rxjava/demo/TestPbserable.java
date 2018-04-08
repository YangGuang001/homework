package com.yang.rxjava.demo;

import org.junit.Test;

/**
 * Created by yz on 2018/3/26.
 */
public class TestPbserable {

    @Test
    public void testObervable() {
        Observable.create(new Observable.OnSubscribe<Integer>() {

            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                }
            }
        }).subscribe(new Subscriber<Integer>() {

            public void onCompleted() {
                System.out.println("finished !!!");
            }

            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            public void onNext(Integer var) {
                System.out.println(var);
            }
        });

    }

    @Test
    public void testObervableMap() {
        Observable.create(new Observable.OnSubscribe<Integer>() {

            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                }
            }
        }).map(new Observable.Transformer<Integer, String>() {
            public String call(Integer from) {
                return "maping " + from;
            }
        }).subscribe(new Subscriber<String>() {

            public void onCompleted() {
                System.out.println("finished !!!");
            }

            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            public void onNext(String var) {
                System.out.println(var);
            }
        });

    }
}
