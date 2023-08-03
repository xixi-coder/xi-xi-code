package com.example.xixi.juc.volatiled;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author : xi-xi
 */
public class TestVolatile {

    public static volatile int counter = 1;

    public static void main(String[] args){


        counter = 2;
        System.out.println(counter);
    }

}
