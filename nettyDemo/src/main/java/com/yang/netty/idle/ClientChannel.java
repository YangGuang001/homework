package com.yang.netty.idle;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by yz on 2018/6/18.
 */
public class ClientChannel {
    private final int port;

    public ClientChannel() {
        this(8080);
    }

    public ClientChannel(int port) {
        this.port = port;
    }

    public void start() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ClientHandler());

        try {
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(this.port)).sync();
            if (!channelFuture.isSuccess()) {
                System.out.println("connect ServerChannel failed");
            } else {
                System.out.println("connect ServerChannel successful");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("connect ServerChannel failed");
        }
    }

    public static void main(String[] args) {
        new ClientChannel().start();
    }
}
