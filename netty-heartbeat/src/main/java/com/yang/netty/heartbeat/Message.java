package com.yang.netty.heartbeat;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Message {
    static final byte TYPE_HEART = 1;
    static final byte TYPE_MESSAGE = 2;

    private byte type;
    private int length;
    private String content;
}
