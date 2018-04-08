package com.yang.rxjava.demo;

import org.junit.Test;

/**
 * Created by yz on 2018/3/26.
 */
public class Observable<T> {
    private final OnSubscribe<T> onSubscribe;

    private Observable(OnSubscribe<T> subscriber) {
        this.onSubscribe = subscriber;
    }

    public static <T> Observable<T> create(OnSubscribe<T> onSubscribe) {
        return new Observable<T>(onSubscribe);
    }

    public void subscribe(Subscriber<? super T> subscriber) {
        subscriber.onStart();
        onSubscribe.call(subscriber);
    }

    public <R> Observable<R> map(final Transformer<? super T, ? extends R> transformer) {
        return create(new OnSubscribe<R>() {
            public void call(final Subscriber<? super R> subscriber) {
                Observable.this.subscribe(new Subscriber<T>() {
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    public void onError(Throwable throwable) {
                        subscriber.onError(throwable);
                    }

                    public void onNext(T var) {
                        subscriber.onNext(transformer.call(var));
                    }
                });
            }
        });
    }

    public interface OnSubscribe<T> {
        void call(Subscriber<? super T> subscriber);
    }

    public interface Transformer<T, R> {
        R call(T from);
    }
}
