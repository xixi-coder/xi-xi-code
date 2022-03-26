package com.example.xixi.juc.ReentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j(topic = "c.TestReadWriteLock")
public class TestReadWriteLock {
    public static void main(String[] args) throws InterruptedException {
        DataContainer dataContainer = new DataContainer();
        new Thread(() -> {
            dataContainer.read();
        }, "t1").start();
        new Thread(() -> {
            dataContainer.write();
        }, "t2").start();
    }
}

@Slf4j(topic = "c.DataContainer")
class DataContainer {
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock r = rw.readLock();
    private ReentrantReadWriteLock.WriteLock w = rw.writeLock();

    public void read() {
        r.lock();
        log.debug("获取读锁...");
        try {
            log.debug("读取");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            r.unlock();
            log.debug("释放读锁...");
        }

    }

    public void write() {
        w.lock();
        log.debug("获取写锁...");
        try {
            log.debug("写入");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            w.unlock();
            log.debug("释放写锁...");
        }
    }
}
