package com.example.xixi.juc.copyonwrite;

/**
 * @author : xi-xi
 */
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

class PutThread extends Thread {
    private CopyOnWriteArrayList<Integer> cowal;

    public PutThread(CopyOnWriteArrayList<Integer> cowal) {
        this.cowal = cowal;
    }

    public void run() {
        try {
            for (int i = 100; i < 110; i++) {
                cowal.add(i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> cowal = new CopyOnWriteArrayList<Integer>();
//        for (int i = 0; i < 10; i++) {
//            cowal.add(i);
//        }
//        PutThread p1 = new PutThread(cowal);
//        p1.start();
//        Iterator<Integer> iterator = cowal.iterator();
//        while (iterator.hasNext()) {
//            System.out.print(iterator.next() + " ");
//        }
//        System.out.println();
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        iterator = cowal.iterator();
//        while (iterator.hasNext()) {
//            System.out.print(iterator.next() + " ");
//        }
        cowal.add(1);
        cowal.add(2);
        cowal.add(3);
        cowal.add(4);

        Iterator<Integer> iterator = cowal.iterator();
        new Thread(()->{
            cowal.remove(0);
            System.out.println(cowal);
        }).start();
        try {
            Thread.sleep(1000);
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}

