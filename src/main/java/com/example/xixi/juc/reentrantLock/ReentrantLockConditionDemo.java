package com.example.xixi.juc.reentrantLock;

/**
 * @author : xi-xi
 */
public class ReentrantLockConditionDemo {

    public static void main(String[] args) throws InterruptedException {
        Depot depot = new Depot(500);
        new Producer(depot).produce(500);
        new Producer(depot).produce(200);
        new Consumer(depot).consume(500);
        new Consumer(depot).consume(200);
    }
}
