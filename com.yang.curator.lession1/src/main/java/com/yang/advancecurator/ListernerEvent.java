package com.yang.advancecurator;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;

@AllArgsConstructor
@Data
public class ListernerEvent {
    private EventType type;
    private PathChildrenCacheEvent cacheEvent;
    private CuratorFramework curatorFramework;
    private PathChildrenCacheEvent.Type cacheEventType;
}
