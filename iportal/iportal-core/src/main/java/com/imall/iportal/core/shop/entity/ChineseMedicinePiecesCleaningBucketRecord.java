
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
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_chinese_medicine_pieces_cleaning_bucket_record" )
public class ChineseMedicinePiecesCleaningBucketRecord extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String CLEANING_BUCKET_DATE = "cleaningBucketDate";
	public static final String CLEANING_BUCKET_QUANTITY = "cleaningBucketQuantity";
	public static final String GOODS_CODE = "goodsCode";
	public static final String GOODS_NM = "goodsNm";
	public static final String GOODS_PINYIN = "goodsPinyin";
	public static final String COMMON_NM = "commonNm";
	public static final String SPEC = "spec";
	public static final String DOSAGE_FORM = "dosageForm";
	public static final String UNIT = "unit";
	public static final String PRODUCE_MANUFACTURER = "produceManufacturer";
	public static final String APPROVAL_NUMBER = "approvalNumber";
	public static final String PRODUCTION_PLACE = "productionPlace";
	public static final String BATCH = "batch";
	public static final String PRODUCE_TIME = "produceTime";
	public static final String VALID_DATE = "validDate";
	public static final String STORAGE_SPACE_NM = "storageSpaceNm";
	public static final String CLEANING_BUCKET_MAN_NM = "cleaningBucketManNm";
	public static final String APPROVE_MAN_ID = "approveManId";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long shopId;
    /** CLEANING_BUCKET_DATE - 清斗 日期 */
    @Column(name = "CLEANING_BUCKET_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date cleaningBucketDate;
    /** CLEANING_BUCKET_QUANTITY - 清斗 数量 */
    @Column(name = "CLEANING_BUCKET_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long cleaningBucketQuantity;
    /** GOODS_CODE - 商品 编码 */
    @Column(name = "GOODS_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String goodsCode;
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
    /** DOSAGE_FORM - 剂型 */
    @Column(name = "DOSAGE_FORM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String dosageForm;
    /** UNIT - 单位 */
    @Column(name = "UNIT", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String unit;
    /** PRODUCE_MANUFACTURER - 生产 厂商 */
    @Column(name = "PRODUCE_MANUFACTURER", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private java.lang.String produceManufacturer;
    /** APPROVAL_NUMBER - 批准文号 */
    @Column(name = "APPROVAL_NUMBER", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private java.lang.String approvalNumber;
    /** PRODUCTION_PLACE - 产地 */
    @Column(name = "PRODUCTION_PLACE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String productionPlace;
    /** BATCH - 批号 */
    @Column(name = "BATCH", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String batch;
    /** PRODUCE_TIME - 生产 时间 */
    @Column(name = "PRODUCE_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date produceTime;
    /** VALID_DATE - 有效期至 */
    @Column(name = "VALID_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date validDate;
    /** STORAGE_SPACE_NM - 货位 */
    @Column(name = "STORAGE_SPACE_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String storageSpaceNm;
    /** CLEANING_BUCKET_MAN_NM - 清斗 人 姓名 */
    @Column(name = "CLEANING_BUCKET_MAN_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String cleaningBucketManNm;
    /** APPROVE_MAN_ID - 审核 人 ID */
    @Column(name = "APPROVE_MAN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long approveManId;

    public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

    public java.lang.Long getShopId() {
		return this.shopId;
	}

    public void setCleaningBucketDate(String value) throws ParseException {
		this.cleaningBucketDate = StringUtils.isNoneBlank(value)? DateTimeUtils.convertStringToDate(value):null;
	}

    public java.util.Date getCleaningBucketDate() {
		return this.cleaningBucketDate;
	}
    public String getCleaningBucketDateString() {
		return this.cleaningBucketDate!=null?DateTimeUtils.convertDateToString(this.cleaningBucketDate):null;
	}

    public void setCleaningBucketQuantity(java.lang.Long value) {
		this.cleaningBucketQuantity = value;
	}

    public java.lang.Long getCleaningBucketQuantity() {
		return this.cleaningBucketQuantity;
	}

    public void setGoodsCode(java.lang.String value) {
		this.goodsCode = value;
	}

    public java.lang.String getGoodsCode() {
		return this.goodsCode;
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

    public void setDosageForm(java.lang.String value) {
		this.dosageForm = value;
	}

    public java.lang.String getDosageForm() {
		return this.dosageForm;
	}

    public void setUnit(java.lang.String value) {
		this.unit = value;
	}

    public java.lang.String getUnit() {
		return this.unit;
	}

    public void setProduceManufacturer(java.lang.String value) {
		this.produceManufacturer = value;
	}

    public java.lang.String getProduceManufacturer() {
		return this.produceManufacturer;
	}

    public void setApprovalNumber(java.lang.String value) {
		this.approvalNumber = value;
	}

    public java.lang.String getApprovalNumber() {
		return this.approvalNumber;
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

    public void setProduceTimeString(String value) throws ParseException {
		this.produceTime = StringUtils.isNoneBlank(value)? DateTimeUtils.convertStringToDate(value):null;

	}

	public void setProduceTime(java.util.Date value) throws ParseException {
		this.produceTime = value;

	}



	public java.util.Date getProduceTime() {
		return this.produceTime;
	}
    public String getProduceTimeString() {
		return this.produceTime!=null?DateTimeUtils.convertDateToString(this.produceTime):null;
	}

    public void setValidDate(java.util.Date value) {
		this.validDate = value;
	}

    public java.util.Date getValidDate() {
		return this.validDate;
	}
	public String getValidDateString() {
		return this.validDate!=null?DateTimeUtils.convertDateToString(this.validDate):null;
	}

    public void setStorageSpaceNm(java.lang.String value) {
		this.storageSpaceNm = value;
	}

    public java.lang.String getStorageSpaceNm() {
		return this.storageSpaceNm;
	}

    public void setCleaningBucketManNm(java.lang.String value) {
		this.cleaningBucketManNm = value;
	}

    public java.lang.String getCleaningBucketManNm() {
		return this.cleaningBucketManNm;
	}

    public void setApproveManId(java.lang.Long value) {
		this.approveManId = value;
	}

    public java.lang.Long getApproveManId() {
		return this.approveManId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("CLEANING_BUCKET_DATE",getCleaningBucketDate())
			.append("CLEANING_BUCKET_QUANTITY",getCleaningBucketQuantity())
			.append("GOODS_CODE",getGoodsCode())
			.append("GOODS_NM",getGoodsNm())
			.append("GOODS_PINYIN",getGoodsPinyin())
			.append("COMMON_NM",getCommonNm())
			.append("SPEC",getSpec())
			.append("DOSAGE_FORM",getDosageForm())
			.append("UNIT",getUnit())
			.append("PRODUCE_MANUFACTURER",getProduceManufacturer())
			.append("APPROVAL_NUMBER",getApprovalNumber())
			.append("PRODUCTION_PLACE",getProductionPlace())
			.append("BATCH",getBatch())
			.append("PRODUCE_TIME",getProduceTime())
			.append("VALID_DATE",getValidDate())
			.append("STORAGE_SPACE_NM",getStorageSpaceNm())
			.append("CLEANING_BUCKET_MAN_NM",getCleaningBucketManNm())
			.append("APPROVE_MAN_ID",getApproveManId())
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
		if(!(obj instanceof ChineseMedicinePiecesCleaningBucketRecord)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		ChineseMedicinePiecesCleaningBucketRecord other = (ChineseMedicinePiecesCleaningBucketRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

