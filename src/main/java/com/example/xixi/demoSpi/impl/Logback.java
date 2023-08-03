package com.example.xixi.demoSpi.impl;

import com.example.xixi.demoSpi.Log;

public class Logback implements Log {
    @Override
    public void log(String info) {
        System.out.println("Logback...."+info);
    }
}