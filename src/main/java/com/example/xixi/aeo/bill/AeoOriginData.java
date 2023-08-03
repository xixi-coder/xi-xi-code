package com.example.xixi.aeo.bill;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class AeoOriginData implements Serializable {

        private String type;

        private String data;
}
