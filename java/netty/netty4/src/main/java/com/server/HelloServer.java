package com.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * {
 *     "bossGroup":{
 *         "children":[
 *             {
 *                 "parent":"bossGroup",
 *                 "epollFd":{
 *                     "channels":[
 *                         {
 *                             "pipeline":{
 *                                 "head":"AbstractChannelHandlerContext",
 *                                 "tail":"AbstractChannelHandlerContext",
 *                                 "pipeline":"1. Pipeline持有DefaultChannelHandlerContext的双向链表中 2. DefaultChannelHandlerContext中就有我们ChannelHandler 3. 对于server端会把ServerBootstrapAcceptor添加到ServerChannel的pipeline的双端队列中，ServerBootstrapAcceptor就是用于处理将新连接注册到workerGroup中"
 *                             }
 *                         }
 *                     ],
 *                     "comments":"就代表这我们linux的epoll，java中的selector。channel都会注册进来,这里先暂时用一个channels数组来表示"
 *                 },
 *                 "eventFd":"用于线程间通讯",
 *                 "taskQueue":"",
 *                 "tailTasks":"",
 *                 "executor":"ThreadPerTaskExecutor真正的线程",
 *                 "comments":"EpollEventLoop代表着一个线程"
 *             }
 *         ],
 *         "comments":"EpollEventLoop代表一个线程池， 默认线程数量为处理器的个数*2, 用于处理connection"
 *     },
 *     "workerGroup":{
 *         "children":[
 *             {
 *                 "parent":"bossGroup",
 *                 "epollFd":{
 *                     "channels":[
 *                         {
 *                             "pipeline":{
 *                                 "head":"AbstractChannelHandlerContext",
 *                                 "tail":"AbstractChannelHandlerContext",
 *                                 "pipeline":"1. Pipeline持有DefaultChannelHandlerContext的双向链表中 2. DefaultChannelHandlerContext中就有我们ChannelHandler"
 *                             }
 *                         }
 *                     ],
 *                     "comments":"就代表这我们linux的epoll，java中的selector。channel都会注册进来,这里先暂时用一个channels数组来表示"
 *                 },
 *                 "eventFd":"用于线程间通讯",
 *                 "taskQueue":"",
 *                 "tailTasks":"",
 *                 "executor":"ThreadPerTaskExecutor真正的线程",
 *                 "comments":"EpollEventLoop代表着一个线程"
 *             }
 *         ],
 *         "comments":"NioEventLoopGroup代表一个线程池, 用于处理read/write时间"
 *     }
 * }
 */
public class HelloServer {

    public void start(int port) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            final HelloServerChildInHandlerWithoutAdapter helloServerChildInHandlerWithoutAdapter = new HelloServerChildInHandlerWithoutAdapter();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).handler(new SimpleServerInboundHandler())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            // 注册handler
                            ch.pipeline().addLast(new HelloServerChildInHandler(), new HelloServerChildOutHandler(), helloServerChildInHandlerWithoutAdapter);
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(port).sync();

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        HelloServer server = new HelloServer();
        server.start(8000);
    }
}