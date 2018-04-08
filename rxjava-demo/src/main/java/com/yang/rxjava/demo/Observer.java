package com.yang.rxjava.demo;

/**
 * Created by yz on 2018/3/26.
 */
public interface Observer<T> {
    void onCompleted();
    void onError(Throwable throwable);
    void onNext(T var);
}
