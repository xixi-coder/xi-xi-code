package com.example.xixi.juc;

/**
 * @author : xi-xi
 */
public class Test1 {
 private static Object a =new Object();
    public static  String test03(String s1, String s2, String s3) {
        synchronized (a){
            String s = s1 + s2 + s3;
            return s;
        }
    }
}
