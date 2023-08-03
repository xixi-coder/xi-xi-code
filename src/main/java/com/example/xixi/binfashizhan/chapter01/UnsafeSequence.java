package com.example.xixi.binfashizhan.chapter01;

/**
 * @author : xi-xi
 */
public class UnsafeSequence {
    private int value;

    /**
     * Returns a unique value.
     */
    public int getNext() {
        return value++;
    }
}