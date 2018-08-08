package com.server;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;

// 该handler是InboundHandler类型
public class HelloServerChildInHandlerWithoutAdapter implements ChannelInboundHandler {

    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HelloServerChildInHandlerWithoutAdapter channelRegistered");
    }

    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HelloServerChildInHandlerWithoutAdapter channelUnregistered");
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HelloServerChildInHandlerWithoutAdapter channelActive");
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HelloServerChildInHandlerWithoutAdapter channelInactive");
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("HelloServerChildInHandlerWithoutAdapter channelRead");
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HelloServerChildInHandlerWithoutAdapter channelReadComplete");
    }

    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("HelloServerChildInHandlerWithoutAdapter userEventTriggered");
    }

    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HelloServerChildInHandlerWithoutAdapter channelWritabilityChanged");
    }

    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HelloServerChildInHandlerWithoutAdapter handlerAdded");
    }

    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HelloServerChildInHandlerWithoutAdapter handlerRemoved");
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("HelloServerChildInHandlerWithoutAdapter exceptionCaught");
    }
}
