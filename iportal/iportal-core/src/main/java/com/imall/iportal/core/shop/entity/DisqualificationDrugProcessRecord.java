
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
@Table(name = "t_shp_disqualification_drug_process_record" )
public class DisqualificationDrugProcessRecord extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String RECORD_DATE = "recordDate";
	public static final String DOCUMENT_TYPE = "documentType";
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
	public static final String VALID_DATE = "validDate";
	public static final String QUANTITY = "quantity";
	public static final String PROCESS_MEASURE = "processMeasure";
	public static final String REMARK = "remark";

	/** SHOP_ID - 门店 ID */
	@Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long shopId;
	/** RECORD_DATE - 记录 日期 */
	@Column(name = "RECORD_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
	private java.util.Date recordDate;
	/** DOCUMENT_TYPE - 单据 类型 */
	@Column(name = "DOCUMENT_TYPE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String documentType;
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
	/** PRODUCE_MANUFACTURER - 生产厂商 */
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
	/** VALID_DATE - 有效期至 */
	@Column(name = "VALID_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
	private java.util.Date validDate;
	/** QUANTITY - 数量 */
	@Column(name = "QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
	private java.lang.Long quantity;
	/** PROCESS_MEASURE - 处理 措施 */
	@Column(name = "PROCESS_MEASURE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String processMeasure;
	/** REMARK - 备注 */
	@Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String remark;

	public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

	public java.lang.Long getShopId() {
		return this.shopId;
	}

	public void setRecordDate(java.util.Date value) {
		this.recordDate = value;
	}

	public java.util.Date getRecordDate() {
		return this.recordDate;
	}

	public void setDocumentType(java.lang.String value) {
		this.documentType = value;
	}

	public java.lang.String getDocumentType() {
		return this.documentType;
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

	public void setValidDate(java.util.Date value) {
		this.validDate = value;
	}

	public java.util.Date getValidDate() {
		return this.validDate;
	}

	public void setQuantity(java.lang.Long value) {
		this.quantity = value;
	}

	public java.lang.Long getQuantity() {
		return this.quantity;
	}

	public void setProcessMeasure(java.lang.String value) {
		this.processMeasure = value;
	}

	public java.lang.String getProcessMeasure() {
		return this.processMeasure;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public java.lang.String getRemark() {
		return this.remark;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("ID",getId())
				.append("SHOP_ID",getShopId())
				.append("RECORD_DATE",getRecordDate())
				.append("DOCUMENT_TYPE",getDocumentType())
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
				.append("VALID_DATE",getValidDate())
				.append("QUANTITY",getQuantity())
				.append("PROCESS_MEASURE",getProcessMeasure())
				.append("REMARK",getRemark())
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
		if(!(obj instanceof DisqualificationDrugProcessRecord)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		DisqualificationDrugProcessRecord other = (DisqualificationDrugProcessRecord)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}

	public String getRecordDateString() {
		return DateTimeUtils.convertDateToString(this.getRecordDate());
	}

	public void setRecordDateString(String value) throws ParseException {
		this.setRecordDate(DateTimeUtils.convertStringToDate(value));
	}

	public String getValidDateString() {
		return DateTimeUtils.convertDateToString(this.getValidDate());
	}

	public void setValidDateString(String value) throws ParseException {
		this.setValidDate(DateTimeUtils.convertStringToDate(value));
	}
}

