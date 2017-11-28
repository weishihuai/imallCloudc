package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;

import java.util.Date;

/**
 * Created by frt on 2017/7/14.
 */
public class DrugCheckItemDetailVo {
    private Long id;

    /**
     * 检查 数量
     */
    private Long checkQuantity;

    /**
     * 检查 项目
     */
    private String checkPrj;

    /**
     * 不 合格 数量
     */
    private Long notQualifiedQuantity;

    /**
     * 处理 意见
     */
    private String processOpinion;

    /**
     * 结论
     */
    private String conclusion;

    /**
     * 备注
     */
    private String remark;

    /**
     * 商品 编码
     */
    private String goodsCode;

    /**
     * 商品 名称
     */
    private String goodsNm;

    /**
     * 通用 名称
     */
    private String commonNm;

    /**
     * 规格
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
     * 批准文号
     */
    private String approvalNumber;

    /**
     * 产地
     */
    private String productionPlace;

    /**
     * 批号
     */
    private String batch;

    /**
     * 生产 日期
     */
    private java.util.Date produceDate;

    /**
     * 有效 日期
     */
    private java.util.Date validDate;

    /**
     * 货位 名称
     */
    private String storageSpaceNm;

    /**
     * 当前 库存
     */
    private Long currentStock;

    /**
     * 采购 价格
     */
    private java.lang.Double purchasePrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCheckQuantity() {
        return checkQuantity;
    }

    public void setCheckQuantity(Long checkQuantity) {
        this.checkQuantity = checkQuantity;
    }

    public String getCheckPrj() {
        return checkPrj;
    }

    public void setCheckPrj(String checkPrj) {
        this.checkPrj = checkPrj;
    }

    public Long getNotQualifiedQuantity() {
        return notQualifiedQuantity;
    }

    public void setNotQualifiedQuantity(Long notQualifiedQuantity) {
        this.notQualifiedQuantity = notQualifiedQuantity;
    }

    public String getProcessOpinion() {
        return processOpinion;
    }

    public void setProcessOpinion(String processOpinion) {
        this.processOpinion = processOpinion;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Date getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
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

    public Long getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Long currentStock) {
        this.currentStock = currentStock;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getProduceDateString(){
        if(this.getProduceDate()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToCompactString(this.getProduceDate());
        }
    }

    public String getValidDateString(){
        if(this.getValidDate()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToCompactString(this.getValidDate());
        }
    }
}
