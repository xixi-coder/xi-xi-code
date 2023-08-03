package com.example.xixi.netty.helloWorld.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class ClientSimpleInboundHandler2 extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        log.info("ClientSimpleInboundHandler2 channelRead");
        log.info("ClientSimpleInboundHandler2: received server data ={}", msg.toString(StandardCharsets.UTF_8));
    }
}