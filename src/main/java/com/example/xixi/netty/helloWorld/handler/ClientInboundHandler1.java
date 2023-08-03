package com.example.xixi.netty.helloWorld.handler;

import com.example.xixi.netty.pojo.MessageProto;
import com.example.xixi.netty.pojo.UserInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class ClientInboundHandler1 extends ChannelInboundHandlerAdapter {
    /**
     * 通道准备就绪
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("ClientInboundHandler1 channelActive begin send data");
        //通道准备就绪后开始向服务端发送数据
//        ByteBuf buf = Unpooled.copiedBuffer("hello server,i am client".getBytes(StandardCharsets.UTF_8));
//        ctx.writeAndFlush(buf);

        //批量发送数据
//        UserInfo userInfo;
//        for (int i = 0; i < 100; i++) {
//
//            userInfo = new UserInfo(i, "name" + i, i + 1, (i % 2 == 0) ? "男" : "女", "北京");
////            ctx.writeAndFlush(ctx.alloc().buffer().writeBytes(userInfo.toString().getBytes(StandardCharsets.UTF_8)));
//            ctx.writeAndFlush(userInfo.toString());
//        }

        //测试protobuf
//        MessageProto.Message message;
//        for (int i=0;i<100;i++) {
//            message= MessageProto.Message.newBuilder().setId("message" + i).setContent("hello protobuf").build();
//            ctx.writeAndFlush(message);
//        }

        //测试protostuff
        UserInfo userInfo;
        for (int i = 0; i < 100; i++) {
            userInfo = new UserInfo(i, "name" + i, i + 1, (i % 2 == 0) ? "男" : "女", "北京");
            ctx.writeAndFlush(userInfo);
        }
    }

    /**
     * 通道有数据可读（服务端返回了数据）
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("ClientInboundHandler1 channelRead");
        ByteBuf buf = (ByteBuf) msg;
        log.info("ClientInboundHandler1: received server data ={}", buf.toString(StandardCharsets.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
