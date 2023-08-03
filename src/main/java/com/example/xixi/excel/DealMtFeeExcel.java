//package com.example.xixi.excel;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.apache.ibatis.annotations.ConstructorArgs;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * 码头缴费的处理
// */
//public class DealMtFeeExcel {
//    public static void main(String[] args) throws Exception {
//        DealMtFeeExcel.testXlsx();
//
//    }
//
//    public static void testXlsx() throws Exception {
//        File jsonFile = new File("/Users/shiheng/Desktop/work/mdb.json");
//        FileReader fileReader = new FileReader(jsonFile);
//
//        Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
//        int ch = 0;
//        StringBuilder sb = new StringBuilder();
//        while ((ch = reader.read()) != -1) {
//            sb.append((char) ch);
//        }
//        fileReader.close();
//        reader.close();
//        String jsonStr = sb.toString();
//        ArrayList<MtFee> list = new ArrayList<>();
//        JSONArray array = JSON.parseArray(jsonStr);
//        int i = 0;
//        for (Object o : array) {
//            JSONObject jb = (JSONObject) o;
//            MtFee mtFee = JSONObject.parseObject(jb.toJSONString(), MtFee.class);
//            mtFee.setId(++i + "");
//            list.add(mtFee);
//        }
//        //按照ebao分组
//        Map<String, List<MtFee>> listMap = list.stream().collect(Collectors.groupingBy(MtFee::getEbaoSeq));
//        ArrayList<MtFee> finalList = new ArrayList<>();
//        listMap.forEach((k, v) -> {
//            if (v.size() >= 2) {
//                v = v.stream().filter(mtFee -> Objects.nonNull(mtFee.getTotalAmount())
//                        && !mtFee.getTotalAmount().equals("0")
//                ).collect(Collectors.toList());
//            }
//            finalList.addAll(v);
//        });
//        System.out.println(list.size());
//        System.out.println(finalList.size());
//        List<MtFee> collect = finalList.stream().sorted(Comparator.comparing(MtFee::getOperationDate)).collect(Collectors.toList());
//
////        for (int i1 = 0; i1 < 10; i1++) {
////            System.out.println(collect.get(i1));
////        }
//
//        XSSFWorkbook wb1 = new XSSFWorkbook();
//        XSSFSheet sheet1 = wb1.createSheet();
//        for (int i1 = 0; i1 < collect.size(); i1++) {
//            MtFee mtFee = collect.get(i1);
//            XSSFRow row = sheet1.createRow(i1);
//            row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(mtFee.getEbaoSeq());
//            row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue(mtFee.getBillNo());
//            row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue(mtFee.getTotalAmount());
//            row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue(mtFee.getOperationDate());
//            row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue(mtFee.getReturnFlag());
//        }
//        File file = new File("/Users/shiheng/Desktop/work/mdb.xlsx");
//        wb1.write(new FileOutputStream(file));
//    }
//
//
//}
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//class MtFee {
//    private String id;
//    private String returnFlag;
//    private String billNo;
//    private String totalAmount;
//    private String operationDate;
//    private String ebaoSeq;
//}
