
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
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_destroy_record" )
public class DestroyRecord extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String GOODS_CODE = "goodsCode";
	public static final String GOODS_NM = "goodsNm";
	public static final String GOODS_PINYIN = "goodsPinyin";
	public static final String COMMON_NM = "commonNm";
	public static final String SPEC = "spec";
	public static final String DOSAGE_FORM = "dosageForm";
	public static final String UNIT = "unit";
	public static final String PRODUCE_MANUFACTURER = "produceManufacturer";
	public static final String PRODUCTION_PLACE = "productionPlace";
	public static final String APPROVAL_NUMBER = "approvalNumber";
	public static final String SUPPLIER_NM = "supplierNm";
	public static final String BATCH = "batch";
	public static final String STOCK_QUANTITY = "stockQuantity";
	public static final String LOCK_QUANTITY = "lockQuantity";
	public static final String LOCK_REASON = "lockReason";
	public static final String PRODUCE_DATE = "produceDate";
	public static final String VALID_DATE = "validDate";
	public static final String STORAGE_SPACE_NM = "storageSpaceNm";
	public static final String DESTROY_PLACE = "destroyPlace";
	public static final String DESTROY_MAN = "destroyMan";
	public static final String DESTROY_TIME = "destroyTime";
	public static final String KEEPER = "keeper";
	public static final String DESTROY_REASON = "destroyReason";
	public static final String APPROVE_MAN_ID = "approveManId";
	public static final String REVIEWER_ID = "reviewerId";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** GOODS_CODE - 商品 编码 */
    @Column(name = "GOODS_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String goodsCode;
    /** GOODS_NM - 商品 名称 */
    @Column(name = "GOODS_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String goodsNm;
    /** GOODS_PINYIN - 商品 拼音 */
    @Column(name = "GOODS_PINYIN", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String goodsPinyin;
    /** COMMON_NM - 通用 名称 */
    @Column(name = "COMMON_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String commonNm;
    /** SPEC - 规格 */
    @Column(name = "SPEC", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String spec;
    /** DOSAGE_FORM - 剂型 */
    @Column(name = "DOSAGE_FORM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String dosageForm;
    /** UNIT - 单位 */
    @Column(name = "UNIT", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String unit;
    /** PRODUCE_MANUFACTURER - 生产厂商 */
    @Column(name = "PRODUCE_MANUFACTURER", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String produceManufacturer;
    /** PRODUCTION_PLACE - 产地 */
    @Column(name = "PRODUCTION_PLACE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String productionPlace;
    /** APPROVAL_NUMBER - 批准文号 */
    @Column(name = "APPROVAL_NUMBER", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String approvalNumber;
    /** SUPPLIER_NM - 供应商 名称 */
    @Column(name = "SUPPLIER_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String supplierNm;
    /** BATCH - 批号 */
    @Column(name = "BATCH", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String batch;
    /** STOCK_QUANTITY - 库存 数量 */
    @Column(name = "STOCK_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long stockQuantity;
    /** LOCK_QUANTITY - 锁定 数量 */
    @Column(name = "LOCK_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long lockQuantity;
    /** LOCK_REASON - 锁定 原因 */
    @Column(name = "LOCK_REASON", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String lockReason;
    /** PRODUCE_DATE - 生产 时间 */
    @Column(name = "PRODUCE_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date produceDate;
    /** VALID_DATE - 有效期至 */
    @Column(name = "VALID_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date validDate;
    /** STORAGE_SPACE_NM - 货位 */
    @Column(name = "STORAGE_SPACE_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String storageSpaceNm;
    /** DESTROY_PLACE - 销毁 地点 */
    @Column(name = "DESTROY_PLACE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String destroyPlace;
    /** DESTROY_MAN - 销毁 人 */
    @Column(name = "DESTROY_MAN", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String destroyMan;
    /** DESTROY_TIME - 销毁 时间 */
    @Column(name = "DESTROY_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date destroyTime;
    /** KEEPER - 保管员 */
    @Column(name = "KEEPER", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String keeper;
    /** DESTROY_REASON - 销毁 原因 */
    @Column(name = "DESTROY_REASON", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String destroyReason;
    /** APPROVE_MAN_ID - 审核 人 ID */
    @Column(name = "APPROVE_MAN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long approveManId;
    /** REVIEWER_ID - 复核人 ID */
    @Column(name = "REVIEWER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long reviewerId;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setGoodsCode(String value) {
		this.goodsCode = value;
	}

    public String getGoodsCode() {
		return this.goodsCode;
	}

    public void setGoodsNm(String value) {
		this.goodsNm = value;
	}

    public String getGoodsNm() {
		return this.goodsNm;
	}

    public void setGoodsPinyin(String value) {
		this.goodsPinyin = value;
	}

    public String getGoodsPinyin() {
		return this.goodsPinyin;
	}

    public void setCommonNm(String value) {
		this.commonNm = value;
	}

    public String getCommonNm() {
		return this.commonNm;
	}

    public void setSpec(String value) {
		this.spec = value;
	}

    public String getSpec() {
		return this.spec;
	}

    public void setDosageForm(String value) {
		this.dosageForm = value;
	}

    public String getDosageForm() {
		return this.dosageForm;
	}

    public void setUnit(String value) {
		this.unit = value;
	}

    public String getUnit() {
		return this.unit;
	}

    public void setProduceManufacturer(String value) {
		this.produceManufacturer = value;
	}

    public String getProduceManufacturer() {
		return this.produceManufacturer;
	}

    public void setProductionPlace(String value) {
		this.productionPlace = value;
	}

    public String getProductionPlace() {
		return this.productionPlace;
	}

    public void setApprovalNumber(String value) {
		this.approvalNumber = value;
	}

    public String getApprovalNumber() {
		return this.approvalNumber;
	}

    public void setSupplierNm(String value) {
		this.supplierNm = value;
	}

    public String getSupplierNm() {
		return this.supplierNm;
	}

    public void setBatch(String value) {
		this.batch = value;
	}

    public String getBatch() {
		return this.batch;
	}

    public void setStockQuantity(Long value) {
		this.stockQuantity = value;
	}

    public Long getStockQuantity() {
		return this.stockQuantity;
	}

    public void setLockQuantity(Long value) {
		this.lockQuantity = value;
	}

    public Long getLockQuantity() {
		return this.lockQuantity;
	}

    public void setLockReason(String value) {
		this.lockReason = value;
	}

    public String getLockReason() {
		return this.lockReason;
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

    public void setStorageSpaceNm(String value) {
		this.storageSpaceNm = value;
	}

    public String getStorageSpaceNm() {
		return this.storageSpaceNm;
	}

    public void setDestroyPlace(String value) {
		this.destroyPlace = value;
	}

    public String getDestroyPlace() {
		return this.destroyPlace;
	}

    public void setDestroyMan(String value) {
		this.destroyMan = value;
	}

    public String getDestroyMan() {
		return this.destroyMan;
	}

    public void setDestroyTime(java.util.Date value) {
		this.destroyTime = value;
	}

    public java.util.Date getDestroyTime() {
		return this.destroyTime;
	}

    public void setKeeper(String value) {
		this.keeper = value;
	}

    public String getKeeper() {
		return this.keeper;
	}

    public void setDestroyReason(String value) {
		this.destroyReason = value;
	}

    public String getDestroyReason() {
		return this.destroyReason;
	}

    public void setApproveManId(Long value) {
		this.approveManId = value;
	}

    public Long getApproveManId() {
		return this.approveManId;
	}

    public void setReviewerId(Long value) {
		this.reviewerId = value;
	}

    public Long getReviewerId() {
		return this.reviewerId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("GOODS_CODE",getGoodsCode())
			.append("GOODS_NM",getGoodsNm())
			.append("GOODS_PINYIN",getGoodsPinyin())
			.append("COMMON_NM",getCommonNm())
			.append("SPEC",getSpec())
			.append("DOSAGE_FORM",getDosageForm())
			.append("UNIT",getUnit())
			.append("PRODUCE_MANUFACTURER",getProduceManufacturer())
			.append("PRODUCTION_PLACE",getProductionPlace())
			.append("APPROVAL_NUMBER",getApprovalNumber())
			.append("SUPPLIER_NM",getSupplierNm())
			.append("BATCH",getBatch())
			.append("STOCK_QUANTITY",getStockQuantity())
			.append("LOCK_QUANTITY",getLockQuantity())
			.append("LOCK_REASON",getLockReason())
			.append("PRODUCE_DATE",getProduceDate())
			.append("VALID_DATE",getValidDate())
			.append("STORAGE_SPACE_NM",getStorageSpaceNm())
			.append("DESTROY_PLACE",getDestroyPlace())
			.append("DESTROY_MAN",getDestroyMan())
			.append("DESTROY_TIME",getDestroyTime())
			.append("KEEPER",getKeeper())
			.append("DESTROY_REASON",getDestroyReason())
			.append("APPROVE_MAN_ID",getApproveManId())
			.append("REVIEWER_ID",getReviewerId())
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
		if(!(obj instanceof DestroyRecord)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		DestroyRecord other = (DestroyRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}


	public String getProduceDateString() {
		return DateTimeUtils.convertDateToString(this.getProduceDate());
	}

	public void setProduceDateString(String value) throws ParseException {
		this.setProduceDate(DateTimeUtils.convertStringToDate(value));
	}

	public String getValidDateString() {
		return DateTimeUtils.convertDateToString(this.getValidDate());
	}

	public void setValidDateString(String value) throws ParseException {
		this.setValidDate(DateTimeUtils.convertStringToDate(value));
	}

	public String getDestroyTimeString() {
		return DateTimeUtils.convertDateToString(this.getDestroyTime());
	}

	public void setDestroyTimeString(String value) throws ParseException {
		this.setDestroyTime(DateTimeUtils.convertStringToDate(value));
	}

}

