package com.example.xixi.containerTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : xi-xi
 */
public class TestArrayList implements Runnable{

//    //线程不安全
    private List<String> threadList = new ArrayList<>();
//    //线程安全
//    //private List
//
//    threadList = Collections.synchronizedList(new ArrayList());

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //把当前线程名称加入list中
        threadList.add(Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException{
//        TestArrayList listThread = new TestArrayList();
//
//        for(int i = 0; i < 100; i++){
//            Thread thread = new Thread(listThread, "thread-" + i);
//            thread.start();
//        }
//
//        //等待子线程执行完
//        Thread.sleep(2000);
//
//        System.out.println(listThread.threadList.size());
//        //输出list中的值
//        for(int i = 0; i < listThread.threadList.size(); i++){
//            if(listThread.threadList.get(i) == null){
//                System.out.println();;
//            }
//            System.out.print(listThread.threadList.get(i) + "  ");
//        }
        ArrayList<String> strings = new ArrayList<>();
        strings.add("123");
    }
}
