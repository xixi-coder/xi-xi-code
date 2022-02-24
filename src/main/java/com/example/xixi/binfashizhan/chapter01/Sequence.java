package com.example.xixi.binfashizhan.chapter01;

/**
 * @author : xi-xi
 */
public class Sequence {
    private int nextValue;

    public synchronized int getNext() {
        return nextValue++;
    }
}
