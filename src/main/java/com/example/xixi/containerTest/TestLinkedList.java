package com.example.xixi.containerTest;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author : xi-xi
 */
public class TestLinkedList {
    public static void main(String[] args) {
//        LinkedList<String> strings = new LinkedList<>();
//        for (int i = 0; i < 10; i++) {
//            strings.add(i+"");
//        }
//        String first = strings.getFirst();
//        String s = strings.removeFirst();
//        strings.removeFirstOccurrence("2");
//        strings.remove("2");
//        System.out.println(strings);
        ArrayDeque<String> strings = new ArrayDeque<>();
        for (int i = 0; i < 18; i++) {
            strings.add(i + "");
        }
        strings.addFirst("a");
        strings.pollFirst();
        System.out.println(strings.size());
    }
}
