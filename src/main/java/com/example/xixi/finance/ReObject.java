package com.example.xixi.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

    /**
     * @Description 应收账单导出excel类
     * @Author shenl
     * @Date 2021/3/22 10:43
     * @Version
     **/
    @Data
    @EqualsAndHashCode(callSuper = false)
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public class ReObject implements Serializable {

        /**
         * 唯一id
         */
        private Long id;

        /**
         * 对账单号
         */
 
        private String accountId;

        /**
         * 账单月份（精确到月）
         */
      
        private String accountTime;

        /**
         * 易豹编号
         */
 
        private String ebaOSeq;

        /**
         * 提运单号
         */
    
        private String billNo;

        /**
         * 结算对象
         */
        private Long settlementObjectId;

        /**
         * 结算对象名称
         */
        private String settlementObjectName;


        private String aggregateAmountCNY;

        private Double aggregateAmount1;


        private String region;

        private Integer settlementRecordNum;



    }
    
