package com.yang.netty.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * 请求的超时时间
     */
    private static final long TIMEOUT = 2*60*1000L;

    /**
     * cache的过期时间：60s
     */
    private static final long MILSECONDS = 1000*60;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        User user = (User)msg;
        System.out.println("用户名: " + user.getUsername() + "密码: " + user.getAge());
        ctx.channel().writeAndFlush(user);
    }

    /**
     * Close the connection when an exception is raised.
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) throws Exception {
        ctx.close();
    }
}
