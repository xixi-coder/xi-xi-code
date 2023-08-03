package com.example.xixi.import_declare;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadCustomerJson {
    public static void main(String[] args) {

        try {

            Workbook wb1 = null;
            InputStream inputStream = Files.newInputStream(Paths.get("/Users/shiheng/Desktop/客户信息.xlsx"));
            HashMap<String, String> map = new HashMap<>();
            wb1 = WorkbookFactory.create(inputStream);
            Sheet sheetAt = wb1.getSheetAt(0);
            int lastRowNum = sheetAt.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheetAt.getRow(i);
                if (row==null){
                    continue;
                }
                Cell cell0 = row.getCell(0);
                if (cell0==null){
                    continue;
                }
                String a = cell0.getStringCellValue();
                if (StringUtils.isEmpty(a)){
                    continue;
                }
                Cell cell2 = row.getCell(2);
                if (cell2==null){
                    continue;
                }
                String b = cell2.getStringCellValue();
                map.put(a,b);
            }
            System.out.println(JSON.toJSONString(map));

        } catch (Exception e) {

        }
    }
}
