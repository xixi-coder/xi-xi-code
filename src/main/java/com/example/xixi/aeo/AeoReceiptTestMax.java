package com.example.xixi.aeo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.xixi.aeo.bill.AeoJson;
import com.example.xixi.aeo.bill.AeoOriginDataJSON;
import com.example.xixi.aeo.receipt.AeoReceipt;
import com.example.xixi.aeo.receipt.AeoReceiptData;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class AeoReceiptTestMax {
    public static void main(String[] args) {

        File file = new File("/Users/shiheng/Desktop/rec-aeo/I20200000462414729-receipt.json");

        String s = null;
        try {
            s = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<AeoJson> aeoJsons = JSONArray.parseArray(s, AeoJson.class);
        AeoJson aeoJson = aeoJsons.get(0);
        AeoOriginDataJSON originData = aeoJson.getOrigin_data();
        JSONObject data = originData.getData();
        AeoReceiptData aeoReceiptData = JSON.parseObject(data.toJSONString(), AeoReceiptData.class);
        List<AeoReceipt> rows = aeoReceiptData.getRows();
        //找rows中最大的noticeDate
        AeoReceipt maxNoticeDate = rows.stream().max(Comparator.comparing(AeoReceipt::getNoticeDate)).get();
        System.out.println(JSON.toJSONString(maxNoticeDate));


    }

}