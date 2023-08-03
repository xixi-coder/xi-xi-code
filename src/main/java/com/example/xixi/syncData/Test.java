package com.example.xixi.syncData;

import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 1000;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {
        ArrayList<String> data = getData();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        List<List<String>> parts = Lists.partition(data, 100);
        parts.forEach(list -> {
            Runnable worker = new MyRunnable(list);
            //执行Runnable
            executor.submit(worker);
        });
        //终止线程池
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }


    public static ArrayList<String> getData() {
        File file = new File("/Users/shiheng/Desktop/work/data-prod-1.text");
        BufferedReader reader = null;
        ArrayList<String> seqNos = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String str = null;
            while (true) {
                str = reader.readLine();
                if(str!=null){
                    seqNos.add(str);
                } else{
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return seqNos;
    }
}

