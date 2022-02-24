package com.example.xixi.binfashizhan.chapter05;

import java.io.File;

/**
 * @author : xi-xi
 */
public class Test {
    public static void main(String[] args) {
        File[] a ={new File("/Users/shiheng/Downloads")};
        ProducerConsumer.startIndexing(a);
    }
}
