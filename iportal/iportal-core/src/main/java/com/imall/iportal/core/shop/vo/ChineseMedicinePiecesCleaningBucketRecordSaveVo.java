package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ou on 2017/7/7.
 */
public class ChineseMedicinePiecesCleaningBucketRecordSaveVo {

    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;
    /**
     * 清斗 日期
     */
    @NotNull
    private Date cleaningBucketDate;
    /**
     * 清斗 数量
     */
    @NotNull
    private Long cleaningBucketQuantity;
    /**
     * 商品 编码
     */
    @NotBlank
    @Length(max = 32)
    private String goodsCode;
    /**
     * 商品 名称
     */
    @NotBlank
    @Length(max = 128)
    private String goodsNm;
    /**
     * 商品 拼音
     */
    @NotBlank
    @Length(max = 64)
    private String goodsPinyin;
    /**
     * 通用 名称
     */
    @NotBlank
    @Length(max = 64)
    private String commonNm;
    /**
     * 规格
     */
    @NotBlank
    @Length(max = 32)
    private String spec;
    /**
     * 剂型
     */
    private String dosageForm;
    /**
     * 单位
     */
    @NotBlank
    @Length(max = 32)
    private String unit;
    /**
     * 生产 厂商
     */
    @NotBlank
    @Length(max = 64)
    private String produceManufacturer;
    /**
     * 批准文号
     */
    @NotBlank
    @Length(max = 64)
    private String approvalNumber;
    /**
     * 产地
     */
    private String productionPlace;
    /**
     * 批号
     */
    @NotBlank
    @Length(max = 32)
    private String batch;
    /**
     * 生产 时间
     */
    @NotNull
    private Date produceTime;
    /**
     * 有效期至
     */
    @NotNull
    private Date validDate;
    /**
     * 货位
     */
    @NotBlank
    @Length(max = 32)
    private String storageSpaceNm;
    /**
     * 清斗 人 姓名
     */
    @NotBlank
    @Length(max = 32)
    private String cleaningBucketManNm;
    /**
     * 审核 人 ID
     */
    @NotNull
    @Length(max = 19)
    private Long approveManId;
}
