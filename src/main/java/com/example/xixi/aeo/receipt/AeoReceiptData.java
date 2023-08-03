package com.example.xixi.aeo.receipt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AeoReceiptData {

//    "total": "5",
//            *             "rows": []
    private String total;
    private List<AeoReceipt> rows;
}
