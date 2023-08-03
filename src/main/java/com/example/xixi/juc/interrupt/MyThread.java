package com.example.xixi.juc.interrupt;

import lombok.SneakyThrows;

/**
 * @author : xi-xi
 */
public class MyThread extends Thread {
    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("i=" + (i + 1));
        }
    }
}
