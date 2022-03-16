package com.example.xixi.juc;

/**
 * @author : xi-xi
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WorkerPool {

    public static void main(String args[]) throws InterruptedException {
        //RejectedExecutionHandler implementation
        //Get the ThreadFactory implementation to use
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //creating the ThreadPoolExecutor
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(
                2, 4, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(6), threadFactory, new RejectedExecutionHandlerImpl());
        //start the monitoring thread
        //想要
        MyMonitorThread monitor = new MyMonitorThread(executorPool, 3);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();
        //submit work to the thread pool
        for (int i = 0; i < 10; i++) {
            executorPool.submit(new WorkerThread("cmd" + i));
        }
        Thread.sleep(300000);
        //shut down the pool
        executorPool.shutdown();
        //shut down the monitor thread
        Thread.sleep(5000);
        monitor.shutdown();

    }
}

