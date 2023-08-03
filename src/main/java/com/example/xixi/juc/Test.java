package com.example.xixi.juc;

import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * @author : xi-xi
 */
public class Test{
    public static void main(String[] args) {

        ExecutorService es = Executors.newCachedThreadPool();
        Future<String> future = es.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    System.out.println(LocalDateTime.now());
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "future result";
            }
        });
        try {
            String result = future.get();
            System.out.println(LocalDateTime.now());
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

