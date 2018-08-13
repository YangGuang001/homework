package com.yang.netty.idle;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

/**
 * Created by yz on 2018/6/18.
 */
public class ClientHandler extends ChannelInitializer<Channel> {
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ClientHandlerMessage());
    }

    private class ClientHandlerMessage extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            System.out.println("message length: " + buf.readableBytes());
        }
    }
}
