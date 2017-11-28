package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ou on 2017/7/7.
 */
public class ChineseMedicinePiecesLoadingBucketRecordSaveVo {

    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;
    /**
     * 装斗 日期
     */
    @NotNull
    private Date loadingBucketDate;
    /**
     * 装斗 数量
     */
    @NotNull
    private Long loadingBucketQuantity;
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
     * 装斗 人 姓名
     */
    @NotBlank
    @Length(max = 32)
    private String loadingBucketManNm;
    /**
     * 审核 人 ID
     */
    @NotNull
    @Length(max = 19)
    private Long approveManId;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Date getLoadingBucketDate() {
        return loadingBucketDate;
    }

    public void setLoadingBucketDate(Date loadingBucketDate) {
        this.loadingBucketDate = loadingBucketDate;
    }

    public Long getLoadingBucketQuantity() {
        return loadingBucketQuantity;
    }

    public void setLoadingBucketQuantity(Long loadingBucketQuantity) {
        this.loadingBucketQuantity = loadingBucketQuantity;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public String getGoodsPinyin() {
        return goodsPinyin;
    }

    public void setGoodsPinyin(String goodsPinyin) {
        this.goodsPinyin = goodsPinyin;
    }

    public String getCommonNm() {
        return commonNm;
    }

    public void setCommonNm(String commonNm) {
        this.commonNm = commonNm;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProduceManufacturer() {
        return produceManufacturer;
    }

    public void setProduceManufacturer(String produceManufacturer) {
        this.produceManufacturer = produceManufacturer;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Date getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Date produceTime) {
        this.produceTime = produceTime;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getStorageSpaceNm() {
        return storageSpaceNm;
    }

    public void setStorageSpaceNm(String storageSpaceNm) {
        this.storageSpaceNm = storageSpaceNm;
    }

    public String getLoadingBucketManNm() {
        return loadingBucketManNm;
    }

    public void setLoadingBucketManNm(String loadingBucketManNm) {
        this.loadingBucketManNm = loadingBucketManNm;
    }

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }
}
