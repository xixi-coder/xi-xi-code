package com.example.xixi.juc;

import java.sql.Connection;

/**
 * @author : xi-xi
 */
public class TestThreadLocal {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(()->{
                ConnectionManager connectionManager = new ConnectionManager(finalI +"");
                try {
                    if (finalI %2 ==0)
                    Thread.sleep(1000);
                    connectionManager.getConnection1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
