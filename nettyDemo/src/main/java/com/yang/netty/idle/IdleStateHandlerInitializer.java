package com.yang.netty.idle;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;


public class IdleStateHandlerInitializer extends ChannelInitializer<Channel> {
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(0, 0, 30, TimeUnit.SECONDS));
        pipeline.addLast(new HeartbeatHandler());
    }

    private static final class HeartbeatHandler extends ChannelInboundHandlerAdapter {
        private static final ByteBuf HEARTBEAT_SEQUENCE =
                Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(
                        "HEARTBEAT", CharsetUtil.ISO_8859_1));

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if (evt instanceof IdleStateEvent ) {
                System.out.println("time: " + System.currentTimeMillis() + ", msg" + HEARTBEAT_SEQUENCE);
                ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate())
                        .addListener(new ChannelFutureListener() {
                            public void operationComplete(ChannelFuture future) throws Exception {
                                if (future.isSuccess()) {
                                    System.out.println("write message successful!!");
                                }
                            }
                        })
                        .addListener(ChannelFutureListener.CLOSE_ON_FAILURE);

            } else {
                super.userEventTriggered(ctx, evt);
            }
        }
    }
}
