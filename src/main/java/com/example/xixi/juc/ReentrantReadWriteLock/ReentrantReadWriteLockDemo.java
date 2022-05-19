package com.example.xixi.juc.ReentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : xi-xi
 */
@Slf4j
public class ReentrantReadWriteLockDemo {


    public static void main(String[] args) {
        ReentrantReadWriteLock rrwLock = new ReentrantReadWriteLock(false);
        ReadThread rt1 = new ReadThread("rt1", rrwLock);
        new Thread(() -> {
            rrwLock.writeLock().lock();
            log.info(Thread.currentThread().getName() + " trying to write lock");
            try {
                Thread.sleep(1000);
                rrwLock.readLock().lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t0001").start();
//        ReadThread rt2 = new ReadThread("rt2", rrwLock);
        WriteThread wt1 = new WriteThread("wt1", rrwLock);
        rt1.start();
//        rt2.start();
        wt1.start();
    }
}

@Slf4j
class ReadThread extends Thread {
    private ReentrantReadWriteLock rrwLock;

    public ReadThread(String name, ReentrantReadWriteLock rrwLock) {
        super(name);
        this.rrwLock = rrwLock;
    }

    @Override
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

    @Override
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
