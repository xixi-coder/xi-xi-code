package com.example.xixi.juc.concurrentHashMap;

import sun.security.util.Length;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author : xi-xi
 */
public class TestFile {

    private static  final  String  a ="qwertyuiopasdfghjklzxcvbnm";
    public static void main(String[] args) {
        int length = a.length();
        int count =200;
        ArrayList<String> list = new ArrayList<>(length * 200);
        for (int i = 0; i < length; i++) {
            char ch = a.charAt(i);
            for (int i1 = 0; i1 < count; i1++) {
                list.add(String.valueOf(ch));
            }
        }
        Collections.shuffle(list);
        for (int i = 0; i < 26; i++) {
            try (
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream("tmp" + (i + 1) + ".txt")))){
                String collect = String.join("\n", list.subList(i * count, (i + 1) * count));
                printWriter.println(collect);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }
}
