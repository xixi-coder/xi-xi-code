package com.example.xixi.netty.future.nettyFuture;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class NettyFutureTest {


    @Test
    public void testFuture() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Future<String> future = group.submit(() -> {
            log.info("---异步线程执行任务开始----,time={}", LocalDateTime.now().toString());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("---异步线程执行任务结束----,time={}", LocalDateTime.now().toString());
            return "hello netty future";
        });
        /*String result = future.get();
        log.info("----主线程阻塞等待异步线程执行结果:{}",result);*/
        //设置监听
        future.addListener(new FutureListener<String>() {
            @Override
            public void operationComplete(Future<String> future) throws Exception {
                log.info("---收到异步线程执行任务结果通知----执行结果是;{},time={}",future.get(), LocalDateTime.now().toString());
            }
        });
        log.info("---主线程----");
        TimeUnit.SECONDS.sleep(10);
    }


    @Test
    public void testPromise() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        //promise绑定到eventloop上
        Promise<String> promise = new DefaultPromise<>(group.next());
        Future<String> future = group.submit(()->{
            log.info("---异步线程执行任务开始----,time={}", LocalDateTime.now());
            try {
                TimeUnit.SECONDS.sleep(3);
                promise.setSuccess("hello netty promise");
                log.info("---异步线程执行任务结束----,time={}", LocalDateTime.now());
                return "hello netty future";
            } catch (InterruptedException e) {
                promise.setFailure(e);
                return null;
            }
        });
        future.addListener((FutureListener<String>) future12 -> log.info("---收到异步线程执行任务结果通知----执行结果是;{},time={}", future12.get(), LocalDateTime.now()));
        //设置监听回调
        promise.addListener((FutureListener<String>) future1 -> log.info("----异步任务执行promise1:{}",future1.get()));
        promise.addListener((FutureListener<String>) future2 -> log.info("----异步任务执行promise2:{}",future2.get()));
        log.info("---主线程----");
        TimeUnit.SECONDS.sleep(10);
    }

}
