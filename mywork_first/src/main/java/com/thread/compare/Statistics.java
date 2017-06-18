package com.thread.compare;

import lombok.Getter;
import lombok.Setter;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yz on 2017/6/18.
 */
public class Statistics {
    private long lastCount = 0;
    @Setter
    @Getter
    private long curCount = 0;
    private Timer timer = new Timer(true);
    public void start(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("TPS: " + (curCount - lastCount));
                lastCount = curCount;
            }
        };

        timer.scheduleAtFixedRate(timerTask,1000,1000);
    }
}
