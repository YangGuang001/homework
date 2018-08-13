package com.yang.netty.heartbeat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.internal.StringUtil;


public class Encoder extends MessageToByteEncoder<Message> {
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        out.writeByte(msg.getType());
        out.writeInt(msg.getLength());
        if (StringUtil.isNullOrEmpty(msg.getContent())) {
            out.writeBytes(msg.getContent().getBytes());
        }
    }
}
