package com.example.xixi.juc;

/**
 * @author : xi-xi
 */
public class SynchronizedDemo2 {

    Object object = new Object();
    public void method1() {
        synchronized (object) {
            System.out.println("method1");
        }
        method2();
    }

    private static void method2() {
        System.out.println("method2");
    }
}

