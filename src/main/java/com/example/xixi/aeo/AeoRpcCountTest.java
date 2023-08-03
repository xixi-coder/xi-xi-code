package com.example.xixi.aeo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.xixi.aeo.bill.AeoJson;
import com.example.xixi.aeo.bill.AeoObj;
import com.example.xixi.aeo.bill.AeoOriginData;
import com.example.xixi.aeo.bill.AeoOriginDataJSON;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class AeoRpcCountTest {


    public static void main(String[] args) {
        File file = new File("/Users/shiheng/Desktop/rec-aeo/进口备案清单报文.json");
        File file2 = new File("/Users/shiheng/Desktop/rec-aeo/进口备案清单回执报文.json");
        ArrayList<String> seqNos = Lists.newArrayList();
//        ArrayList<String> receiptSeqNos = Lists.newArrayList();

        String s = null;
        String s2 = null;
        try {
            s = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            s2 = FileUtils.readFileToString(file2, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        List<AeoJson> aeoObjs = JSONArray.parseArray(s, AeoJson.class);
        List<AeoJson> receiptJSONs = JSONArray.parseArray(s2, AeoJson.class);
        HashSet<String> billCusCiqNos = new HashSet<>();
        HashSet<String> receiptCusCiqNos = new HashSet<>();
        for (AeoJson aeoObj : aeoObjs) {
            JSONObject data = aeoObj.getOrigin_data().getData();
            JSONObject jsonObject = data.getJSONObject("decDetail");
            JSONObject jsonObject1 = jsonObject.getJSONObject("preDecHeadVo");
            String cusCiqNo = jsonObject1.getString("entryId");
            billCusCiqNos.add(cusCiqNo);
        }
        for (AeoJson aeoObj : receiptJSONs) {
            JSONObject data = aeoObj.getOrigin_data().getData();
            JSONArray jsonArray = data.getJSONArray("rows");
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String cusCiqNo = jsonObject.getString("entryId");
            receiptCusCiqNos.add(cusCiqNo);
        }
        System.out.println(billCusCiqNos.size());
        System.out.println(receiptCusCiqNos.size());
        //找出billCusCiqNos中不在receiptCusCiqNos中的
        Set<String> collect = billCusCiqNos.stream().filter(a -> !receiptCusCiqNos.contains(a)).collect(Collectors.toSet());
        System.out.println(collect.size());
        System.out.println(collect);
        System.out.println(collect.stream().collect(Collectors.joining("','")));

        Set<String> collect2 = billCusCiqNos.stream().filter(a -> receiptCusCiqNos.contains(a)).collect(Collectors.toSet());
        System.out.println(collect2.size());
        System.out.println(collect2);

        //找出receiptCusCiqNos中不在billCusCiqNos中的
        Set<String> collect1 = receiptCusCiqNos.stream().filter(a -> !billCusCiqNos.contains(a)).collect(Collectors.toSet());
        System.out.println(collect1.size());
        System.out.println(collect1);


    }


}
