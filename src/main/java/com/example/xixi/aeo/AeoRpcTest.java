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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;


public class AeoRpcTest {


    public static void main(String[] args) {
        File file = new File("/Users/shiheng/Desktop/rec-aeo/report_bsb_declare_record_202307.json");
        File file2 = new File("/Users/shiheng/Desktop/rec-aeo/所有的备案清单回执报文.json");
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


        List<AeoObj> aeoObjs = JSONArray.parseArray(s, AeoObj.class);
        List<AeoJson> receiptJSONs = JSONArray.parseArray(s2, AeoJson.class);

//        HashSet<String> billList = new HashSet<>();
        HashSet<String> receiptList = new HashSet<>();

        ArrayList<AeoJson> billDataList = new ArrayList<>();
        ArrayList<AeoJson> receiptDataList = new ArrayList<>();
        int i = 0;
        for (AeoObj aeoObj : aeoObjs) {
            String origin_data = "";
            try {
                origin_data = aeoObj.getOrigin_data();
                //替换字符串中的]"
                //替换字符串中的\
                origin_data = StringEscapeUtils.unescapeJava(origin_data);
                origin_data = origin_data.replaceAll("\\\\", "");
                origin_data = origin_data.replaceAll("}\"", "}");
                origin_data = origin_data.replaceAll("\"\\[", "[");
                origin_data = origin_data.replaceAll("\"\\{", "{");
                origin_data = origin_data.replaceAll("}\"", "}");
                origin_data = origin_data.replaceAll("]\"", "]");
                AeoOriginData aeoOriginData = JSON.parseObject(origin_data, AeoOriginData.class);
                aeoObj.setOrigin_data(JSON.toJSONString(aeoOriginData));
                String dataStr = aeoOriginData.getData();
                JSONObject data = JSON.parseObject(dataStr);
                String type = aeoOriginData.getType();
                //报关单数据
                if ("1".equals(type)) {
                    JSONObject decDetail = data.getJSONObject("decDetail");
                    if (Objects.isNull(decDetail)) {
                        continue;
                    }
                    JSONObject preDecHeadVo = decDetail.getJSONObject("preDecHeadVo");
                    String cusCiqNo = preDecHeadVo.getString("cusCiqNo");
//                "ciqIEFlag": "I"
                    if ("I".equals(preDecHeadVo.getString("ciqIEFlag")) && !seqNos.contains(cusCiqNo)) {
                        AeoJson aeoJson = new AeoJson();
                        aeoJson.setId(aeoObj.getId());
                        AeoOriginDataJSON dataJSON = new AeoOriginDataJSON();
                        dataJSON.setType(type);
                        dataJSON.setData(data);
                        aeoJson.setOrigin_data(dataJSON);
                        billDataList.add(aeoJson);
                        seqNos.add(cusCiqNo);
                    }
//                if (seqNos.contains(cusCiqNo)) {
//                    if (!billList.contains(cusCiqNo)) {
//                        billDataList.add(aeoObj);
//                    }
//                    billList.add(cusCiqNo);
//                    //如果set中不存在，则加入json文件里
//
//                }
                }

            } catch (Exception e) {
                i++;
                System.out.println("数据格式错误" + aeoObj.getId());
                System.out.println(origin_data);
                System.out.println(Throwables.getStackTraceAsString(e));

            }

        }
        for (AeoJson receiptJSON : receiptJSONs) {
            //        //回执数据
            AeoOriginDataJSON originData = receiptJSON.getOrigin_data();
            String type = originData.getType();
            JSONObject data = originData.getData();
            if ("2".equals(type)) {
                JSONArray rows = data.getJSONArray("rows");
                if (Objects.isNull(rows)) {
                    continue;
                }
                JSONObject jsonObject = rows.getJSONObject(0);
                String cusCiqNo = jsonObject.getString("cusCiqNo");
                if (seqNos.contains(cusCiqNo)) {
                    //如果set中不存在，则加入json文件里
                    if (!receiptList.contains(cusCiqNo)) {
                        receiptDataList.add(receiptJSON);
                    }
                    receiptList.add(cusCiqNo);
                }
            }
        }


        File file1 = new File("/Users/shiheng/Desktop/rec-aeo/进口备案清单报文.json");
        if (!file1.exists()) {
            try {
                boolean newFile = file1.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        File file3 = new File("/Users/shiheng/Desktop/rec-aeo/进口备案清单回执报文.json");
        if (!file3.exists()) {
            try {
                boolean newFile = file3.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            FileUtils.writeByteArrayToFile(file1, JSON.toJSONBytes(billDataList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileUtils.writeByteArrayToFile(file3, JSON.toJSONBytes(receiptDataList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("数据格式错误数量{}" + i);

    }


}
