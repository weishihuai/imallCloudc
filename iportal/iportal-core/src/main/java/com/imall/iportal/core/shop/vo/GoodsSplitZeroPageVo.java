package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;

import java.util.Date;

/**
 * Created by ou on 2017/7/6.
 */
public class GoodsSplitZeroPageVo {

    /**
     * 商品拆零id
     */
    private Long id;
    /**
     * 商品 ID
     */
    private Long goodsId;
    /**
     * SKU ID
     */
    private Long skuId;
    /**
     * 商品 批次 ID
     */
    private Long goodsBatchId;
    /**
     * 商品编码
     */
    private String goodsCode;
    /**
     * 商品名称
     */
    private String goodsNm;
    /**
     * 拆零 规格
     */
    private String spec;
    /**
     * 剂型
     */
    private String dosageForm;
    /**
     * 单位
     */
    private String unit;
    /**
     * 生产 厂商
     */
    private String produceManufacturer;
    /**
     * 批号
     */
    private String batch;
    /**
     * 有效日期
     */
    private String validDate;
    /**
     * 货位
     */
    private String storageSpaceNm;
    /**
     * 当前 库存
     */
    private Long currentStock;
    /**
     * 拆零 数量
     */
    private Long splitZeroQuantity;
    /**
     * 拆后 小 包装 数量
     */
    private Long splitSmallPackageQuantity;
    /**
     * 拆零 单位
     */
    private String splitZeroUnit;
    /**
     * 拆零 规格
     */
    private String splitZeroSpec;
    /**
     * 经办人
     */
    private String operator;
    /**
     * 拆零时间
     */
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getStorageSpaceNm() {
        return storageSpaceNm;
    }

    public void setStorageSpaceNm(String storageSpaceNm) {
        this.storageSpaceNm = storageSpaceNm;
    }

    public Long getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Long currentStock) {
        this.currentStock = currentStock;
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

    public String getSplitZeroUnit() {
        return splitZeroUnit;
    }

    public void setSplitZeroUnit(String splitZeroUnit) {
        this.splitZeroUnit = splitZeroUnit;
    }

    public String getSplitZeroSpec() {
        return splitZeroSpec;
    }

    public void setSplitZeroSpec(String splitZeroSpec) {
        this.splitZeroSpec = splitZeroSpec;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateDateString(){
        if(this.getCreateDate()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getCreateDate());
        }
    }
}
