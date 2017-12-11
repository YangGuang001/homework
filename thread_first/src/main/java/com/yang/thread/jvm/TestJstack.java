package com.yang.thread.jvm;

/**
 * Created by yz on 2017/9/16.
 */
public class TestJstack {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyThread());
        thread.start();
        boolean flag = true;
        int j = 0;
        while (flag) {
            j++;
        }
    }
}

class MyThread implements Runnable {
    private boolean flag = true;

    public void run() {
        int i = 0;
        while (flag) {
            i++;
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
