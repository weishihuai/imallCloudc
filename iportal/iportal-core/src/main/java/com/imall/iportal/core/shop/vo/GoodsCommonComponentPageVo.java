package com.imall.iportal.core.shop.vo;

/**
 * Created by caidapao on 2017/7/6.
 * 商品公共组件列表
 */
public class GoodsCommonComponentPageVo {
    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品名称
     */
    private String goodsNm;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 生产厂家
     */
    private String produceManufacturer;

    /**
     * 通用名称
     */
    private String commonNm;

    /**
     * 规格
     */
    private String spec;

    /**
     * 单位
     */
    private String unit;

    /**
     * 剂型 code
     */
    private String dosageForm;

    /**
     * 剂型 名称
     */
    private String dosageFormName;

    /**
     * 批准 文号
     */
    private String approvalNumber;

    /**
     * 毒理 代码
     */
    private String toxicologyCode;

    /**
     * 储存 条件 代码
     */
    private String storageCondition;

    /**
     * 排序
     */
    private Long displayPosition;

    /**
     * 说明书
     */
    private String instructionsStr;

    /**
     * 用药指导
     */
    private String medicationGuideStr;

    /**
     * 产地
     */
    private String productionPlace;

    /**
     * 零售价
     */
    private Double retailPrice;

    /**
     * 当前库存
     */
    private Long currentStock;

    /**
     * 会员价
     */
    private Double memberPrice;
    //采购税率
    private String purchaseTaxRate;

    /**
     * 商品类型代码
     */
    private String goodsTypeCode;

    public String getDosageFormName() {
        return dosageFormName;
    }

    public void setDosageFormName(String dosageFormName) {
        this.dosageFormName = dosageFormName;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Long getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Long currentStock) {
        this.currentStock = currentStock;
    }

    public String getInstructionsStr() {
        return instructionsStr;
    }

    public void setInstructionsStr(String instructionsStr) {
        this.instructionsStr = instructionsStr;
    }

    public String getMedicationGuideStr() {
        return medicationGuideStr;
    }

    public void setMedicationGuideStr(String medicationGuideStr) {
        this.medicationGuideStr = medicationGuideStr;
    }

    public String getToxicologyCode() {
        return toxicologyCode;
    }

    public void setToxicologyCode(String toxicologyCode) {
        this.toxicologyCode = toxicologyCode;
    }

    public String getStorageCondition() {
        return storageCondition;
    }

    public void setStorageCondition(String storageCondition) {
        this.storageCondition = storageCondition;
    }

    public Long getDisplayPosition() {
        return displayPosition;
    }

    public void setDisplayPosition(Long displayPosition) {
        this.displayPosition = displayPosition;
    }

    public String getCommonNm() {
        return commonNm;
    }

    public void setCommonNm(String commonNm) {
        this.commonNm = commonNm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getProduceManufacturer() {
        return produceManufacturer;
    }

    public void setProduceManufacturer(String produceManufacturer) {
        this.produceManufacturer = produceManufacturer;
    }

    public String getPurchaseTaxRate() {
        return purchaseTaxRate;
    }

    public void setPurchaseTaxRate(String purchaseTaxRate) {
        this.purchaseTaxRate = purchaseTaxRate;
    }

    public String getGoodsTypeCode() {
        return goodsTypeCode;
    }

    public void setGoodsTypeCode(String goodsTypeCode) {
        this.goodsTypeCode = goodsTypeCode;
    }
}
