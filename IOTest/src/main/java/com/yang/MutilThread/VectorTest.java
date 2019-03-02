package com.yang.MutilThread;

import java.util.ArrayList;
import java.util.List;

public class VectorTest {

    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            for (int i=0; i < 10; i++) {
                list.add(i);
            }

            Thread thread = new Thread() {
                @Override
                public void run() {
                    for (int i=0; i < 10; i++) {
                        list.remove(i);
                    }
                }
            };

            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    for (int i=0; i < 10; i++) {
                        list.get(i);
                    }
                }
            };

            thread.start();
            thread1.start();
        }

    }
}
