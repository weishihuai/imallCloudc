package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/8/1.
 * 药品锁定保存时 商品集合 VO对象
 */
public class DrugLockGoodsSaveVo {
    /**
     * 锁定数量
     */
    @NotNull
    private Long lockQuantity;
    /**
     * 锁定原因
     */
    private String lockReason;
    /**
     * 备注
     */
    private String remark;
    /**
     * 商品批次ID
     */
    @NotNull
    private Long batchId;
    /**
     * 商品 id
     */
    @NotNull
    private Long goodsId;
    /**
     * skuId
     */
    @NotNull
    private Long skuId;
    /**
     * 商品名称
     */
    @NotBlank
    @Length(max = 32)
    private String goodsNm;
    /**
     * 商品通用名称
     */
    @NotBlank
    private String commonNm;
    /**
     * 商品编码
     */
    @NotBlank
    private String goodsCode;
    /**
     * 商品拼音码
     */
    @NotBlank
    private String pinyin;

    public Long getLockQuantity() {
        return lockQuantity;
    }

    public void setLockQuantity(Long lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public String getLockReason() {
        return lockReason;
    }

    public void setLockReason(String lockReason) {
        this.lockReason = lockReason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getCommonNm() {
        return commonNm;
    }

    public void setCommonNm(String commonNm) {
        this.commonNm = commonNm;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }
}
