package com.example.xixi.javaBasic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author : xi-xi
 */
public class TestMap {
    public static void main(String[] args) {
        HashMap<String, Integer> prices = new HashMap<>();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        Map<Integer, String> collect = strings.stream().collect(Collectors.toMap(s -> s.length(), Function.identity()));

        // 往 HashMap 插入映射
        prices.put("Shoes", 200);
        prices.put("Bag", 300);
        prices.put("Pant", 150);
        prices.put("Shirt", 1000);
        System.out.println("HashMap: " + prices);

        int returnedValue = prices.merge("Shirt", 100, Integer::sum);
        System.out.println("Price of Shirt: " + returnedValue);

        // 输出更新后的 HashMap
        System.out.println("Updated HashMap: " + prices);
    }
}
