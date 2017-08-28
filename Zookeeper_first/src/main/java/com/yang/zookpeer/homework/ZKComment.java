package com.yang.zookpeer.homework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.zookeeper.WatchedEvent;

/**
 * Created by yz on 2017/8/25.
 */
@Data
@AllArgsConstructor
public class ZKComment {
    private ZKCommentType zkCommentType;
    private WatchedEvent watchedEvent;
}
enum ZKCommentType{
    ParentWatcherType,
    ChildWatcherType
}
