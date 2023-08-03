package com.example.xixi.demoSpi.impl;

import com.example.xixi.demoSpi.Log;

public class Log4j implements Log {
    @Override
    public void log(String info) {
        System.out.println("log4j...."+info);
    }
}
