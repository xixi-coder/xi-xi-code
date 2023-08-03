package com.example.xixi.report;

import com.alibaba.druid.pool.DruidDataSource;

public class DruidConfig {
    private static DruidDataSource dataSource;

    static {
        dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://192.168.3.235:3309?characterEncoding=UTF-8&serverTimezone=Hongkong&useSSL=true");
        dataSource.setUsername("test");
        dataSource.setPassword("123456");
    }

    public static DruidDataSource getDataSource() {
        return dataSource;
    }
}

