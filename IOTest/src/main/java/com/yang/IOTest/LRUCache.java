package com.yang.IOTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

@Slf4j
public class LRUCache extends AbstractCache {

    private Deque<Integer> queue;

    private int capacity;

    public LRUCache(int num) {
        this.capacity = num;
        this.queue = new ArrayDeque<>(num);
    }

    /**
     * 添加元素
     * @param key 值
     * @return 添加成功与否
     */
    public boolean put(int key) {
        if (this.capacity > this.queue.size() + 1) {
            this.queue.removeLast();
        }
        if (this.queue.contains(key)) {
            this.queue.remove(key);
        }
        this.queue.addFirst(key);
        return true;
    }

    public Integer get(int key) {
        Iterator<Integer> integerIterator = this.queue.iterator();
        while (integerIterator.hasNext()) {
            Integer num = integerIterator.next();
            if (num == key) {
                return num;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(10);
    }
}
