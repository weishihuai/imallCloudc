package com.imall.iportal.core.shop.vo;

/**
 * Created by caidapao on 2017/7/6.
 * 商品/商品批次 公共组件列表搜索参数
 */
public class GoodsCommonComponentSearchParam {

    /**
     * 商品名称/通用名称/拼音
     */
    private String keyWords;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 生产厂家
     */
    private String produceManufacturer;

    /**
     * 批号
     */
    private String batch;

    /**
     * 是否麻黄碱
     */
    private String isEphedrine;

    /**
     * 处方类型代码
     */
    private String prescriptionDrugsTypeCode;

    /**
     * 入库时间大于 N 天
     */
    private Integer gtInStockDays;
    /**
     * 是否是中药饮品
     */
    private String isChineseMedicinePieces;
    /**
     * 是否 内置
     */
    private String isBuiltIn;
    /**
     * 是否 库存大于零
     */
    private String isBiggerThanCurrentStock;
    /**
     * 是否 虚拟货物
     */
    private String isVirtualWarehouse;

    /**
     * 是否 拆零
     */
    private String isSplitZero;

    private String batchState;

    /**
     * 药品是否有效
     */
    private String isInEffective;

    /**
     * 是否允许出库
     */
    private String isAllowOutStock;

    public String getBatchState() {
        return batchState;
    }

    public void setBatchState(String batchState) {
        this.batchState = batchState;
    }

    public String getIsBuiltIn() {
        return isBuiltIn;
    }

    public void setIsBuiltIn(String isBuiltIn) {
        this.isBuiltIn = isBuiltIn;
    }

    public String getIsBiggerThanCurrentStock() {
        return isBiggerThanCurrentStock;
    }

    public void setIsBiggerThanCurrentStock(String isBiggerThanCurrentStock) {
        this.isBiggerThanCurrentStock = isBiggerThanCurrentStock;
    }

    public String getIsChineseMedicinePieces() {
        return isChineseMedicinePieces;
    }

    public void setIsChineseMedicinePieces(String isChineseMedicinePieces) {
        this.isChineseMedicinePieces = isChineseMedicinePieces;
    }

    public String getIsVirtualWarehouse() {
        return isVirtualWarehouse;
    }

    public void setIsVirtualWarehouse(String isVirtualWarehouse) {
        this.isVirtualWarehouse = isVirtualWarehouse;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getIsEphedrine() {
        return isEphedrine;
    }

    public void setIsEphedrine(String isEphedrine) {
        this.isEphedrine = isEphedrine;
    }

    public String getPrescriptionDrugsTypeCode() {
        return prescriptionDrugsTypeCode;
    }

    public void setPrescriptionDrugsTypeCode(String prescriptionDrugsTypeCode) {
        this.prescriptionDrugsTypeCode = prescriptionDrugsTypeCode;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
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

    public Integer getGtInStockDays() {
        return gtInStockDays;
    }

    public void setGtInStockDays(Integer gtInStockDays) {
        this.gtInStockDays = gtInStockDays;
    }

    public String getIsSplitZero() {
        return isSplitZero;
    }

    public void setIsSplitZero(String isSplitZero) {
        this.isSplitZero = isSplitZero;
    }

    public String getIsInEffective() {
        return isInEffective;
    }

    public void setIsInEffective(String isInEffective) {
        this.isInEffective = isInEffective;
    }

    public String getIsAllowOutStock() {
        return isAllowOutStock;
    }

    public void setIsAllowOutStock(String isAllowOutStock) {
        this.isAllowOutStock = isAllowOutStock;
    }
}
