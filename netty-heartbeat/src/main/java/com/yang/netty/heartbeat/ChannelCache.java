package com.yang.netty.heartbeat;

import io.netty.channel.Channel;
import io.netty.util.concurrent.ScheduledFuture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ChannelCache {
    private Channel channel;
    private ScheduledFuture scheduledFuture;
}
