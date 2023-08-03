package com.example.xixi.aeo.bill;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class AeoObj implements Serializable {
    private String id;
    private String origin_data;
}
