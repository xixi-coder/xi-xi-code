package com.example.xixi.juc.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : xi-xi
 */
public class TestLinkedBlockingQueue {
    public static void main(String[] args) {
        try {
            LinkedBlockingQueue<String> strings = new LinkedBlockingQueue<>();
//
//            ArrayBlockingQueue<String> strings = new ArrayBlockingQueue<String>(10);
            CopyOnWriteArrayList<String> copyOnWriteArrayList =new CopyOnWriteArrayList<>();

            strings.offer("12323");
            strings.take();
            AtomicInteger count =new AtomicInteger(23);
            int i = count.getAndDecrement();
            System.out.println(i);
        } catch (Exception e) {

        }

    }
}
