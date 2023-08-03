package com.example.xixi.netty.helloWorld.handler;

import com.example.xixi.netty.helloWorld.util.ProtostuffUtil;
import com.example.xixi.netty.pojo.UserInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class ProtostuffDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf msg, List<Object> list) throws Exception {
        int length = msg.readableBytes();
        byte[] bytes = new byte[length];
        msg.readBytes(bytes, 0, length);
        UserInfo userInfo = ProtostuffUtil.deserialize(bytes, UserInfo.class);
        list.add(userInfo);


    }
}
