
package com.imall.iportal.core.shop.entity;

import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.ParseException;

/**
 * 实体类
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_goods_batch")
public class GoodsBatch extends BaseEntity<Long> {

    public static final String SHOP_ID = "shopId";
    public static final String GOODS_ID = "goodsId";
    public static final String SKU_ID = "skuId";
    public static final String BATCH = "batch";
    public static final String PRODUCE_DATE = "produceDate";
    public static final String VALID_DATE = "validDate";
    public static final String CURRENT_STOCK = "currentStock";
    public static final String STORAGE_SPACE_ID = "storageSpaceId";
    public static final String SUPPLIER_ID = "supplierId";
    public static final String PINYIN = "pinyin";
    public static final String GOODS_CODE = "goodsCode";
    public static final String GOODS_NM = "goodsNm";
    public static final String GOODS_COMMON_NM = "goodsCommonNm";
    public static final String BATCH_STATE = "batchState";
    public static final String PURCHASE_PRICE = "purchasePrice";


    /**
     * SHOP_ID - 门店 ID
     */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /**
     * GOODS_ID - 商品 ID
     */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsId;

    /** SKU_ID - SKU ID */
    @Column(name = "SKU_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long skuId;

    /**
     * BATCH - 批号
     */
    @Column(name = "BATCH", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String batch;
    /**
     * PRODUCE_DATE - 生产 日期
     */
    @Column(name = "PRODUCE_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date produceDate;
    /**
     * VALID_DATE - 有效 日期
     */
    @Column(name = "VALID_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date validDate;
    /**
     * CURRENT_STOCK - 当前 库存
     */
    @Column(name = "CURRENT_STOCK", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long currentStock;
    /**
     * STORAGE_SPACE_ID - 货位_ID
     */
    @Column(name = "STORAGE_SPACE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long storageSpaceId;
    /** SUPPLIER_ID - 供应商 ID */
    @Column(name = "SUPPLIER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long supplierId;
    /** PINYIN - 拼音 */
    @Column(name = "PINYIN", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private java.lang.String pinyin;
    /** GOODS_CODE - 商品 编码 */
    @Column(name = "GOODS_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private java.lang.String goodsCode;
    /** GOODS_NM - 商品 名称 */
    @Column(name = "GOODS_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private java.lang.String goodsNm;
    /** GOODS_COMMON_NM - 通用 名称 */
    @Column(name = "GOODS_COMMON_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String goodsCommonNm;
    /** BATCH_STATE - 批次 状态 */
    @Column(name = "BATCH_STATE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String batchState;
    /** PURCHASE_PRICE - 采购 价格 */
    @Column(name = "PURCHASE_PRICE", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private java.lang.Double purchasePrice;

    public void setGoodsId(Long value) {
        this.goodsId = value;
    }

    public Long getGoodsId() {
        return this.goodsId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public void setBatch(String value) {
        this.batch = value;
    }

    public String getBatch() {
        return this.batch;
    }

    public void setProduceDate(java.util.Date value) {
        this.produceDate = value;
    }

    public java.util.Date getProduceDate() {
        return this.produceDate;
    }

    public void setValidDate(java.util.Date value) {
        this.validDate = value;
    }

    public java.util.Date getValidDate() {
        return this.validDate;
    }

    public void setCurrentStock(Long value) {
        this.currentStock = value;
    }

    public Long getCurrentStock() {
        return this.currentStock;
    }

    public void setShopId(Long value) {
        this.shopId = value;
    }

    public Long getShopId() {
        return this.shopId;
    }

    public Long getStorageSpaceId() {
        return storageSpaceId;
    }

    public void setStorageSpaceId(Long storageSpaceId) {
        this.storageSpaceId = storageSpaceId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
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

    public String getGoodsCommonNm() {
        return goodsCommonNm;
    }

    public void setGoodsCommonNm(String goodsCommonNm) {
        this.goodsCommonNm = goodsCommonNm;
    }

    public String getBatchState() {
        return batchState;
    }

    public void setBatchState(String batchState) {
        this.batchState = batchState;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("ID", getId())
                .append("GOODS_ID", getGoodsId())
                .append("SKU_ID", getSkuId())
                .append("BATCH", getBatch())
                .append("PRODUCE_DATE", getProduceDate())
                .append("VALID_DATE", getValidDate())
                .append("CURRENT_STOCK", getCurrentStock())
                .append("SHOP_ID", getShopId())
                .append("CREATE_DATE", getCreateDate())
                .append("CREATE_BY", getCreateBy())
                .append("LAST_MODIFIED_DATE", getLastModifiedDate())
                .append("LAST_MODIFIED_BY", getLastModifiedBy())
                .append("VERSION", getVersion())
                .append("STORAGE_SPACE_ID", getStorageSpaceId())
                .append("SUPPLIER_ID",getSupplierId())
                .append("PINYIN",getPinyin())
                .append("GOODS_CODE",getGoodsCode())
                .append("GOODS_NM",getGoodsNm())
                .append("GOODS_COMMON_NM",getGoodsCommonNm())
                .append("BATCH_STATE",getBatchState())
                .append("PURCHASE_PRICE",getPurchasePrice())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (this.getId() == null) {
            return false;
        }
        if (!(obj instanceof GoodsBatch)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        GoodsBatch other = (GoodsBatch) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }

    public String getProduceDateString() {
        return DateTimeUtils.convertDateToString(this.getProduceDate());
    }

    public void setProduceDateString(String value) throws ParseException {
        this.setProduceDate(StringUtils.isBlank(value)?null:DateTimeUtils.convertStringToDate(value));
    }

    public String getValidDateString() {
        return DateTimeUtils.convertDateToString(this.getValidDate());
    }

    public void setValidDateString(String value) throws ParseException {
        this.setValidDate(StringUtils.isBlank(value)?null:DateTimeUtils.convertStringToDate(value));
    }

}

