
package com.imall.iportal.core.shop.entity;

import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.util.DateTimeUtils;
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
@Table(name = "t_shp_drug_in_bucket")
public class DrugInBucket extends BaseEntity<Long> {

    public static final String SHOP_ID = "shopId";
    public static final String GOODS_ID = "goodsId";
    public static final String SKU_ID = "skuId";
    public static final String BATCH_ID = "batchId";
    public static final String IN_BUCKET_MAN_NAME = "inBucketManName";
    public static final String IN_BUCKET_TIME = "inBucketTime";
    public static final String QUANTITY = "quantity";
    public static final String STORAGE_SPACE_ID = "storageSpaceId";
    public static final String APPROVE_MAN_ID = "approveManId";
    public static final String IS_QUALITY_QUALIFIED = "isQualityQualified";

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
    /**
     * SKU_ID - SKU ID
     */
    @Column(name = "SKU_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long skuId;
    /**
     * BATCH_ID - 批号 ID
     */
    @Column(name = "BATCH_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long batchId;
    /**
     * IN_BUCKET_MAN_NAME - 装斗 人 姓名
     */
    @Column(name = "IN_BUCKET_MAN_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String inBucketManName;
    /**
     * IN_BUCKET_TIME - 装斗 时间
     */
    @Column(name = "IN_BUCKET_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date inBucketTime;
    /**
     * QUANTITY - 数量
     */
    @Column(name = "QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long quantity;
    /**
     * STORAGE_SPACE_ID - 货位 ID
     */
    @Column(name = "STORAGE_SPACE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long storageSpaceId;
    /**
     * APPROVE_MAN_ID - 审核 人 ID
     */
    @Column(name = "APPROVE_MAN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long approveManId;
    /**
     * IS_QUALITY_QUALIFIED - 是否 质量 合格
     */
    @Column(name = "IS_QUALITY_QUALIFIED", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isQualityQualified;

    public void setShopId(Long value) {
        this.shopId = value;
    }

    public Long getShopId() {
        return this.shopId;
    }

    public void setGoodsId(Long value) {
        this.goodsId = value;
    }

    public Long getGoodsId() {
        return this.goodsId;
    }

    public void setSkuId(Long value) {
        this.skuId = value;
    }

    public Long getSkuId() {
        return this.skuId;
    }

    public void setBatchId(Long value) {
        this.batchId = value;
    }

    public Long getBatchId() {
        return this.batchId;
    }

    public void setInBucketManName(String value) {
        this.inBucketManName = value;
    }

    public String getInBucketManName() {
        return this.inBucketManName;
    }

    public void setInBucketTime(java.util.Date value) {
        this.inBucketTime = value;
    }

    public java.util.Date getInBucketTime() {
        return this.inBucketTime;
    }

    public void setQuantity(Long value) {
        this.quantity = value;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public void setStorageSpaceId(Long value) {
        this.storageSpaceId = value;
    }

    public Long getStorageSpaceId() {
        return this.storageSpaceId;
    }

    public void setApproveManId(Long value) {
        this.approveManId = value;
    }

    public Long getApproveManId() {
        return this.approveManId;
    }

    public void setIsQualityQualified(String value) {
        this.isQualityQualified = value;
    }

    public String getIsQualityQualified() {
        return this.isQualityQualified;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("ID", getId())
                .append("SHOP_ID", getShopId())
                .append("GOODS_ID", getGoodsId())
                .append("SKU_ID", getSkuId())
                .append("BATCH_ID", getBatchId())
                .append("IN_BUCKET_MAN_NAME", getInBucketManName())
                .append("IN_BUCKET_TIME", getInBucketTime())
                .append("QUANTITY", getQuantity())
                .append("STORAGE_SPACE_ID", getStorageSpaceId())
                .append("APPROVE_MAN_ID", getApproveManId())
                .append("IS_QUALITY_QUALIFIED", getIsQualityQualified())
                .append("CREATE_DATE", getCreateDate())
                .append("CREATE_BY", getCreateBy())
                .append("LAST_MODIFIED_DATE", getLastModifiedDate())
                .append("LAST_MODIFIED_BY", getLastModifiedBy())
                .append("VERSION", getVersion())
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
        if (!(obj instanceof DrugInBucket)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        DrugInBucket other = (DrugInBucket) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }

    public String getInBucketTimeString() {
        return DateTimeUtils.convertDateToString(this.getInBucketTime());
    }

    public void setInBucketTimeString(String value) throws ParseException {
        this.setInBucketTime(DateTimeUtils.convertStringToDate(value));
    }

}

