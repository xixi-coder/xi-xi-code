package com.example.xixi.netty.helloWorld.handler;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class ServerOutboundHandler1 extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        ByteBuf buf = (ByteBuf) msg;
        log.info("OutboundHandler1----server send msg to client,msg ={}", buf.toString(StandardCharsets.UTF_8));
        //ctx.channel().writeAndFlush(Unpooled.copiedBuffer("okokokok".getBytes(StandardCharsets.UTF_8)));
        // 利用channel在outboundhandler中再写数据会引起类似递归的调用，数据再从pipeline尾部流向头部
        super.write(ctx, buf, promise);//将事件向前传播,父类中调用了ctx.write(msg,promise);
        ctx.writeAndFlush(Unpooled.copiedBuffer("nonono".getBytes(StandardCharsets.UTF_8)));//用ctx写数据，代码写到super之后，该事件会流到该handler之前的handler进行处理

    }
}
