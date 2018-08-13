package com.yang.netty.idle;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by yz on 2018/6/18.
 */
public class ServerChannel {
    private final int port;

    public ServerChannel() {
        this(8080);
    }

    public ServerChannel(int port) {
        this.port = port;
    }

    public void startServer() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new IdleStateHandlerInitializer());

        try {
            ChannelFuture channelFuture = bootstrap.bind(this.port).sync();
            if (!channelFuture.isSuccess()) {
                System.out.println("start ServerChannel failed");
            } else {
                System.out.println("start ServerChannel successful");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("start ServerChannel failed");
        }
    }

    public static void main(String[] args) {
        new ServerChannel().startServer();
    }
}
