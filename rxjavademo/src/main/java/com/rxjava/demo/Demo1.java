package com.rxjava.demo;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import java.util.Arrays;
import java.util.List;


/**
 * Created by yz on 2018/1/12.
 */
public class Demo1 {
    public static void main(String[] args) {
        Integer[] number = {1,2,3,4,5,6,7};
        List<Integer> list = Arrays.asList(number);
        Observable<Integer> integerObservable = Observable.from(list);

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            public void onCompleted() {
                System.out.println("Rx completed");
            }

            public void onError(Throwable throwable) {
                System.out.println(throwable.getCause());
            }

            public void onNext(Integer integer) {
                System.out.println("onNext : " + integer);
            }
        };

        integerObservable.filter(new Func1<Integer, Boolean>(){
            public Boolean call(Integer integer) {
                return integer%2 == 0;
            }
        }).subscribe(subscriber);
    }
}
