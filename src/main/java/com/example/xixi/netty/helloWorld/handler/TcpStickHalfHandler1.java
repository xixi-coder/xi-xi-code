package com.example.xixi.netty.helloWorld.handler;

import com.example.xixi.netty.pojo.MessageProto;
import com.example.xixi.netty.pojo.UserInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class TcpStickHalfHandler1 extends ChannelInboundHandlerAdapter {

    int count = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
        count++;
//        log.info("---服务端收到的第{}个数据:{}", count, buf.toString(StandardCharsets.UTF_8));
//        String message = (String) msg;

//        MessageProto.Message message = (MessageProto.Message) msg;

//        log.info("---服务端收到的第{}个数据:{}", count, message);

        UserInfo userInfo = (UserInfo) msg;
        log.info("---服务端收到的第{}个数据:{}", count, userInfo);
        super.channelRead(ctx, msg);
    }
}
