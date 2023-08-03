package com.example.xixi.aeo.receipt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AeoReceipt implements Serializable {

//    select id,cus_ciq_no    as cusCiqNo,
//    entry_id      as
//    entryId,
//    notice_date   as noticeDate,
//    channel,
//    note,
//    custom_master as customMaster,
//    i_e_date      as ieDate,
//    d_date        as dDate
//    from bsb_dec_result;
    private String cusCiqNo;
    private String entryId;
    private String noticeDate;
    private String channel;
    private String note;
    private String customMaster;
    private String ieDate;
    private String dDate;
}
