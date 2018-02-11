package com.yang.IOTest;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * Created by yz on 2018/1/25.
 */

@RequiredArgsConstructor
@Data
@Slf4j
public class RemidTask{

    @NonNull public int age;
    @NonNull public String name;

    public final String phone = null;

    public static void main(String[] args) {
        RemidTask remidTask = new RemidTask(1,"yang");
        log.info("yangxinzhao");
    }
}