package com.example.xixi.juc;

/**
 * @author : xi-xi
 */
import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo {
    static class LocalVariable {
        private Long[] a = new Long[1024 * 1024];
    }


    final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(), ThreadFactoryBuilder.create().build());

    final static ThreadLocal<LocalVariable> LOCAL_VARIABLE = new ThreadLocal<LocalVariable>();

    public static void main(String[] args) throws InterruptedException {
        // (3)
        Thread.sleep(5000 * 4);
        for (int i = 0; i < 50; ++i) {
            POOL_EXECUTOR.execute(new Runnable() {
                @Override
                public void run() {
                    // (4)
                    LOCAL_VARIABLE.set(new LocalVariable());
                    // (5)
                    System.out.println("use local varaible" + LOCAL_VARIABLE.get());
//                    localVariable.remove();
                }
            });
        }
        System.out.println("pool execute over");
    }
}

