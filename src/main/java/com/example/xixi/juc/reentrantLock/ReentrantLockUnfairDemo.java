package com.example.xixi.juc.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : xi-xi
 */


public class ReentrantLockUnfairDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        AQSThread t1 = new AQSThread("t1", lock);
        AQSThread t2 = new AQSThread("t2", lock);
        AQSThread t3 = new AQSThread("t3", lock);
        t1.start();
        t2.start();
        t3.start();
    }
}

