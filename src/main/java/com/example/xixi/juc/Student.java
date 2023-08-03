package com.example.xixi.juc;

/**
 * @author : xi-xi
 */
public class Student {
    private int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public Student() {
    }

    public Student(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "Student{" +
                "a=" + a +
                '}';
    }
}
