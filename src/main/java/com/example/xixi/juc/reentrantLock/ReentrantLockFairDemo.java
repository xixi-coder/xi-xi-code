package com.example.xixi.juc.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : xi-xi
 */


public class ReentrantLockFairDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);
        lock.unlock();
        AQSThread t1 = new AQSThread("t1", lock);
        AQSThread t2 = new AQSThread("t2", lock);
        t1.start();
        t2.start();
    }
}

