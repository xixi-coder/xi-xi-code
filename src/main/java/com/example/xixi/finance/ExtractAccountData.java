package com.example.xixi.finance;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ExtractAccountData {
    public static void main(String[] args) {
        String filePath ="/Users/shiheng/Desktop/work/a.json";
        String jsonStr = "";
        try {
            File jsonFile = new File(filePath);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
//            JSONObject object = JSON.parse(jsonStr);
            JSONObject object = JSON.parseObject(jsonStr);
//            ArrayList<JSONObject> jsonObjects = new ArrayList<>();
//            JSONArray array1 = new JSONArray();
//            for (Object o : array) {
//                JSONObject jb = (JSONObject) o;
                JSONArray array1 = object.getJSONObject("data").getJSONArray("records");
//                array1.addAll(jsonArray);
//            }
            System.out.println(array1.size());
//            ArrayList<ReObject> reObjects = new ArrayList<>();
            XSSFWorkbook wb1 = new XSSFWorkbook();
            XSSFSheet sheet = wb1.createSheet();
            int i =0 ;
            int j =0 ;
            for (Object o : array1) {
                JSONObject jb = (JSONObject) o;
                Object settlementObjectName = jb.get("settlementObjectName");
                Object aggregateAmount = jb.get("aggregateAmount");
                XSSFRow row = sheet.createRow(i++);
                XSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(settlementObjectName.toString());
                XSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(aggregateAmount.toString());
//                ReObject reObject = JSON.parseObject(jb.toJSONString(), ReObject.class);
//                if (!StringUtils.isEmpty(reObject.getAggregateAmountCNY())){
//                    reObject.setAggregateAmount1(Double.valueOf(reObject.getAggregateAmountCNY()));
//                }else {
//                    System.out.println(jb.toJSONString());
//                }
//                reObjects.add(reObject);
            }
            FileOutputStream out = new FileOutputStream(new File("/Users/shiheng/Downloads/test123.xlsx"));
            wb1.write(out);
//            ReObject reObject1 = reObjects.get(0);
//            System.out.println(reObject1.getRegion());
//            System.out.println(reObject1.getAccountTime());
//
//            Map<String, List<ReObject>> collect = reObjects.stream().filter(reObject -> Objects.nonNull(reObject.getRegion())).collect(Collectors.groupingBy(ReObject::getRegion));
//            collect.forEach((x, y) -> {
//                        Map<String, List<ReObject>> collect1 = y.stream().filter(reObject -> Objects.nonNull(reObject.getAccountTime())).collect(Collectors.groupingBy(ReObject::getAccountTime));
//                        collect1.forEach((a, b) -> {
//                            System.out.println(x+a);
//                            List<ReObject> collect2 = b.stream().filter(reObject -> Objects.nonNull(reObject.getSettlementRecordNum())).
//                                    sorted(
//                                    Comparator.comparing(ReObject::getSettlementRecordNum).reversed()).limit(3).collect(Collectors.toList());
//                            System.out.println(collect2.stream().map(ReObject::getSettlementRecordNum).collect(Collectors.toList()));
//                            System.out.println(collect2.stream().map(ReObject::getSettlementObjectName).collect(Collectors.toList()));
//
//                        });
//                    }
//            );

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
