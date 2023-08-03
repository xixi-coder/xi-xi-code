package com.example.xixi.netty.helloWorld;


import com.example.xixi.netty.helloWorld.handler.*;
import com.example.xixi.netty.pojo.MessageProto;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.logging.SocketHandler;

@Slf4j
public class NettyServer {

    public static void main(String[] args) {
        NettyServer nettServer = new NettyServer();
        nettServer.start(8888);
    }

    private void start(int port) {
        //创建boss group 和 worker group
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        //创建服务端启动对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker)
                // 指定服务端通道，用于接收并创建新连接
                .channel(NioServerSocketChannel.class)
                //每个客户端channel初始化时都会执行该方法来配置该channel的相关handler
                .handler(new LoggingHandler(LogLevel.DEBUG)) // 给boss group 配置handler
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //获取与该channel绑定的pipeline
                        ChannelPipeline pipeline = ch.pipeline();
                        //一次编解码解决粘包、半包问题
//                        pipeline.addLast(new StringEncoder());
//                        pipeline.addLast(new LengthFieldPrepender(4));
////                        pipeline.addLast(new ProtobufEncoder());
//                        pipeline.addLast(new ProtostuffEncoder());
//
//                        pipeline.addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 4));
////                        pipeline.addLast(new ProtobufDecoder(MessageProto.Message.getDefaultInstance()));
//                        pipeline.addLast(new ProtostuffDecoder());
////                        pipeline.addLast(new StringDecoder());
//                        //像pipeline中添加handler
////                        pipeline.addLast(new ServerOutboundHandler1());
////
////                        pipeline.addLast(new ServerInboundHandler1());
//                        pipeline.addLast(new TcpStickHalfHandler1());

                        //测试 http service
                        pipeline.addLast(new HttpResponseEncoder());

                        pipeline.addLast(new HttpRequestDecoder());
                        //文件上传需要设置大点儿 单位是字节
                        pipeline.addLast(new HttpObjectAggregator(1024*1024*8));
                        pipeline.addLast(new MyHttpServerHandler());

                    }
                });
        //绑定端口并启动
        ChannelFuture future = serverBootstrap.bind(port);
        try {
            //对关闭通道进行监听
            future.channel().closeFuture().sync();
            log.info("服务端启动成功");
        } catch (InterruptedException e) {
            log.error("netty server error ,{}", e.getMessage());
        } finally {
            //优雅关闭
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }


}
