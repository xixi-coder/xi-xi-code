package com.example.xixi.juc.volatiled;

/**
 * @author : xi-xi
 */
public class SingleInstance {

    private static volatile SingleInstance instance;

    public static SingleInstance getInstance() {
        if (instance == null) {   //第一次检查
            synchronized (SingleInstance.class) { //加锁
                if (instance == null) { //第二次检查
                    instance = new SingleInstance(); //问题的根源出现在这里
                }
            }
        }
        return instance;
    }
}
