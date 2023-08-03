package com.example.xixi.javaBasic;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author : xi-xi
 */
public class A {
    private int x;         // 实例变量
    private static int y;  // 静态变量

    public static void main(String[] args) {
//         int x = A.x;  // Non-static field 'x' cannot be referenced from a static context
//        A a = new A();
//        int x = a.x;

//        int y = A.y;
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        for (Search search : s) {
            search.searchDoc("hello world");
        }
    }
}

