package com.example.xixi.syncData;


import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class MyRunnable implements Runnable{

    private  String url ="https://declare.smartebao.com/dub-finance/dubfinance/dub-settlement-record/sync";
    private List<String> data;

    public MyRunnable(List<String> s) {
        this.data = s;
    }
    @Override
    public void run() {
        System.out.println(data.toString());
        JSONObject param =new JSONObject();
//        System.out.println(data.size()+"---"+data.get(data.size() - 1));
        param.put("eBaoSeq",data);
        String result = HttpClientUtil.doPost(url, param.toJSONString());
        System.out.println(result);
    }




}
