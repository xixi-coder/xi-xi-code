package com.example.xixi.excel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 处理
 */
public class DealExcelSkMtFee {
    public static void main(String[] args) throws Exception {
        DealExcelSkMtFee.testXlsx();
//        String date ="2022-07-06 00:00:00";
//        String replace = date.substring(0, 10).replace("-", "");
//        System.out.println(replace);
    }

    public static void testXlsx() throws Exception {
        String path = "/Users/shiheng/Downloads/";
        File excel = new File(path + "cctt12.xlsx");
        File json = new File(path + "mongo234.json");
        String content = FileUtils.readFileToString(json, "UTF-8");
        JSONArray array = JSONArray.parseArray(content);
        HashMap<String, String> jsonMap = new HashMap<>();
        for (Object o : array) {
            JSONObject jb = (JSONObject) o;
            String billNo = (String) jb.get("billNo");
            String totalAmount = (String) jb.get("totalAmount");
            if (jsonMap.containsKey(billNo)) {
                String s = jsonMap.get(billNo);
                totalAmount = Integer.parseInt(s) + Integer.parseInt(totalAmount) + "";
            }
            jsonMap.put(billNo, totalAmount);
        }
        Map<String, String> excelMap = readExcelTwoColumn(excel);

        TreeMap<String, String> excelTreeMap = new TreeMap<>(excelMap);
        TreeMap<String, String> jsonTreeMap = new TreeMap<>(jsonMap);
//        System.out.println(JSONObject.toJSONString(excelTreeMap));
//        System.out.println(JSONObject.toJSONString(jsonTreeMap));
        excelTreeMap.forEach(
                (k, v) -> {
                    if (jsonTreeMap.containsKey(k)) {
                        String s = jsonTreeMap.get(k);
                        if (!v.equals(s)) {
                            System.out.println("金额不等-"+k+"-json中金额-"+s+"-excel中金额"+v);
                        }
                    }else {
                        System.out.println("excel中存在json不存在-"+k);
                    }
                }
        );
        System.out.println("-------------------");
        jsonTreeMap.forEach((k, v) -> {
            if (excelTreeMap.containsKey(k)) {
                String s = excelTreeMap.get(k);
                if (!v.equals(s)) {
                    System.out.println("金额不等-"+k+"-json中金额-"+v+"-excel中金额"+s);
                }
            }else {
                System.out.println("json中存在excel不存在-"+k);
            }
        });
    }

//EK42208230129、EE42208233147 已缴费，易豹上还是未缴费
    private static Map<String, String> readExcelTwoColumn(File finance) throws IOException {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        InputStream stream3 = new FileInputStream(finance);
        XSSFWorkbook wb1 = new XSSFWorkbook(stream3);
        XSSFSheet sheetAt1 = wb1.getSheetAt(0);
        int lastRowNum1 = sheetAt1.getLastRowNum();
        for (int i = 1; i < lastRowNum1; i++) {
            XSSFRow row = sheetAt1.getRow(i);
            if (Objects.isNull(row)) {
                System.out.println(i);
            }
            String key = row.getCell(1).getStringCellValue();
            String val = row.getCell(6).getStringCellValue();
            if (stringStringHashMap.containsKey(key)) {
                String s = stringStringHashMap.get(key);
                int i1 = Integer.parseInt(s);
                int i2 = i1 + Integer.parseInt(val);
                val = i2 + "";
            }
            stringStringHashMap.put(key, val);
        }
        return stringStringHashMap;
    }
}
