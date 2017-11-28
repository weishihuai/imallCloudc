package com.imall.iportal.core.shop.vo;

/**
 * Created by caidapao on 2017/7/6.
 * 商品批次公共组件列表
 */
public class GoodsBatchCommonComponentPageVo {
    /**
     * 批次id
     */
    private Long id;

    /**
     * 批次状态
     */
    private String batchState;

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
     * 剂型名称
     */
    private String dosageFormName;

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
     * 批号
     */
    private String batch;

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
     * 药品状态
     */
    private String isEnable;

    /**
     * 货位名称
     */
    private String storageSpaceNm;

    /**
     * 会员价
     */
    private Double memberPrice;

    /**
     * sku Id
     */
    private Long skuId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 供应商名称
     */
    private String supplierNm;

    /**
     * 供应商id
     */
    private Long supplierId;

    /**
     * 生产日期
     */
    private String produceDateString;

    /**
     * 有效日期
     */
    private String validDateString;

    /**
     * 入库日期
     */
    private String createDateString;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 批准文号
     */
    private String approvalNumber;

    /**
     * 货位id
     */
    private Long storageSpaceId;

    /**
     * 采购 价格
     */
    private java.lang.Double purchasePrice;

    /**
     * 拆零 单位
     */
    private java.lang.String splitZeroUnit;

    /**
     * 拆零 规格
     */
    private java.lang.String splitZeroSpec;

    /**
     * 拆零 零售 价格
     */
    private java.lang.Double splitZeroRetailPrice;

    /**
     * 拆零 会员 价格
     */
    private java.lang.Double splitZeroMemberPrice;

    /**
     * 商品 类型 代码
     */
    private String goodsTypeCode;

    /**
     * 拆零 数量
     */
    private java.lang.Long splitZeroQuantity;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getBatchState() {
        return batchState;
    }

    public void setBatchState(String batchState) {
        this.batchState = batchState;
    }

    public String getDosageFormName() {
        return dosageFormName;
    }

    public void setDosageFormName(String dosageFormName) {
        this.dosageFormName = dosageFormName;
    }

    public Long getStorageSpaceId() {
        return storageSpaceId;
    }

    public void setStorageSpaceId(Long storageSpaceId) {
        this.storageSpaceId = storageSpaceId;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public String getProduceDateString() {
        return produceDateString;
    }

    public void setProduceDateString(String produceDateString) {
        this.produceDateString = produceDateString;
    }

    public String getValidDateString() {
        return validDateString;
    }

    public void setValidDateString(String validDateString) {
        this.validDateString = validDateString;
    }

    public String getCreateDateString() {
        return createDateString;
    }

    public void setCreateDateString(String createDateString) {
        this.createDateString = createDateString;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getStorageSpaceNm() {
        return storageSpaceNm;
    }

    public void setStorageSpaceNm(String storageSpaceNm) {
        this.storageSpaceNm = storageSpaceNm;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
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

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
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

    public Double getSplitZeroRetailPrice() {
        return splitZeroRetailPrice;
    }

    public void setSplitZeroRetailPrice(Double splitZeroRetailPrice) {
        this.splitZeroRetailPrice = splitZeroRetailPrice;
    }

    public Double getSplitZeroMemberPrice() {
        return splitZeroMemberPrice;
    }

    public void setSplitZeroMemberPrice(Double splitZeroMemberPrice) {
        this.splitZeroMemberPrice = splitZeroMemberPrice;
    }

    public String getGoodsTypeCode() {
        return goodsTypeCode;
    }

    public void setGoodsTypeCode(String goodsTypeCode) {
        this.goodsTypeCode = goodsTypeCode;
    }

    public Long getSplitZeroQuantity() {
        return splitZeroQuantity;
    }

    public void setSplitZeroQuantity(Long splitZeroQuantity) {
        this.splitZeroQuantity = splitZeroQuantity;
    }
}
