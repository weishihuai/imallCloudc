package com.imall.iportal.core.shop.vo;

/**
 * Created by lxh on 2017/7/25.
 * 快速收货项VO
 */
public class FastReceiveItemVo {
    //批号
   private String goodsBatch;
    //生产日期
    private String productionDateString;
    //y有效期
    private String validityString;
    //到货数量
    private Long goodsArrivalQuantity;
    //采购单价
    private Double purchaseUnitPrice;
    //零售价
    private Double retailPrice;
    //货位
    private String storageSpaceNm;
    //合格数量
    private Long qualifiedQuantity;
    //拒收数量
    private Long rejectionQuantity;
    //入库数量
    private Long inStorageQuantity;
    //抽样数量
    private Long sampleQuantity;
    //灭菌批次
    private String sterilizationBatch;
    //验收报告
    private String acceptanceRep;
    //验收合格金额
    private Double acceptanceQualifiedAmount;
    //商品编码
    private String goodsCode;
    //商品名称
    private String goodsNm;
    //通用名称
    private String commonNm;
    //规格
    private String spec;
    //剂型
    private String dosageForm;
    //单位
    private String unit;
    //生产厂商
    private String produceManufacturer;
    //批准文号
    private String approvalNumber;
    //产地
    private String productionPlace;

    public String getGoodsBatch() {
        return goodsBatch;
    }

    public void setGoodsBatch(String goodsBatch) {
        this.goodsBatch = goodsBatch;
    }

    public String getProductionDateString() {
        return productionDateString;
    }

    public void setProductionDateString(String productionDateString) {
        this.productionDateString = productionDateString;
    }

    public String getValidityString() {
        return validityString;
    }

    public void setValidityString(String validityString) {
        this.validityString = validityString;
    }

    public Long getGoodsArrivalQuantity() {
        return goodsArrivalQuantity;
    }

    public void setGoodsArrivalQuantity(Long goodsArrivalQuantity) {
        this.goodsArrivalQuantity = goodsArrivalQuantity;
    }

    public Double getPurchaseUnitPrice() {
        return purchaseUnitPrice;
    }

    public void setPurchaseUnitPrice(Double purchaseUnitPrice) {
        this.purchaseUnitPrice = purchaseUnitPrice;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getStorageSpaceNm() {
        return storageSpaceNm;
    }

    public void setStorageSpaceNm(String storageSpaceNm) {
        this.storageSpaceNm = storageSpaceNm;
    }

    public Long getQualifiedQuantity() {
        return qualifiedQuantity;
    }

    public void setQualifiedQuantity(Long qualifiedQuantity) {
        this.qualifiedQuantity = qualifiedQuantity;
    }

    public Long getRejectionQuantity() {
        return rejectionQuantity;
    }

    public void setRejectionQuantity(Long rejectionQuantity) {
        this.rejectionQuantity = rejectionQuantity;
    }

    public Long getInStorageQuantity() {
        return inStorageQuantity;
    }

    public void setInStorageQuantity(Long inStorageQuantity) {
        this.inStorageQuantity = inStorageQuantity;
    }

    public Long getSampleQuantity() {
        return sampleQuantity;
    }

    public void setSampleQuantity(Long sampleQuantity) {
        this.sampleQuantity = sampleQuantity;
    }

    public String getSterilizationBatch() {
        return sterilizationBatch;
    }

    public void setSterilizationBatch(String sterilizationBatch) {
        this.sterilizationBatch = sterilizationBatch;
    }

    public String getAcceptanceRep() {
        return acceptanceRep;
    }

    public void setAcceptanceRep(String acceptanceRep) {
        this.acceptanceRep = acceptanceRep;
    }

    public Double getAcceptanceQualifiedAmount() {
        return acceptanceQualifiedAmount;
    }

    public void setAcceptanceQualifiedAmount(Double acceptanceQualifiedAmount) {
        this.acceptanceQualifiedAmount = acceptanceQualifiedAmount;
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
}
