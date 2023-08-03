package com.example.xixi.aeo.bill;

import lombok.Data;

import java.io.Serializable;

@Data
public class AeoJson implements Serializable {
    private String id;
    private AeoOriginDataJSON origin_data;
}
