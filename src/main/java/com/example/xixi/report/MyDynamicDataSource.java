package com.example.xixi.report;


import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public class MyDynamicDataSource {



    public static final Map<String, DruidDataSource> DataSourceCache = new ConcurrentHashMap<String, DruidDataSource>();

    /**
     * 初始化DataSource
     * todo 从数据库取数据源信息
     */
    public static void initDataSource() {
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setUrl("jdbc:mysql://192.168.3.235:3309?characterEncoding=UTF-8&serverTimezone=Hongkong&useSSL=true");
        dataSource1.setUsername("test");
        dataSource1.setPassword("123456");
        //驱动表
        DataSourceCache.put("dev", dataSource1);
        String dataSourceName2 = "test";
        //排序条件
        DruidDataSource dataSource2 = new DruidDataSource();
        dataSource2.setUrl("jdbc:mysql://192.168.8.168:3306/crm_test?characterEncoding=UTF-8&serverTimezone=Hongkong&useSSL=true");
        dataSource2.setUsername("dubtest02");
        dataSource2.setPassword("Dubtest_0728");
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        DataSourceCache.put(dataSourceName2, dataSource1);
    }
}

