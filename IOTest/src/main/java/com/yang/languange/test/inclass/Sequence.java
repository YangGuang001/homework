package com.yang.languange.test.inclass;

/**
 * Created by yz on 2018/7/9.
 */
public class Sequence {
    private Object[] items;

    private int next = 0;

    public Sequence(int size) {
        this.items = new Object[size];
    }

    public void add(Object object) {
        if (next < items.length) {
            items[next++] = object;
        }
    }

    private class SequenceSelector implements Selector {
        private int i = 0;
        public boolean end() {
            return i == items.length;
        }

        public Object current() {
            return items[i];
        }

        public void next() {
            if (i < items.length) {
                i++;
            }
        }
    }

    public Selector selector() {
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for (int i=0; i < 10; i++) {
            sequence.add(Integer.toString(i));
        }
        Selector selector = sequence.selector();
        while (!selector.end()) {
            System.out.println(selector.current() + " ");
            selector.next();
        }
    }
}
