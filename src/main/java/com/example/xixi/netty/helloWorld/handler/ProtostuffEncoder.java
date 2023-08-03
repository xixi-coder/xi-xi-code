package com.example.xixi.netty.helloWorld.handler;

import com.example.xixi.netty.helloWorld.util.ProtostuffUtil;
import com.example.xixi.netty.pojo.UserInfo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class ProtostuffEncoder extends MessageToMessageEncoder<UserInfo> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, UserInfo userInfo, List<Object> list) throws Exception {
        byte[] bytes = ProtostuffUtil.serialize(userInfo);
        ByteBuf buf = Unpooled.wrappedBuffer(bytes);
        list.add(buf);
    }
}
