package com.example.xixi.javaBasic;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author : xi-xi
 */
public class TestMapCompute {
    public static void main(String[] args) {
//        String str = "hello java, i am vary happy! nice to meet you";
//
//        // jdk1.8的写法
//        HashMap<Character, Integer> result2 = new HashMap<>(32);
//        for (int i = 0; i < str.length(); i++) {
//            char curChar = str.charAt(i);
//            result2.compute(curChar, (k, v) -> {
//                if (v == null) {
//                    v = 1;
//                } else {
//                    v += 1;
//                }
//                return v;
//            });
//        }
//        System.out.println(JSON.toJSONString(result2));
        // 求一个数的平方
        Function<Integer, Integer> fun1;
        fun1 = arg -> arg * arg;
        Integer apply = fun1.apply(10);
        // 100
        System.out.println(apply);

        // 求输入两个的和
        BiFunction<Integer, Integer, Integer> fun2 = Integer::sum;
        Integer sum = fun2.apply(10, 20);
        // 30
        System.out.println(sum);

    }
}
