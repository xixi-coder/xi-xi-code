//package com.example.xixi.report;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.zaxxer.hikari.HikariDataSource;
//import org.apache.commons.collections4.CollectionUtils;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class GetDataForDyDataSource {
//    public static void main(String[] args) {
//
//        MyDynamicDataSource.initDataSource();
//
//        DruidDataSource dev = MyDynamicDataSource.DataSourceCache.get("dev");
//        try {
//            Connection connection = dev.getConnection();
//            Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//            statement.setFetchSize(100);
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM ccms_test.shsr_base_param limit 10");
//            while (resultSet.next()) {
//                ResultSetMetaData metaData = resultSet.getMetaData();
//                int columnCount = metaData.getColumnCount();
//                if (CollectionUtils.isEmpty(head)) {
//                    for (int i = 1; i <= columnCount; i++) {
//                        System.out.println(i);
//                        String columnName = metaData.getColumnName(i);
//                        System.out.println(columnName);
//                        head.add(Collections.singletonList(columnName));
//                    }
//                }
//                ArrayList<List<Object>> singleData = new ArrayList<>();
//                ArrayList<Object> objects = new ArrayList<>();
//                for (int i = 1; i <= columnCount; i++) {
//                    Object object = resultSet.getObject(i);
//                    objects.add(object);
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//}
