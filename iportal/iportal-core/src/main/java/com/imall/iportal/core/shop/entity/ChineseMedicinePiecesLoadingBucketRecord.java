
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
/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_chinese_medicine_pieces_loading_bucket_record" )
public class ChineseMedicinePiecesLoadingBucketRecord extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String LOADING_BUCKET_DATE = "loadingBucketDate";
	public static final String LOADING_BUCKET_MAN_NM = "loadingBucketManNm";
	public static final String APPROVE_MAN_ID = "approveManId";
	public static final String GOODS_NM = "goodsNm";
	public static final String GOODS_PINYIN = "goodsPinyin";
	public static final String COMMON_NM = "commonNm";
	public static final String SPEC = "spec";
	public static final String PRODUCE_MANUFACTURER = "produceManufacturer";
	public static final String PRODUCTION_PLACE = "productionPlace";
	public static final String BATCH = "batch";
	public static final String PRODUCE_DATE = "produceDate";
	public static final String VALID_DATE = "validDate";
	public static final String ORIGINAL_STORAGE_SPACE_NM = "originalStorageSpaceNm";
	public static final String TARGET_STORAGE_SPACE_NM = "targetStorageSpaceNm";
	public static final String LOADING_BUCKET_QUANTITY = "loadingBucketQuantity";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long shopId;
    /** LOADING_BUCKET_DATE - 装斗 日期 */
    @Column(name = "LOADING_BUCKET_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date loadingBucketDate;
    /** LOADING_BUCKET_MAN_NM - 装斗 人 姓名 */
    @Column(name = "LOADING_BUCKET_MAN_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String loadingBucketManNm;
    /** APPROVE_MAN_ID - 审核 人 ID */
    @Column(name = "APPROVE_MAN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long approveManId;
    /** GOODS_NM - 商品 名称 */
    @Column(name = "GOODS_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private java.lang.String goodsNm;
    /** GOODS_PINYIN - 商品 拼音 */
    @Column(name = "GOODS_PINYIN", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private java.lang.String goodsPinyin;
    /** COMMON_NM - 通用 名称 */
    @Column(name = "COMMON_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private java.lang.String commonNm;
    /** SPEC - 规格 */
    @Column(name = "SPEC", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String spec;
    /** PRODUCE_MANUFACTURER - 生产厂商 */
    @Column(name = "PRODUCE_MANUFACTURER", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private java.lang.String produceManufacturer;
    /** PRODUCTION_PLACE - 产地 */
    @Column(name = "PRODUCTION_PLACE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String productionPlace;
    /** BATCH - 批号 */
    @Column(name = "BATCH", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String batch;
    /** PRODUCE_DATE - 生产 时间 */
    @Column(name = "PRODUCE_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date produceDate;
    /** VALID_DATE - 有效期至 */
    @Column(name = "VALID_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date validDate;
    /** ORIGINAL_STORAGE_SPACE_NM - 原货位 */
    @Column(name = "ORIGINAL_STORAGE_SPACE_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String originalStorageSpaceNm;
    /** TARGET_STORAGE_SPACE_NM - 目标 货位 */
    @Column(name = "TARGET_STORAGE_SPACE_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String targetStorageSpaceNm;
    /** LOADING_BUCKET_QUANTITY - 装斗 数量 */
    @Column(name = "LOADING_BUCKET_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long loadingBucketQuantity;

    public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

    public java.lang.Long getShopId() {
		return this.shopId;
	}

    public void setLoadingBucketDate(java.util.Date value) {
		this.loadingBucketDate = value;
	}

    public java.util.Date getLoadingBucketDate() {
		return this.loadingBucketDate;
	}
    public String getLoadingBucketDateString() {
		return this.loadingBucketDate!=null? DateTimeUtils.convertDateToString(this.loadingBucketDate):null;
	}

    public void setLoadingBucketManNm(java.lang.String value) {
		this.loadingBucketManNm = value;
	}

    public java.lang.String getLoadingBucketManNm() {
		return this.loadingBucketManNm;
	}

    public void setApproveManId(java.lang.Long value) {
		this.approveManId = value;
	}

    public java.lang.Long getApproveManId() {
		return this.approveManId;
	}

    public void setGoodsNm(java.lang.String value) {
		this.goodsNm = value;
	}

    public java.lang.String getGoodsNm() {
		return this.goodsNm;
	}

    public void setGoodsPinyin(java.lang.String value) {
		this.goodsPinyin = value;
	}

    public java.lang.String getGoodsPinyin() {
		return this.goodsPinyin;
	}

    public void setCommonNm(java.lang.String value) {
		this.commonNm = value;
	}

    public java.lang.String getCommonNm() {
		return this.commonNm;
	}

    public void setSpec(java.lang.String value) {
		this.spec = value;
	}

    public java.lang.String getSpec() {
		return this.spec;
	}

    public void setProduceManufacturer(java.lang.String value) {
		this.produceManufacturer = value;
	}

    public java.lang.String getProduceManufacturer() {
		return this.produceManufacturer;
	}

    public void setProductionPlace(java.lang.String value) {
		this.productionPlace = value;
	}

    public java.lang.String getProductionPlace() {
		return this.productionPlace;
	}

    public void setBatch(java.lang.String value) {
		this.batch = value;
	}

    public java.lang.String getBatch() {
		return this.batch;
	}

    public void setProduceDate(java.util.Date value) {
		this.produceDate = value;
	}

    public java.util.Date getProduceDate() {
		return this.produceDate;
	}
    public String getProduceDateString() {
		return this.produceDate!=null? DateTimeUtils.convertDateToString(this.produceDate):null;

	}

    public void setValidDate(java.util.Date value) {
		this.validDate = value;
	}

    public java.util.Date getValidDate() {
		return this.validDate;
	}

	public String getValidDateString() {
		return this.validDate!=null? DateTimeUtils.convertDateToString(this.validDate):null;
	}

    public void setOriginalStorageSpaceNm(java.lang.String value) {
		this.originalStorageSpaceNm = value;
	}

    public java.lang.String getOriginalStorageSpaceNm() {
		return this.originalStorageSpaceNm;
	}

    public void setTargetStorageSpaceNm(java.lang.String value) {
		this.targetStorageSpaceNm = value;
	}

    public java.lang.String getTargetStorageSpaceNm() {
		return this.targetStorageSpaceNm;
	}

    public void setLoadingBucketQuantity(java.lang.Long value) {
		this.loadingBucketQuantity = value;
	}

    public java.lang.Long getLoadingBucketQuantity() {
		return this.loadingBucketQuantity;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("LOADING_BUCKET_DATE",getLoadingBucketDate())
			.append("LOADING_BUCKET_MAN_NM",getLoadingBucketManNm())
			.append("APPROVE_MAN_ID",getApproveManId())
			.append("GOODS_NM",getGoodsNm())
			.append("GOODS_PINYIN",getGoodsPinyin())
			.append("COMMON_NM",getCommonNm())
			.append("SPEC",getSpec())
			.append("PRODUCE_MANUFACTURER",getProduceManufacturer())
			.append("PRODUCTION_PLACE",getProductionPlace())
			.append("BATCH",getBatch())
			.append("PRODUCE_DATE",getProduceDate())
			.append("VALID_DATE",getValidDate())
			.append("ORIGINAL_STORAGE_SPACE_NM",getOriginalStorageSpaceNm())
			.append("TARGET_STORAGE_SPACE_NM",getTargetStorageSpaceNm())
			.append("LOADING_BUCKET_QUANTITY",getLoadingBucketQuantity())
			.append("CREATE_DATE",getCreateDate())
			.append("CREATE_BY",getCreateBy())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("VERSION",getVersion())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(this.getId() == null){
			return false;
		}
		if(!(obj instanceof ChineseMedicinePiecesLoadingBucketRecord)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		ChineseMedicinePiecesLoadingBucketRecord other = (ChineseMedicinePiecesLoadingBucketRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

