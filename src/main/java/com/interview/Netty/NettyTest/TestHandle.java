package com.interview.Netty.NettyTest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class TestHandle extends SimpleChannelInboundHandler<HttpObject> {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
                if (httpObject instanceof HttpRequest){
                    ByteBuf byteBuf= Unpooled.copiedBuffer("hello word!", CharsetUtil.UTF_8);
                    FullHttpResponse response=new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,byteBuf);
                    response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
                    response.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
                    channelHandlerContext.writeAndFlush(response);
                    channelHandlerContext.channel().close();
                }
    }
}
