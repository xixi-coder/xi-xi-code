package com.example.xixi.juc.threadLocal;

/**
 * @author : xi-xi
 */
public class TestThreadLocal {

    public static void main(String[] args) {
        ThreadLocal<String> t1 = new ThreadLocal<>();
        ThreadLocal<String> t2 = new ThreadLocal<>();
        t1.set("thanks");
        t2.set("thanks1");
        System.out.println(t1.get());
        System.out.println(t2.get());
    }
}
