package com.example.xixi.import_declare;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadJson {
    public static void main(String[] args) {

        try {

            Workbook wb1 = null;
            InputStream inputStream = Files.newInputStream(Paths.get("/Users/shiheng/Desktop/报关单.xlsx"));
            ArrayList<String> seqNos = Lists.newArrayList();
            wb1 = WorkbookFactory.create(inputStream);
            Sheet sheetAt = wb1.getSheetAt(0);
            int lastRowNum = sheetAt.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                String a = sheetAt.getRow(i).getCell(0).getStringCellValue();
                seqNos.add(a);
            }
            System.out.println(seqNos.size());
            File file = new File("/Users/shiheng/Desktop/bsb_dec_head.json");

            String s = FileUtils.readFileToString(file, Charset.defaultCharset());

            JSONArray objects = JSONArray.parseArray(s);
            System.out.println(objects.size());
            JSONArray array = new JSONArray();

            for (int i = 0; i < objects.size(); i++) {
                JSONObject jsonObject = objects.getJSONObject(i);
                String seq_no = jsonObject.getString("cus_ciq_no");
                if (seqNos.contains(seq_no)){
                    array.add(jsonObject);
                }
            }
            System.out.println(array.size());
            System.out.println(array.get(0));
//            List<String> isbn = JsonPath.read(s, "[*].seq_no");
//            System.out.println(isbn);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
