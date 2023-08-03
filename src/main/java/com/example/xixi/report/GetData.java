package com.example.xixi.report;

import java.sql.*;
import java.util.*;
import java.util.stream.StreamSupport;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.xixi.copy.ObejctB;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

@Slf4j
public class GetData {


    public static void main(String[] args) {
        MyDynamicDataSource.initDataSource();
        //todo 解决n条数据写入缓存后自动flush到磁盘
        try (Connection connection = MyDynamicDataSource.DataSourceCache.get("test").getConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            statement.setFetchSize(100);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ccms_test.shsr_base_param limit 10");
            String fileName = "/Users/shiheng/Desktop/reports/test2.xlsx";

            List<List<String>> head = new ArrayList<>();
            while (resultSet.next()) {
                // 处理每一行数据
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                if (CollectionUtils.isEmpty(head)) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.println(i);
                        String columnName = metaData.getColumnName(i);
                        System.out.println(columnName);
                        head.add(Collections.singletonList(columnName));
                    }
                }
                ArrayList<List<Object>> singleData = new ArrayList<>();
                ArrayList<Object> objects = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    Object object = resultSet.getObject(i);
                    objects.add(object);
                }
                try (
                        ExcelWriter excelWriter = EasyExcel.write(fileName).registerConverter(new TimeStampConverter()).build();
                ) {
                    WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1").build();
                    if (CollectionUtils.isEmpty(writeSheet.getHead())){
                        writeSheet.setHead(head);
                    }
                    singleData.add(objects);
                    System.out.println(JSONObject.toJSONString(singleData));
                    excelWriter.write(singleData, writeSheet);
                    excelWriter.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


