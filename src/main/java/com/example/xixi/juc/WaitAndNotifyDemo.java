package com.example.xixi.juc;

/**
 * @author : xi-xi
 */
public class WaitAndNotifyDemo {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        synchronized (myThread) {
            try {
                myThread.start();
                // 主线程睡眠3s
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()+" before wait");
                // 阻塞主线程 释放锁、释放cpu执行权 让MyThread 获取锁
                myThread.wait();
                System.out.println(Thread.currentThread().getName()+" after wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
