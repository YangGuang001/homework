package com.yang.IOTest;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yz on 2018/1/25.
 */
public class Reminder {
    Timer time;

    public Reminder(int seconds) {
        time = new Timer();
        time.schedule(new RemidTask(), seconds * 1000);
    }

    class RemidTask extends TimerTask {
        public void run() {
            System.out.println("Time's up");
            time.cancel();
        }
    }

    public static void main(String[] args) {
        System.out.println("About to schedule task");
        new Reminder(5);
        System.out.println("Task end");
    }
}

