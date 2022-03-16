package com.example.xixi.juc;

/**
 * @author : xi-xi
 */
class Singleton {
    private volatile static Singleton instance;
    private Singleton() {
    }
    public static Singleton getInstance() {
        //避免一些线程直接去争抢锁达到阻塞状态，导致线程从用户态到内核态转换对于资源的消耗
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                    // new对象分三步
                    //1.开辟内存空间 2 将对象初始化 3 将instance指针指向这片内存区域
                    //A  B线程
                    //线程A 竞争到了锁，进行new对象
                    //加volatile 是避免外层的空判断 B线程拿到错误的结果
                    // volatile 在new 指令前加了 storeStore  后加了storeLoad 屏障
                }
            }
        }
        return instance;
    }
}

