package com.example.xixi.aeo.bill;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class AeoOriginDataJSON implements Serializable {

        private String type;

        private JSONObject data;
}
