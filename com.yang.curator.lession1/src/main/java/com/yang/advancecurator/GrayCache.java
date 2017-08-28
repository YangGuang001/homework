package com.yang.advancecurator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yz on 2017/8/27.
 */
public class GrayCache {
    public static ConcurrentHashMap<String, List<String>> hashMap = new ConcurrentHashMap<String, List<String>>();

    public static void initData(String taskId, List<String> sons){
        Set<String> sets = hashMap.keySet();
        if (!sets.contains(taskId)){
            hashMap.put(taskId, new ArrayList<String>(sons));
        }
    }

    public static void loadDate(EventType type, String fullpath){

    }

    public static void upDataTaskId(String fullpath) {
        System.out.println(fullpath);
    }

    public static void removeTaskId(String fullpath) {
        String[] paths = fullpath.split("/");
        if (paths.length > 2){
            hashMap.get(paths[1]).remove(paths[2]);
        }else{
            hashMap.remove(paths[1]);
        }
    }

    public static void addTaskId(String fullpath){
        String[] paths = fullpath.substring(1).split("/");
        String taskId = paths[1];
        Set<String> taskIds = hashMap.keySet();
        if (paths.length > 2){
            if (!taskIds.contains(taskId)){
                List<String> lists = new ArrayList<String>();
                lists.add(paths[2]);
                hashMap.put(taskId, lists);
            }else{
                hashMap.get(taskId).add(paths[2]);
            }
        }
    }


}

enum EventType {
    Parent,
    Child
}
