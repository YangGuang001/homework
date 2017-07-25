package com.yang.MutilThread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yz on 2017/7/22.
 */
public class MyThreadLocal<T> {
    private Map<Thread, T> container = Collections.synchronizedMap(new HashMap<Thread, T>());

    protected T initialValue() {
        return null;
    }

    public void set(T value){
        container.put(Thread.currentThread(), value);
    }

    public T get() {
        Thread thread = Thread.currentThread();
        T value = container.get(thread);
        if (value == null && !container.containsKey(thread)) {
            value = initialValue();
            container.put(thread, value);
        }
        return value;
    }

    public void remove() {
        container.remove(Thread.currentThread());
    }
}
