package com.imall.iportal.core.shop.vo;

/**
 * Created by ou on 2017/7/6.
 * 商品拆零 - 获取对象详情
 */
public class GoodsSplitZeroDetailVo {

    // 商品拆零对象字段
    /**
     * 拆零 数量
     */
    private Long splitZeroQuantity;
    /**
     * 拆后 小 包装 数量
     */
    private Long splitSmallPackageQuantity;
    /**
     * 用法
     */
    private String usageText;
    /**
     *
     * 用量
     */
    private String dosage;
    /**
     * 经办人
     */
    private String operator;

    //商品字段
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
     * 货位
     */
    private String storageSpaceNm;
    /**
     * 批号
     */
    private String batch;
    /**
     * 有效日期
     */
    private String validDate;
    /**
     * 生产日期
     */
    private String produceDate;

    /**
     * 入库日期
     */
    private String inStockDate;

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

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
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

    public String getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate;
    }

    public String getInStockDate() {
        return inStockDate;
    }

    public void setInStockDate(String inStockDate) {
        this.inStockDate = inStockDate;
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
}
