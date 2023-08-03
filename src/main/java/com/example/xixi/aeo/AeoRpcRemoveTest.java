package com.example.xixi.aeo;

import com.alibaba.fastjson.JSON;
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


public class AeoRpcRemoveTest {


    public static void main(String[] args) {
        File file = new File("/Users/shiheng/Desktop/rec-aeo/进口备案清单报文.json");
        ArrayList<String> entryIds = Lists.newArrayList("310520211059950660", "311620201169993456", "310520201059967887", "299120211000019038", "311720201179991524", "310520201059981108", "310520201059958051", "310120231019967757", "310320201039989930", "310520211059992500", "310520211059989734");

        String s = null;

        try {
            s = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        List<AeoJson> aeoObjs = JSONArray.parseArray(s, AeoJson.class);
        ArrayList<AeoJson> list = new ArrayList<>();
        for (AeoJson aeoObj : aeoObjs) {
            JSONObject data = aeoObj.getOrigin_data().getData();
            JSONObject jsonObject = data.getJSONObject("decDetail");
            JSONObject jsonObject1 = jsonObject.getJSONObject("preDecHeadVo");
            String cusCiqNo = jsonObject1.getString("entryId");
            if (!entryIds.contains(cusCiqNo)) {
                list.add(aeoObj);
            }
        }

        File file1 = new File("/Users/shiheng/Desktop/rec-aeo/进口备案清单报文-11.json");
        if (!file1.exists()) {
            try {
                boolean newFile = file1.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(list.size());
        try {
            FileUtils.writeByteArrayToFile(file1, JSON.toJSONBytes(list));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }


}
