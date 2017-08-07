package com.yang.netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class UserEncoder extends MessageToByteEncoder<User> {

    @Override
    protected void encode(ChannelHandlerContext ctx, User user, ByteBuf out) throws Exception {
        byte[] datas = ByteObjConverter.objectToByte(user);
        out.writeBytes(datas);
        ctx.flush();
    }
}
