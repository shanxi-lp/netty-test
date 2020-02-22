package com.interview.Netty.NettyTest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class NettyServer {

    public static void main(String[] args) {
        EventLoopGroup boos=new NioEventLoopGroup();
        EventLoopGroup work=new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(boos,work).channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("httpServerCodec",new HttpServerCodec());
                            socketChannel.pipeline().addLast("testHandle",null);
                }
            });
            ChannelFuture future=bootstrap.bind(8899).sync();
            future.channel().closeFuture().sync();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            boos.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}
