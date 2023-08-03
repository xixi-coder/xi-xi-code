package com.example.xixi.excel;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * 处理
 */
public class DealExcel {
    public static void main(String[] args) throws Exception {
//        DealExcel.testXlsx();
//        String date ="2022-07-06 00:00:00";
//        String replace = date.substring(0, 10).replace("-", "");
//        System.out.println(replace);
        HashSet<String> strings = readExcelFirstColumn(new File("/Users/shiheng/Desktop/随附单据.xlsx"));
        System.out.println(strings.toString());
    }

    public static void testXlsx() throws Exception {
        String path = "/Users/shiheng/Downloads/";
        File sea = new File(path + "test41.xlsx");
        //获取输入流
//        HashSet<String> seaEbaos = readExcelFirstColumn(sea);
        File finance = new File(path + "test3.xlsx");
//        HashSet<String> financeEbaos = readExcelFirstColumn(finance);
        Map<String, String> financeMap = readExcelTwoColumn(finance, false);
        Map<String, String> exportMap = readExcelTwoColumn(sea, true);
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<String> list4 = new ArrayList<>();
        ArrayList<String> list5 = new ArrayList<>();
        exportMap.forEach((ebao, date) -> {
            if (financeMap.containsKey(ebao)) {
                if ("20220701".equals(date)) {
                    list1.add(financeMap.get(ebao));
                } else if ("20220702".equals(date)) {
                    list2.add(financeMap.get(ebao));
                } else if ("20220704".equals(date)) {
                    list4.add(financeMap.get(ebao));
                } else if ("20220705".equals(date)) {
                    list5.add(financeMap.get(ebao));
                }
            }
        });
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list4);
        System.out.println(list5);
//        seaEbaos.forEach(
//                ebao->{
//                    if(!financeEbaos.contains(ebao)){
//                        System.out.println(ebao);
//                    }
//                }
//        );
    }

    private static HashSet<String> readExcelFirstColumn(File finance) throws IOException {
        HashSet<String> ebaoList = new HashSet<>();
        InputStream stream3 = new FileInputStream(finance);
        XSSFWorkbook wb1 = new XSSFWorkbook(stream3);
        XSSFSheet sheetAt1 = wb1.getSheetAt(0);
        int lastRowNum1 = sheetAt1.getLastRowNum();
        for (int i = 1; i < lastRowNum1; i++) {
            XSSFRow row = sheetAt1.getRow(i);
            ebaoList.add(row.getCell(0).getStringCellValue());
        }
        return ebaoList;
    }

    private static Map<String, String> readExcelTwoColumn(File finance, boolean dateFlag) throws IOException {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
//        HashSet<String> ebaoList = new HashSet<>();
        InputStream stream3 = new FileInputStream(finance);
        XSSFWorkbook wb1 = new XSSFWorkbook(stream3);
        XSSFSheet sheetAt1 = wb1.getSheetAt(0);
        int lastRowNum1 = sheetAt1.getLastRowNum();
        for (int i = 1; i < lastRowNum1 + 1; i++) {
            XSSFRow row = sheetAt1.getRow(i);
//            ebaoList.add(row.getCell(0).getStringCellValue());
            String dateStr = row.getCell(1).getStringCellValue();
            if (dateFlag) {
                String date = row.getCell(1).getStringCellValue();
                dateStr = date.substring(0, 10).replace("-", "");
                stringStringHashMap.put(row.getCell(0).getStringCellValue(), dateStr);
            } else {
                stringStringHashMap.put(row.getCell(1).getStringCellValue(), row.getCell(0).getStringCellValue());
            }

        }
        return stringStringHashMap;
    }

    private static Set<String> readExcelOneColumn(File file) throws IOException {
        HashSet<String> cusCiqNos = new HashSet<>();
        InputStream stream3 = new FileInputStream(file);
        XSSFWorkbook wb1 = new XSSFWorkbook(stream3);
        XSSFSheet sheetAt1 = wb1.getSheetAt(0);
        int lastRowNum1 = sheetAt1.getLastRowNum();
        for (int i = 1; i < lastRowNum1 + 1; i++) {
            XSSFRow row = sheetAt1.getRow(i);
            String stringCellValue = row.getCell(0).getStringCellValue();
            cusCiqNos.add(stringCellValue);
        }
        return cusCiqNos;
    }
}
