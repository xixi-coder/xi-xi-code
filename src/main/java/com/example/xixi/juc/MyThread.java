package com.example.xixi.juc;

/**
 * @author : xi-xi
 */
class MyThread extends Thread {

    public void run() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName()+" before notify");
            notify();
            System.out.println(Thread.currentThread().getName()+" after notify");
        }
    }
}



