
package com.imall.iportal.core.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.math.BigDecimal;
import java.text.ParseException;

/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_first_manage_drug_quality_approve" )
public class FirstManageDrugQualityApprove extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String GOODS_ID = "goodsId";
	public static final String GOODS_CODE = "goodsCode";
	public static final String GOODS_NM = "goodsNm";
	public static final String COMMON_NM_FIRST_SPELL = "commonNmFirstSpell";
	public static final String COMMON_NM = "commonNm";
	public static final String SPEC = "spec";
	public static final String DOSAGE_FORM = "dosageForm";
	public static final String UNIT = "unit";
	public static final String PRODUCE_MANUFACTURER = "produceManufacturer";
	public static final String APPROVAL_NUMBER = "approvalNumber";
	public static final String APPROVE_STATE_CODE = "approveStateCode";
	public static final String QUALITY_APPROVE_TIME = "qualityApproveTime";
	public static final String APPLY_MAN_NAME = "applyManName";
	public static final String SUBMIT_ADVICE = "submitAdvice";
	public static final String APPLY_REMARK = "applyRemark";

	/** SHOP_ID - 门店 ID */
	@Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long shopId;
	/** GOODS_ID - 商品 ID */
	@Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long goodsId;
	/** GOODS_CODE - 商品 编码 */
	@Column(name = "GOODS_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
	private java.lang.String goodsCode;
	/** GOODS_NM - 商品 名称 */
	@Column(name = "GOODS_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
	private java.lang.String goodsNm;
	/** COMMON_NM_FIRST_SPELL - 通用 名称 首拼 */
	@Column(name = "COMMON_NM_FIRST_SPELL", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
	private java.lang.String commonNmFirstSpell;
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
	/** APPROVE_STATE_CODE - 审核 状态 代码 */
	@Column(name = "APPROVE_STATE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String approveStateCode;
	/** QUALITY_APPROVE_TIME - 质量 审核 时间 */
	@Column(name = "QUALITY_APPROVE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	private java.util.Date qualityApproveTime;
	/** APPLY_MAN_NAME - 申请 人 姓名 */
	@Column(name = "APPLY_MAN_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String applyManName;
	/** SUBMIT_ADVICE - 提交 意见 */
	@Column(name = "SUBMIT_ADVICE", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	private java.lang.String submitAdvice;
	/** APPLY_REMARK - 申请 备注 */
	@Column(name = "APPLY_REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String applyRemark;

	public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

	public java.lang.Long getShopId() {
		return this.shopId;
	}

	public void setGoodsId(java.lang.Long value) {
		this.goodsId = value;
	}

	public java.lang.Long getGoodsId() {
		return this.goodsId;
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

	public void setCommonNmFirstSpell(java.lang.String value) {
		this.commonNmFirstSpell = value;
	}

	public java.lang.String getCommonNmFirstSpell() {
		return this.commonNmFirstSpell;
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

	public void setApproveStateCode(java.lang.String value) {
		this.approveStateCode = value;
	}

	public java.lang.String getApproveStateCode() {
		return this.approveStateCode;
	}

	public void setQualityApproveTime(java.util.Date value) {
		this.qualityApproveTime = value;
	}

	public java.util.Date getQualityApproveTime() {
		return this.qualityApproveTime;
	}

	public void setApplyManName(java.lang.String value) {
		this.applyManName = value;
	}

	public java.lang.String getApplyManName() {
		return this.applyManName;
	}

	public void setSubmitAdvice(java.lang.String value) {
		this.submitAdvice = value;
	}

	public java.lang.String getSubmitAdvice() {
		return this.submitAdvice;
	}

	public void setApplyRemark(java.lang.String value) {
		this.applyRemark = value;
	}

	public java.lang.String getApplyRemark() {
		return this.applyRemark;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("ID",getId())
				.append("SHOP_ID",getShopId())
				.append("GOODS_ID",getGoodsId())
				.append("GOODS_CODE",getGoodsCode())
				.append("GOODS_NM",getGoodsNm())
				.append("COMMON_NM_FIRST_SPELL",getCommonNmFirstSpell())
				.append("COMMON_NM",getCommonNm())
				.append("SPEC",getSpec())
				.append("DOSAGE_FORM",getDosageForm())
				.append("UNIT",getUnit())
				.append("PRODUCE_MANUFACTURER",getProduceManufacturer())
				.append("APPROVAL_NUMBER",getApprovalNumber())
				.append("APPROVE_STATE_CODE",getApproveStateCode())
				.append("QUALITY_APPROVE_TIME",getQualityApproveTime())
				.append("APPLY_MAN_NAME",getApplyManName())
				.append("SUBMIT_ADVICE",getSubmitAdvice())
				.append("APPLY_REMARK",getApplyRemark())
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
		if(!(obj instanceof FirstManageDrugQualityApprove)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		FirstManageDrugQualityApprove other = (FirstManageDrugQualityApprove)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}

	public String getQualityApproveTimeStr() {
		return DateTimeUtils.convertDateToString(getQualityApproveTime());
	}
	public void setQualityApproveTimeStr(String value) throws ParseException {
		setQualityApproveTime(StringUtils.isBlank(value)?null:DateTimeUtils.convertStringToDate(value));
	}

	public String getCreateDateStr() {
		return DateTimeUtils.convertDateToString(getCreateDate());
	}
	public void setCreateDateStr(String value) throws ParseException {
		setCreateDate(StringUtils.isBlank(value)?null:DateTimeUtils.convertStringToDate(value));
	}

}

