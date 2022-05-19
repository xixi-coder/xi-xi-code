package com.example.xixi.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;

/**
 * @author : xi-xi
 */
public class TokenBucketTest {
    public static void main(String[] args) {
        // 每秒生成2个令牌
        RateLimiter rateLimiter = RateLimiter.create(2);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                // 获得令牌
                rateLimiter.acquire();
                System.out.println(LocalDateTime.now());
            }).start();
        }

    }
}
