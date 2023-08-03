package com.example.xixi.distribute.delayJob.redis;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * @author : xi-xi
 */
public class RedissionTest1 {
    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);
        RBlockingQueue<String> blockingQueue = redisson.getBlockingQueue("dest_queue1");
        RDelayedQueue<String> delayedQueue = redisson.getDelayedQueue(blockingQueue);
        new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        //阻塞队列有数据就返回，否则wait
                        String take = blockingQueue.take();
                        System.err.println(take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }.start();

        for(int i=10;i<=16;i++) {
            // 向阻塞队列放入数据
            delayedQueue.offer("fffffffff"+i, 13, TimeUnit.SECONDS);
        }
    }
}
