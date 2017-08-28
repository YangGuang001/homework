package com.yang.zookpeer.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yz on 2017/8/27.
 */
public class GrayCache {
    public static ConcurrentHashMap<String, List<String>> hashMap = new ConcurrentHashMap<String, List<String>>();

    public static void initData(String taskId, List<String> ips){
        Set<String> sets = hashMap.keySet();
        if (!sets.contains(taskId)){
            hashMap.put(taskId, new ArrayList<String>(ips));
        }
    }

}
