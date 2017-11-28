package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by ou on 2017/7/6.
 */
public class GoodsSplitZeroSaveVo {
    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;
    /**
     * 商品 ID
     */
    @NotNull
    private Long goodsId;
    /**
     * SKU ID
     */
    @NotNull
    private Long skuId;
    /**
     * 商品 批次 ID
     */
    @NotNull
    private Long goodsBatchId;
    /**
     * 拆零 数量
     */
    @NotNull
    private Long splitZeroQuantity;
    /**
     * 拆后 小 包装 数量
     */
    @NotNull
    private Long splitSmallPackageQuantity;
    /**
     * 用法
     */
    @Length(max = 64)
    private String usageText;
    /**
     * 用量
     */
    @Length(max = 32)
    private String dosage;
    /**
     * 经办人
     */
    @NotBlank
    @Length(max = 32)
    private String operator;
    /**
     * 商品 名称
     */
    @NotBlank
    @Length(max = 128)
    private String goodsNm;
    /**
     * 商品 通用 名称
     */
    @NotBlank
    @Length(max = 64)
    private String goodsCommonNm;
    /**
     * 商品 编码
     */
    @NotBlank
    @Length(max = 32)
    private String goodsCode;

    /**
     * 拼音
     */
    @NotBlank
    @Length(max = 64)
    private String pinyin;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public Long getGoodsBatchId() {
        return goodsBatchId;
    }

    public void setGoodsBatchId(Long goodsBatchId) {
        this.goodsBatchId = goodsBatchId;
    }

    public Long getSplitZeroQuantity() {
        return splitZeroQuantity;
    }

    public void setSplitZeroQuantity(Long splitZeroQuantity) {
        this.splitZeroQuantity = splitZeroQuantity;
    }

    public Long getSplitSmallPackageQuantity() {
        return splitSmallPackageQuantity;
    }

    public void setSplitSmallPackageQuantity(Long splitSmallPackageQuantity) {
        this.splitSmallPackageQuantity = splitSmallPackageQuantity;
    }

    public String getUsageText() {
        return usageText;
    }

    public void setUsageText(String usageText) {
        this.usageText = usageText;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public String getGoodsCommonNm() {
        return goodsCommonNm;
    }

    public void setGoodsCommonNm(String goodsCommonNm) {
        this.goodsCommonNm = goodsCommonNm;
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
}
