package com.yang.work.lession2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yz on 2017/9/11.
 */
public class MMSCRouter {
    private static volatile MMSCRouter instance = new MMSCRouter();

    private final Map<String, MMSCInfo> routerMap;

    public MMSCRouter() {
        this.routerMap = MMSCRouter.retrieveRouterMapFromDB();
    }

    public static Map<String, MMSCInfo> retrieveRouterMapFromDB() {
        Map<String, MMSCInfo> map = new HashMap<String, MMSCInfo>();
        return map;
    }

    public static MMSCRouter getInstance() {
        return instance;
    }

    public MMSCInfo getMMSCInfo(String key) {
        return routerMap.get(key);
    }

    public static void setInstance(MMSCRouter newInstance) {
        instance = newInstance;
    }

    public static Map<String, MMSCInfo> deepCopy(Map<String, MMSCInfo> m) {
        Map<String, MMSCInfo> result = new HashMap<String, MMSCInfo>();
        for (String key : m.keySet() ) {
            result.put(key, new MMSCInfo(m.get(key)));
        }
        return result;
    }

    public Map<String, MMSCInfo> getRouterMap() {
        return Collections.unmodifiableMap(deepCopy(routerMap));
    }
}
