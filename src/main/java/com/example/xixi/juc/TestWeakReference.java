package com.example.xixi.juc;

import java.lang.ref.WeakReference;
import java.util.Hashtable;

/**
 * @author : xi-xi
 */
public class TestWeakReference {
    public static void main(String[] args) {
        WeakReference<Student> studentWeakReference = new WeakReference<>(new Student(111));
        System.out.println(studentWeakReference.get());
        System.gc();
        System.out.println(studentWeakReference.get());
    }
}
