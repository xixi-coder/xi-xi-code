package com.example.xixi.netty.helloWorld;

import com.example.xixi.netty.helloWorld.handler.ClientInboundHandler1;
import com.example.xixi.netty.helloWorld.handler.ProtostuffDecoder;
import com.example.xixi.netty.helloWorld.handler.ProtostuffEncoder;
import com.example.xixi.netty.pojo.MessageProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClient {
    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        client.start("127.0.0.1", 8888);
    }

    public void start(String host, int port) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            // OutboundHandler 出站数据编码
                            //pipeline 里的handler
                            // LengthFieldPrepender StringEncoder LengthFieldBasedFrameDecoder  StringDecoder ClientInboundHandler1
                            //
                            //从bytebuf 编码成 带长度的bytebuf
                            pipeline.addLast(new LengthFieldPrepender(4));
                            //从string 编码成bytebuf
//                            pipeline.addLast(new ProtobufEncoder());
                            pipeline.addLast(new ProtostuffEncoder());
//                            //pipeline.addLast(new ProtobufEncoder());
//                            pipeline.addLast(new ProtoStuffEncoder());
//
//                            pipeline.addLast(new WriterIdleHandler());
//                            // InboundHandler 入站数据解码
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 4));
//                            pipeline.addLast(new StringDecoder());
//                            pipeline.addLast(new ProtobufDecoder(MessageProto.Message.getDefaultInstance()));
                            pipeline.addLast(new ProtostuffDecoder());

                            //添加客户端channel对应的handler
                            pipeline.addLast(new ClientInboundHandler1());
                        }
                    });
            //连接远程启动
            ChannelFuture future = bootstrap.connect(host, port).sync();
            //监听通道关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("netty client error ,msg={}", e.getMessage());
        } finally {
            //优雅关闭
            group.shutdownGracefully();
        }
    }
}
