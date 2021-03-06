package com.yang.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class Server {
    /**
     * 用于分配处理业务线程的线程组个数
     */
    protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors()*2; //默认

    /**
     *业务出现线程大小
     */
    protected static final int BIZTHREADSIZE = 4;

    /**
     * NioEventLoopGroup实际上就是个线程池,
     * NioEventLoopGroup在后台启动了n个NioEventLoop来处理Channel事件,
     * 每一个NioEventLoop负责处理m个Channel,
     * NioEventLoopGroup从NioEventLoop数组里挨个取出NioEventLoop来处理Channel
     */
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);

    protected void run() throws Exception {

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup);
        b.channel(NioServerSocketChannel.class);
        b.handler(new SimpleServerHandler());
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("decoder", new UserDecoder());
                pipeline.addLast("encoder", new UserEncoder());
                pipeline.addLast(new ServerHandler());
            }
        });

        ChannelFuture f = b.bind("127.0.0.1",9873).sync();


        System.out.println("netty server start success...");

        /**
         * wait until the socket close
         */
        f.channel().closeFuture().sync();

        shutdown();

    }

    protected static void shutdown() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        new Server().run();
    }

    private static class SimpleServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("SimpleServerHandler channelRegistered");
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("SimpleServerHandler channelActive");
        }

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            System.out.println("SimpleServerHandler handlerAdded");
        }
    }

    private static class SecondServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("SecondServerHandler channelRegistered");
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("SecondServerHandler channelActive");
        }

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            System.out.println("SecondServerHandler handlerAdded");
        }
    }
}