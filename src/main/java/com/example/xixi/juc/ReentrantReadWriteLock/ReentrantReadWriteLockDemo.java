package com.example.xixi.juc.ReentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : xi-xi
 */
public class ReentrantReadWriteLockDemo {


    public static void main(String[] args) {
        ReentrantReadWriteLock rrwLock = new ReentrantReadWriteLock(true);
        ReadThread rt1 = new ReadThread("rt1", rrwLock);
        ReadThread rt2 = new ReadThread("rt2", rrwLock);
//        WriteThread wt1 = new WriteThread("wt1", rrwLock);
        rt1.start();
        rt2.start();
//        wt1.start();
    }
}
@Slf4j
class ReadThread extends Thread {
    private ReentrantReadWriteLock rrwLock;

    public ReadThread(String name, ReentrantReadWriteLock rrwLock) {
        super(name);
        this.rrwLock = rrwLock;
    }

    public void run() {
        log.info(Thread.currentThread().getName() + " trying to lock");
        try {
            rrwLock.readLock();
            rrwLock.readLock().lock();
            log.info(Thread.currentThread().getName() + " lock successfully");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rrwLock.readLock().unlock();
            log.info(Thread.currentThread().getName() + " unlock successfully");
        }
    }
}
@Slf4j
class WriteThread extends Thread {
    private ReentrantReadWriteLock rrwLock;

    public WriteThread(String name, ReentrantReadWriteLock rrwLock) {
        super(name);
        this.rrwLock = rrwLock;
    }

    public void run() {
        log.info(Thread.currentThread().getName() + " trying to lock");
        try {
            rrwLock.writeLock().lock();
            log.info(Thread.currentThread().getName() + " lock successfully");
        } finally {
            rrwLock.writeLock().unlock();
            log.info(Thread.currentThread().getName() + " unlock successfully");
        }
    }
}
