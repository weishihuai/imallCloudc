package com.imall.iportal.core.elasticsearch.entity;

import java.util.Date;

/**
 * Created by hWJ on 2017/7/6
 */
public class GoodsEntity {

    /**
     * 商品ID
     */
    public static final String ID = "id";

    /**
     * 商品编码
     */
    public static final String GOODS_CODE = "goodsCode";

    /**
     * 商品名称
     */
    public static final String GOODS_NM = "goodsNm";

    /**
     * 条形码
     */
    public static final String BAR_CODE = "barCode";

    /**
     * 品牌名称
     */
    public static final String BRAND_NM = "brandNm";

    /**
     * 商品 类型 代码
     */
    public static final String GOODS_TYPE_CODE = "goodsTypeCode";

    /**
     * 通用 名称
     */
    public static final String COMMON_NM = "commonNm";

    /**
     * 门店 ID
     */
    public static final String SHOP_ID = "shopId";

    /**
     * 生产产商
     */
    public static final String PRODUCE_MANUFACTURER = "produceManufacturer";

    /**
     * 是否 删除
     */
    public static final String IS_DELETE = "isDelete";

    /**
     * 排序
     */
    public static final String DISPLAY_POSITION = "displayPosition";

    /**
     * 拼音
     */
    public static final String PINYIN = "pinyin";

    /**
     * 审核状态代码
     */
    public static final String APPROVE_STATE_CODE = "approveStateCode";

    /**
     * 商品拆零
     */
    public static final String GOODS_SPLIT_ZERO_IDS = "goodsSplitZeroIds";

    /**
     * 处方药类型代码
     */
    public static final String PRESCRIPTION_DRUGS_TYPE_CODE = "prescriptionDrugsTypeCode";

    /**
     * 是否 麻黄碱
     */
    public static final String IS_EPHEDRINE = "isEphedrine";

    /**
     * 创建时间
     */
    public static final String CREATE_DATE = "createDate";

    /**
     * 货位ID
     */
    public static final String STORAGE_SPACE_ID = "storageSpaceId";

    /**
     * 是否启用
     */
    public static final String IS_ENABLE = "isEnable";

    /**
     * 销量
     */
    public static final String SALES_VOLUME = "salesVolume";

    /**
     * 零售价
     */
    public static final String RETAIL_PRICE = "retailPrice";

    /**
     * 拆零 源 商品 ID
     */
    public static final String SPLIT_ZERO_SOURCE_GOODS_ID = "splitZeroSourceGoodsId";

    /**
     * 销售分类
     */
    public static final String SALES_CATEGORY_IDS = "salesCategoryIds";

    private Long id;
    private String goodsCode;
    private String goodsNm;
    private String brandNm;
    private String goodsTypeCode;
    private String commonNm;
    private Long shopId;
    private String produceManufacturer;
    private String isDelete;
    private Long displayPosition;
    private String pinyin;
    private String approveStatCode;
    private Long[] goodsSplitZeroIds;
    private String prescriptionDrugsTypeCode;
    private String isEphedrine;
    private Date createDate;
    private Long[] storageSpaceId;
    private String isEnable;
    private String approveStateCode;
    private Long salesVolume;
    private Double retailPrice;
    private Long splitZeroSourceGoodsId;
    private String barCode;
    private Long[] salesCategoryIds;

    public Long getSplitZeroSourceGoodsId() {
        return splitZeroSourceGoodsId;
    }

    public void setSplitZeroSourceGoodsId(Long splitZeroSourceGoodsId) {
        this.splitZeroSourceGoodsId = splitZeroSourceGoodsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getGoodsTypeCode() {
        return goodsTypeCode;
    }

    public void setGoodsTypeCode(String goodsTypeCode) {
        this.goodsTypeCode = goodsTypeCode;
    }

    public String getCommonNm() {
        return commonNm;
    }

    public void setCommonNm(String commonNm) {
        this.commonNm = commonNm;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Long getDisplayPosition() {
        return displayPosition;
    }

    public void setDisplayPosition(Long displayPosition) {
        this.displayPosition = displayPosition;
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

    public String getApproveStatCode() {
        return approveStatCode;
    }

    public void setApproveStatCode(String approveStatCode) {
        this.approveStatCode = approveStatCode;
    }

    public Long[] getGoodsSplitZeroIds() {
        return goodsSplitZeroIds;
    }

    public void setGoodsSplitZeroIds(Long[] goodsSplitZeroIds) {
        this.goodsSplitZeroIds = goodsSplitZeroIds;
    }

    public String getPrescriptionDrugsTypeCode() {
        return prescriptionDrugsTypeCode;
    }

    public void setPrescriptionDrugsTypeCode(String prescriptionDrugsTypeCode) {
        this.prescriptionDrugsTypeCode = prescriptionDrugsTypeCode;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long[] getStorageSpaceId() {
        return storageSpaceId;
    }

    public void setStorageSpaceId(Long[] storageSpaceId) {
        this.storageSpaceId = storageSpaceId;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getApproveStateCode() {
        return approveStateCode;
    }

    public void setApproveStateCode(String approveStateCode) {
        this.approveStateCode = approveStateCode;
    }

    public Long getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Long salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Long[] getSalesCategoryIds() {
        return salesCategoryIds;
    }

    public void setSalesCategoryIds(Long[] salesCategoryIds) {
        this.salesCategoryIds = salesCategoryIds;
    }
}
