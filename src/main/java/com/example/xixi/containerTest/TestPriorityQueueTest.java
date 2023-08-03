package com.example.xixi.containerTest;

import java.util.PriorityQueue;

/**
 * @author : xi-xi
 */
public class TestPriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<Integer> integers = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                integers.add(i);
            }
        }
        integers.add(3);
        System.out.println(integers.peek());
    }
}
