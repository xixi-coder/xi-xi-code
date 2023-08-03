package com.example.xixi.aeo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.xixi.aeo.bill.AeoJson;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class AeoRpcCusCiqNoTest {


    public static void main(String[] args) {
        File file = new File("/Users/shiheng/Desktop/rec-aeo/进口备案清单报文.json");
//        File file2 = new File("/Users/shiheng/Desktop/rec-aeo/进口备案清单回执报文.json");
        ArrayList<String> seqNos = Lists.newArrayList();
//        ArrayList<String> receiptSeqNos = Lists.newArrayList();

        String s = null;
        String s2 = null;
        try {
            s = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        List<AeoJson> aeoObjs = JSONArray.parseArray(s, AeoJson.class);
//        List<AeoJson> receiptJSONs = JSONArray.parseArray(s2, AeoJson.class);
        HashSet<String> billCusCiqNos = new HashSet<>();
        HashSet<String> receiptCusCiqNos = new HashSet<>();
        for (AeoJson aeoObj : aeoObjs) {
            JSONObject data = aeoObj.getOrigin_data().getData();
            JSONObject jsonObject = data.getJSONObject("decDetail");
            JSONObject jsonObject1 = jsonObject.getJSONObject("preDecHeadVo");
            String cusCiqNo = jsonObject1.getString("cusCiqNo");
            billCusCiqNos.add(cusCiqNo);
        }

        System.out.println(billCusCiqNos.size());
        System.out.println(billCusCiqNos);




    }


}
