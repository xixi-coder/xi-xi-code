package com.example.xixi.juc.lockSupport;

import com.example.xixi.juc.lockSupport.MyThread1;

import java.util.concurrent.locks.LockSupport;

/**
 * @author : xi-xi
 */
public class LockSupportTest {
    public static void main(String[] args) {

        MyThread1 myThread = new MyThread1(Thread.currentThread());
        myThread.start();
        System.out.println(Thread.currentThread().getName()+"before park");
        // 获取许可
        LockSupport.park("ParkAndUnparkDemo");
        System.out.println(Thread.currentThread().getName()+"after park");

    }
}
