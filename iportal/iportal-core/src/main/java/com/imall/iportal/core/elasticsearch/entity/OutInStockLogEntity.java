package com.imall.iportal.core.elasticsearch.entity;

import java.util.Date;

/**
 * 出入库明细日志
 * Created by HWJ on 2017/7/12
 */
public class OutInStockLogEntity {

    /**
     * 出入库日志ID
     */
    public static final String ID = "id";

    /**
     * 门店 ID
     */
    public static final String SHOP_ID = "shopId";

    /**
     * 商品 ID
     */
    public static final String GOODS_ID = "goodsId";

    /**
     * 出入库 类型 代码
     */
    public static final String TYPE_CODE = "typeCode";

    /**
     * 商品 批次 ID
     */
    public static final String GOODS_BATCH_ID = "goodsBatchId";

    /**
     * 货位 ID
     */
    public static final String STORAGE_SPACE_ID = "storageSpaceId";

    /**
     * 单位 价格
     */
    public static final String UNIT_PRICE = "unitPrice";

    /**
     * 入库数量
     */
    public static final String IN_STOCK_QUANTITY = "inStockQuantity";

    /**
     * 出库数量
     */
    public static final String OUT_STOCK_QUANTITY = "outStockQuantity";

    /**
     * 入库金额
     */
    public static final String IN_STOCK_AMOUNT = "inStockAmount";

    /**
     * 出库金额
     */
    public static final String OUT_STOCK_AMOUNT = "outStockAmount";

    /**
     * 结算 前 数量
     */
    public static final String CLEARING_PREV_QUANTITY = "clearingPrevQuantity";

    /**
     * 结算 前 金额
     */
    public static final String CLEARING_PREV_AMOUNT = "clearingPrevAmount";

    /**
     * 日志 来源 类型 代码
     */
    public static final String LOG_SOURCE_TYPE_CODE = "logSourceTypeCode";

    /**
     * 商品 编码
     */
    public static final String GOODS_CODE = "goodsCode";

    /**
     * 商品 名称
     */
    public static final String GOODS_NM = "goodsNm";

    /**
     * 商品通用名称
     */
    public static final String COMMON_NM = "commonNm";

    /**
     * 品牌 名称
     */
    public static final String BRAND_NM = "brandNm";

    /**
     * 商品名称首字母拼音
     */
    public static final String PINYIN = "pinyin";

    /**
     * 批号
     */
    public static final String BATCH = "batch";

    /**
     * 记录时间
     */
    public static final String CREATE_DATE = "createDate";


    private Long id;
    private Long shopId;
    private Long goodsId;
    private String typeCode;
    private Long goodsBatchId;
    private Long storageSpaceId;
    private Double unitPrice;
    private Long inStockQuantity;
    private Long outStockQuantity;
    private Double inStockAmount;
    private Double outStockAmount;
    private Long clearingPrevQuantity;
    private Double clearingPrevAmount;
    private String logSourceTypeCode;
    private String goodsCode;
    private String goodsNm;
    private String commonNm;
    private String brandNm;
    private String pinyin;
    private String batch;
    private Date createDate;

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

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Long getGoodsBatchId() {
        return goodsBatchId;
    }

    public void setGoodsBatchId(Long goodsBatchId) {
        this.goodsBatchId = goodsBatchId;
    }

    public Long getStorageSpaceId() {
        return storageSpaceId;
    }

    public void setStorageSpaceId(Long storageSpaceId) {
        this.storageSpaceId = storageSpaceId;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getInStockQuantity() {
        return inStockQuantity;
    }

    public void setInStockQuantity(Long inStockQuantity) {
        this.inStockQuantity = inStockQuantity;
    }

    public Long getOutStockQuantity() {
        return outStockQuantity;
    }

    public void setOutStockQuantity(Long outStockQuantity) {
        this.outStockQuantity = outStockQuantity;
    }

    public Double getInStockAmount() {
        return inStockAmount;
    }

    public void setInStockAmount(Double inStockAmount) {
        this.inStockAmount = inStockAmount;
    }

    public Double getOutStockAmount() {
        return outStockAmount;
    }

    public void setOutStockAmount(Double outStockAmount) {
        this.outStockAmount = outStockAmount;
    }

    public Long getClearingPrevQuantity() {
        return clearingPrevQuantity;
    }

    public void setClearingPrevQuantity(Long clearingPrevQuantity) {
        this.clearingPrevQuantity = clearingPrevQuantity;
    }

    public Double getClearingPrevAmount() {
        return clearingPrevAmount;
    }

    public void setClearingPrevAmount(Double clearingPrevAmount) {
        this.clearingPrevAmount = clearingPrevAmount;
    }

    public String getLogSourceTypeCode() {
        return logSourceTypeCode;
    }

    public void setLogSourceTypeCode(String logSourceTypeCode) {
        this.logSourceTypeCode = logSourceTypeCode;
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

    public String getBrandNm() {
        return brandNm;
    }

    public void setBrandNm(String brandNm) {
        this.brandNm = brandNm;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
