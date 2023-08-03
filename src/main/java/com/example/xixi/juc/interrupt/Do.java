package com.example.xixi.juc.interrupt;

/**
 * @author : xi-xi
 */
public class Do {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
        new Thread(() -> {
            for (;;){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("调用thread.isInterrupted()：" + thread.isInterrupted());
                //测试interrupted（）函数
                System.out.println("调用thread.interrupted()：" + thread.interrupted());
            }
        }).start();
        thread.interrupt();

    }
}
