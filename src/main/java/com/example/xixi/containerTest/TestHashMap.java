package com.example.xixi.containerTest;

/**
 * @author : xi-xi
 */
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.*;

public class TestHashMap {



    public static void main(String args[]) throws InterruptedException {
//        Map<Integer,Integer>  map = new HashMap<Integer, Integer>();  //则≤10W：
        //修正
        //Map<Integer,Integer>  map = Collections.synchronizedMap(new HashMap<Integer, Integer>());  //则=10W
        //修改
        //Map<Integer,Integer>  map = new ConcurrentHashMap<Integer, Integer>(); //则=10W

        //JDK 1.8测试
//        new Thread(()->{
//            for (int i = 0; i < 50000; i++) {
//                map.put(i, i);
//            }
//        },"t1").start();
//        new Thread(()->{
//            for (int i = 50000; i <100000; i++) {
//                map.put(i, i);
//            }
//        },"t2").start();
//
//        Thread.sleep(1000);
//        System.out.println(map.size());
        //Map<Object, Object> map = new HashMap<>();
//        //Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());
//        Map<Object, Object> map = new ConcurrentHashMap<>();
//        for(int i = 1;i <= 100; i++){
//            new Thread(()->{
//                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
//                System.out.println(map);
//            },String.valueOf(i)).start();
//        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("a","b");
        hashMap.put("b","c");

    }
}

