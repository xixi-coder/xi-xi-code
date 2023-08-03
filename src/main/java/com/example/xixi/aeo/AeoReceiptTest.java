package com.example.xixi.aeo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.xixi.aeo.bill.AeoJson;
import com.example.xixi.aeo.bill.AeoOriginDataJSON;
import com.example.xixi.aeo.receipt.AeoReceipt;
import com.example.xixi.aeo.receipt.AeoReceiptData;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class AeoReceiptTest {
    public static void main(String[] args) {

        File file = new File("/Users/shiheng/Desktop/rec-aeo/Result_5.json");

//        ArrayList<String> receiptSeqNos = Lists.newArrayList();

        String s = null;
        try {
            s = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<AeoReceipt> aeoObjs = JSONArray.parseArray(s, AeoReceipt.class);
        ArrayList<AeoJson> list = new ArrayList<>();
        Map<String, List<AeoReceipt>> collectMap = aeoObjs.stream().collect(Collectors.groupingBy(AeoReceipt::getCusCiqNo));
        /**
         * [{
         *     "id": 1645310541655003138,
         *     "business_type": "DEC",
         *     "data_source": "DONG_FANG_KOU_AN",
         *     "origin_data": {
         *         "data": {
         *             "total": "5",
         *             "rows": []
         *         },
         *         "type": "2"
         *     },
         *     "source_way": 2,
         *     "create_time": "2023-04-10 14:19:36",
         *     "modify_time": "2023-04-10 14:19:36",
         *     "task_record_id": null
         * }]
         */
        collectMap.forEach((k, v) -> {
            if (CollectionUtils.isNotEmpty(v)) {
                AeoReceiptData aeoReceiptData = new AeoReceiptData();
                aeoReceiptData.setTotal(String.valueOf(v.size()));
                aeoReceiptData.setRows(v);
                String jsonString = JSON.toJSONString(aeoReceiptData);
                JSONObject jsonObject = JSON.parseObject(jsonString);

                AeoOriginDataJSON aeoOriginDataJSON = new AeoOriginDataJSON();
                aeoOriginDataJSON.setType("2");
                aeoOriginDataJSON.setData(jsonObject);

                AeoJson aeoJson = new AeoJson();
                aeoJson.setId(UUID.randomUUID().toString());
                aeoJson.setOrigin_data(aeoOriginDataJSON);

                list.add(aeoJson);
            }
        });
        File file2 = new File("/Users/shiheng/Desktop/rec-aeo/所有的备案清单回执报文.json");
        if (!file2.exists()) {
            try {
                boolean newFile = file2.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            FileUtils.writeByteArrayToFile(file2, JSON.toJSONBytes(list));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
