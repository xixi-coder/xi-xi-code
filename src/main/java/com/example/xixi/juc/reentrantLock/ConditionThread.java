package com.example.xixi.juc.reentrantLock;

/**
 * @author : xi-xi
 */
public class ConditionThread {


    public void run() {
        new Thread(new Runnable() {

            @Override
            public void run() {
//                depot.produce(no);
            }
        }, 123 + " produce thread").start();

    }
}
