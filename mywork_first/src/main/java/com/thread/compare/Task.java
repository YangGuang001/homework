package com.thread.compare;

import lombok.AllArgsConstructor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by yz on 2017/6/18.
 */
@AllArgsConstructor
public class Task {
    private int index;

    public boolean computer(){
//        long result = 1;
//        for (int i=0; i < index; i++){
//            result *= i;
//        }
        return true;
    }
}
