package com.yang.zookpeer.homework;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yz on 2017/8/25.
 */
public class ZookpeerFactory {
    private static List<RegistryZK> registryZKList = new ArrayList<RegistryZK>();

    public static void init(){
        ZKConfig zkConfig = new ZKConfig("127.0.0.1:2181", "/root", 50000);
        RegistryZK registryZK = new RegistryZK();
        registryZK.init(zkConfig);
        registryZKList.add(registryZK);

    }

    public static void start(){
        registryZKList.get(0).start();
    }

    public static void stop(){
        registryZKList.get(0).shutdown();
    }
}
