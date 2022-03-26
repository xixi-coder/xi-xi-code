package com.example.xixi.juc.ReentrantReadWriteLock;

/**
 * @author : xi-xi
 */

import java.util.concurrent.locks.ReentrantReadWriteLock;

class WriteThread extends Thread {
    private ReentrantReadWriteLock rrwLock;

    public WriteThread(String name, ReentrantReadWriteLock rrwLock) {
        super(name);
        this.rrwLock = rrwLock;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " trying to lock");
        try {
            rrwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " lock successfully");
        } finally {
            rrwLock.writeLock().unlock();
            System.out.println(Thread.currentThread().getName() + " unlock successfully");
        }
    }
}
