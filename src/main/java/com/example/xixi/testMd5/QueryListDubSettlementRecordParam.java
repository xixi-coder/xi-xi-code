package com.example.xixi.testMd5;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: zhuody
 * @Date: 2020/3/19 9:34
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class QueryListDubSettlementRecordParam implements Serializable {
    /**
     * 费用状态     未审批0（可以修改，不可同步），已审批1（不可修改，可以同步），反审中2（不可修改，不可同步）
     */
    private Integer settlementStatus;
    /**
     * 审批用户名称
     */
    private String checkUser;
    /**
     * 航次/航班号
     */
    private String trafToolNo;
    /**
     * QG编号
     */
    private String qgId;

    /**
     * YS编号
     */
    private String ysId;
    /**
     * 0--收发货人企业名称；1--收发货人海关十位编码；2--收发货人社会信用代码；3--收发货人检验检疫编码；
     */
    @JsonProperty("oType")
    private String oType;
    /**
     * 对应的值
     */
    @JsonProperty("oValue")
    private String oValue;
    /**
     * 查验状态   有查验1、无查验0，全部null
     */
    private Integer checkState;
    /**
     * 销售人员名称--客户经理
     */
    private String salemanName;

    /**
     * 收发货人企业名称
     */
    private String ownerName;

    /**
     * 收发货人海关十位编码
     */
    private String ownerCode;

    /**
     * 收发货人社会信用代码
     */
    private String ownerNewCode;

    /**
     * 收发货人检验检疫编码
     */
    private String consigneeCode;

    /**
     * 拼箱处理方式  0未处理，1应收均摊，应付在本条，2应收应付在本条，3应收应付都不在本条，4应收均摊，应付不在本条
     */
    private Integer lclDealWay;
    /**
     * 出境关别
     */
    private String customsCode;
    /**
     * 接单日期
     */
    private Date orderTime;
    /**
     * 接单日期大于等于
     */
    private String orderTimeGe;
    /**
     * 接单日期小于等于
     */
    private String orderTimeLe;

    /**
     * 回执状态： -1 已发送  0:重新编辑，1:已传财务系统，2:未核销，3:部分核销，4:已核销
     */
    private Integer settlementRtnStatus;

    /**
     * 当前用户企业编码
     */
    private String companyCode;

    /**
     * 作业号，报关行主体
     */
    private String workNo;

    private Long id;

    /**
     * 记录id
     */
    private String recordId;

    /**
     * 易豹业务编号
     */
    private String ebaoSeq;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 同步状态   已同步1、未同步0
     */
    private Integer settlementSynStatus;

    /**
     * 导出状态   未导出0  已导出1
     */
    private Integer exportState;


    /**
     * 报关单号
     */
    private String entryNo;


    /**
     * 提单号
     */
    private String billNo;

    /**
     * 客服
     */
    private String documenter;

    /**
     * 经营单位
     */
    private String producerName;

    /**
     * 易豹0/单一窗口1
     */
    private String platform;

    /**
     * 集装箱号
     */
    private String containerNo;

    /**
     * 联系人名称
     */
    private String contactName;

    /**
     * 区域
     */
    private String region;

    /**
     * 申报日期
     */
    private Date declareDate;
    /**
     * 申报日期大于等于
     */
    private String declareDateGe;

    /**
     * 申报日期小于等于
     */
    private String declareDateLe;

    /**
     * 核算日期
     */
    private Date accountingDate;
    /**
     * 核算日期大于等于
     */
    private String accountingDateGe;

    /**
     * 核算日期小于等于
     */
    private String accountingDateLe;


    /**
     * 查验费             已录入1、未录入0
     */
    private Integer checkCost;


    /**
     * edi编号
     */
    private String ediNo;


    /**
     * 创建用户
     */
    private String creatorId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改用户
     */
    private String modifierId;

    /**
     * 删除标记。未删除0，已删除1
     */
    private Integer deletedFlag;

    /**
     * 版本号用于并发控制
     */
    private String version;
}
