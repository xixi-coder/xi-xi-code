package com.example.xixi.juc;

/**
 * @author : xi-xi
 */
import org.openjdk.jol.info.ClassLayout;
public class JOLDemo {
    private static Object o = new Object();;
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println("---------加锁之后的变化--------------------");
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
