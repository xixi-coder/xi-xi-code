package com.example.xixi.juc.exchanger;

/**
 * @author : xi-xi
 */

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @author wzx
 * @time 2018/2/9
 */
public class Producer implements Runnable {

    private List<String> buffer;

    private final Exchanger<List<String>> exchanger;

    public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle = 1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("Producer: Cycle %d\n", cycle);
            for (int j = 0; j < 10; j++) {
                String message = "Event " + ((i * 10) + j);
                System.out.printf("Producer: %s\n", message);
                buffer.add(message);
            }
            //调用 exchange() 方法来与consumer交换数据
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Producer: " + buffer.size());
            cycle++;
        }
    }
}
