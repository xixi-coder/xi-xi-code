package com.example.xixi.juc.lockSupport;

/**
 * @author : xi-xi
 */
import java.util.concurrent.locks.LockSupport;

class MyThread1 extends Thread {
    private Object object;

    public MyThread1(Object object) {
        this.object = object;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName()+"before unpark");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 获取blocker
        System.out.println(Thread.currentThread().getName()+"Blocker info " + LockSupport.getBlocker((Thread) object));
        // 释放许可
        LockSupport.park();
        LockSupport.unpark((Thread) object);
        // 休眠500ms，保证先执行park中的setBlocker(t, null);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 再次获取blocker
        System.out.println(Thread.currentThread().getName()+"Blocker info " + LockSupport.getBlocker((Thread) object));

        System.out.println(Thread.currentThread().getName()+"after unpark");
    }
}




