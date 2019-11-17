package com.oracle.truffle.sl.utils;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Consumer;

public class ServerSocketFactory {
    public static class SocketData{
        private ByteBuf bytes;
        private String address;
        private String action;
        private ChannelHandlerContext out;

        public SocketData(ByteBuf bytes, String address,String action,ChannelHandlerContext out) {
            this.bytes = bytes;
            this.address = address;
            this.action = action;
            this.out = out;
        }

        public String getAddress() {
            return address;
        }

        public ByteBuf getBytes() {
            return bytes;
        }

        public String getAction() {
            return action;
        }

        public ChannelHandlerContext getOut() {
            return out;
        }
    }
    public static class ServerHandler extends ChannelInboundHandlerAdapter {
        private Consumer<SocketData> consumer;
        public ServerHandler(Consumer<SocketData> consumer){
            this.consumer = consumer;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx){
            InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
            SocketData data = new SocketData(null,address.toString(),"connect",ctx);
            consumer.accept(data);
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx){
            InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
            SocketData data = new SocketData(null,address.toString(),"disconnect",ctx);
            consumer.accept(data);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf inBuffer = (ByteBuf) msg;
            InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
            consumer.accept(new SocketData(inBuffer,address.toString(),"data",ctx));
        }

//        @Override
//        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
//                    .addListener(ChannelFutureListener.CLOSE);
//        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }
    private static EventLoopGroup group = null;
    private static HashSet<Channel> channels = new HashSet<>();
    public static boolean createServerSocket(int port, Consumer<SocketData> consumer){
        if(group==null) group = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.localAddress(new InetSocketAddress("localhost", port));
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) {
                    socketChannel.pipeline().addLast(new ServerHandler(consumer));
                }
            });
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            final Channel serverChannel = channelFuture.channel();
            channels.add(serverChannel);
            serverChannel.closeFuture().addListener((result)->{
                channels.remove(serverChannel);
                if(channels.isEmpty()){
                    group.shutdownGracefully().sync();
                }
            });
        } catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

}
