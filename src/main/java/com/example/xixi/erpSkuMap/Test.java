package com.example.xixi.erpSkuMap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : xi-xi
 */
public class Test {
    private static final String dateFormatter1 = "yyyy-MM-dd HH:mm:ss";
    public static void main(String[] args) {
        ArrayList<Test123> test123s = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Test123 test123 = new Test123();
            test123.setA(i+"");
            test123.setB("1");
            test123s.add(test123);
        }
        System.out.println(test123s);
        Map<String, String> collect = test123s.stream().collect(Collectors.toMap(Test123::getA, Test123::getB));
        System.out.println(collect);


    }
}
