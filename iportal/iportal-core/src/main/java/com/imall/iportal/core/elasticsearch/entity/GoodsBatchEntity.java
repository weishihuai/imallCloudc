package com.imall.iportal.core.elasticsearch.entity;

import java.util.Date;

/**
 * Created by hWJ on 2017/8/14
 */
public class GoodsBatchEntity {

    /**
     * 商品批次ID
     */
    public static final String ID = "id";

    /**
     * 门店 ID
     */
    public static final String SHOP_ID = "shopId";

    /**
     * 商品ID
     */
    public static final String GOODS_ID = "goodsId";

    /**
     * 商品编码
     */
    public static final String GOODS_CODE = "goodsCode";

    /**
     * 商品名称
     */
    public static final String GOODS_NM = "goodsNm";

    /**
     * 通用 名称
     */
    public static final String COMMON_NM = "commonNm";

    /**
     * 拼音
     */
    public static final String PINYIN = "pinyin";

    /**
     * 商品 类型 代码
     */
    public static final String GOODS_TYPE_CODE = "goodsTypeCode";

    /**
     * 生产产商
     */
    public static final String PRODUCE_MANUFACTURER = "produceManufacturer";

    /**
     * 是否 麻黄碱
     */
    public static final String IS_EPHEDRINE = "isEphedrine";

    /**
     * 商品批次
     */
    public static final String GOODS_BATCH = "goodsBatch";

    /**
     * 处方药类型代码
     */
    public static final String PRESCRIPTION_DRUGS_TYPE_CODE = "prescriptionDrugsTypeCode";

    /**
     * 货位ID
     */
    public static final String STORAGE_SPACE_ID = "storageSpaceId";

    /**
     * 货位类型
     */
    public static final String STORAGE_SPACE_TYPE = "storageSpaceType";

    /**
     * 是否有库存
     */
    public static final String HAS_STOCK = "hasStock";

    /**
     * 是否内置
     */
    public static final String IS_BUILT_IN = "isBuiltIn";
    /**
     * 是否 虚拟 货位
     */
    public static final String IS_VIRTUAL_WAREHOUSE = "isVirtualWarehouse";

    /**
     * 是否 拆零
     */
    public static final String IS_SPLIT_ZERO = "isSplitZero";

    /**
     * 批次状态
     */
    public static final String BATCH_STATE = "batchState";

    /**
     * 创建时间
     */
    public static final String CREATE_DATE = "createDate";

    /**
     * 入库时间（其他入库为入库时间，采购为批次创建时间）
     */
    public static final String IN_STOCK_TIME = "inStockTime";

    /**
     * 有效 日期
     */
    public static final String VALID_DATE = "validDate";

    private Long id;
    private Long shopId;
    private Long goodsId;
    private String goodsCode;
    private String goodsNm;
    private String commonNm;
    private String pinyin;
    private String goodsTypeCode;
    private String produceManufacturer;
    private String isEphedrine;
    private String goodsBatch;
    private String prescriptionDrugsTypeCode;
    private Long storageSpaceId;
    private String storageSpaceType;
    private String hasStock;
    private String isBuiltIn;
    private String isVirtualWarehouse;
    private String isSplitZero;
    private String batchState;
    private Date createDate;
    private Date inStockTime;
    private Date validDate;

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

    public String getIsVirtualWarehouse() {
        return isVirtualWarehouse;
    }

    public void setIsVirtualWarehouse(String isVirtualWarehouse) {
        this.isVirtualWarehouse = isVirtualWarehouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getGoodsTypeCode() {
        return goodsTypeCode;
    }

    public void setGoodsTypeCode(String goodsTypeCode) {
        this.goodsTypeCode = goodsTypeCode;
    }

    public String getProduceManufacturer() {
        return produceManufacturer;
    }

    public void setProduceManufacturer(String produceManufacturer) {
        this.produceManufacturer = produceManufacturer;
    }

    public String getIsEphedrine() {
        return isEphedrine;
    }

    public void setIsEphedrine(String isEphedrine) {
        this.isEphedrine = isEphedrine;
    }

    public String getGoodsBatch() {
        return goodsBatch;
    }

    public void setGoodsBatch(String goodsBatch) {
        this.goodsBatch = goodsBatch;
    }

    public String getPrescriptionDrugsTypeCode() {
        return prescriptionDrugsTypeCode;
    }

    public void setPrescriptionDrugsTypeCode(String prescriptionDrugsTypeCode) {
        this.prescriptionDrugsTypeCode = prescriptionDrugsTypeCode;
    }

    public Long getStorageSpaceId() {
        return storageSpaceId;
    }

    public void setStorageSpaceId(Long storageSpaceId) {
        this.storageSpaceId = storageSpaceId;
    }

    public String getStorageSpaceType() {
        return storageSpaceType;
    }

    public void setStorageSpaceType(String storageSpaceType) {
        this.storageSpaceType = storageSpaceType;
    }

    public String getHasStock() {
        return hasStock;
    }

    public void setHasStock(String hasStock) {
        this.hasStock = hasStock;
    }

    public String getIsSplitZero() {
        return isSplitZero;
    }

    public void setIsSplitZero(String isSplitZero) {
        this.isSplitZero = isSplitZero;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getInStockTime() {
        return inStockTime;
    }

    public void setInStockTime(Date inStockTime) {
        this.inStockTime = inStockTime;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }
}
