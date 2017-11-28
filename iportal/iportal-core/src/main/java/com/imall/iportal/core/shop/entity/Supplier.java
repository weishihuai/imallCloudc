
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
@Table(name = "t_shp_supplier" )
public class Supplier extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String SUPPLIER_DOC_ID = "supplierDocId";
	public static final String SUPPLIER_NM = "supplierNm";
	public static final String PINYIN = "pinyin";
	public static final String SUPPLIER_CODE = "supplierCode";
	public static final String UNIT_NATURE = "unitNature";
	public static final String LEGAL_REPRESENTATIVE = "legalRepresentative";
	public static final String QUALITY_RESPONSE_MAN_NAME = "qualityResponseManName";
	public static final String BUSINESS_RESPONSE_MAN_NAME = "businessResponseManName";
	public static final String BUSINESS_RESPONSE_MAN_TEL = "businessResponseManTel";
	public static final String BUSINESS_RESPONSE_MAN_EMAIL = "businessResponseManEmail";
	public static final String REG_CAPITAL = "regCapital";
	public static final String REG_ADDR = "regAddr";
	public static final String FAX = "fax";
	public static final String RETURNED_PURCHASE_ADDR = "returnedPurchaseAddr";
	public static final String BUSINESS_CATEGORY = "businessCategory";
	public static final String BUSINESS_RANGE = "businessRange";
	public static final String IS_FIRST_CHECK = "isFirstCheck";
	public static final String APPLY_MAN = "applyMan";
	public static final String APPLY_DATE = "applyDate";
	public static final String SHOP_BUSINESS_RESPONSE_MAN = "shopBusinessResponseMan";
	public static final String SHOP_BUSINESS_RESPONSE_MAN_TEL = "shopBusinessResponseManTel";
	public static final String STATE = "state";
	public static final String SUBMIT_OPINION = "submitOpinion";
	public static final String QUALITY_MNG_SYSTEM_EVALUATE = "qualityMngSystemEvaluate";
	public static final String REMARK = "remark";
	public static final String APPROVE_STATE_CODE = "approveStateCode";

	/** SHOP_ID - 门店 ID */
	@Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long shopId;
	/** SUPPLIER_DOC_ID - 供应商 档案 ID */
	@Column(name = "SUPPLIER_DOC_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	private java.lang.Long supplierDocId;
	/** SUPPLIER_NM - 供应商 名称 */
	@Column(name = "SUPPLIER_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String supplierNm;
	/** PINYIN - 拼音 */
	@Column(name = "PINYIN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String pinyin;
	/** SUPPLIER_CODE - 供应商 编码 */
	@Column(name = "SUPPLIER_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String supplierCode;
	/** UNIT_NATURE - 单位 性质 */
	@Column(name = "UNIT_NATURE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String unitNature;
	/** LEGAL_REPRESENTATIVE - 法定代表人 */
	@Column(name = "LEGAL_REPRESENTATIVE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String legalRepresentative;
	/** QUALITY_RESPONSE_MAN_NAME - 质量 负责 人 姓名 */
	@Column(name = "QUALITY_RESPONSE_MAN_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String qualityResponseManName;
	/** BUSINESS_RESPONSE_MAN_NAME - 业务 负责 人 */
	@Column(name = "BUSINESS_RESPONSE_MAN_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String businessResponseManName;
	/** BUSINESS_RESPONSE_MAN_TEL - 业务 负责 人 电话 */
	@Column(name = "BUSINESS_RESPONSE_MAN_TEL", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String businessResponseManTel;
	/** BUSINESS_RESPONSE_MAN_EMAIL - 业务 负责 人 邮箱 */
	@Column(name = "BUSINESS_RESPONSE_MAN_EMAIL", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String businessResponseManEmail;
	/** REG_CAPITAL - 注册 资本 */
	@Column(name = "REG_CAPITAL", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	private java.lang.Double regCapital;
	/** REG_ADDR - 注册 地址 */
	@Column(name = "REG_ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String regAddr;
	/** FAX - 传真 */
	@Column(name = "FAX", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String fax;
	/** RETURNED_PURCHASE_ADDR - 退货 地址 */
	@Column(name = "RETURNED_PURCHASE_ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String returnedPurchaseAddr;
	/** BUSINESS_CATEGORY - 经营 分类 */
	@Column(name = "BUSINESS_CATEGORY", unique = false, nullable = false, insertable = true, updatable = true, length = 512)
	private java.lang.String businessCategory;
	/** BUSINESS_RANGE - 经营 范围 */
	@Column(name = "BUSINESS_RANGE", unique = false, nullable = false, insertable = true, updatable = true, length = 512)
	private java.lang.String businessRange;
	/** IS_FIRST_CHECK - 是否 首营 */
	@Column(name = "IS_FIRST_CHECK", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String isFirstCheck;
	/** APPLY_MAN - 申请 人 */
	@Column(name = "APPLY_MAN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String applyMan;
	/** APPLY_DATE - 申请 日期 */
	@Column(name = "APPLY_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	private java.util.Date applyDate;
	/** SHOP_BUSINESS_RESPONSE_MAN - 门店 业务 负责 人 */
	@Column(name = "SHOP_BUSINESS_RESPONSE_MAN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String shopBusinessResponseMan;
	/** SHOP_BUSINESS_RESPONSE_MAN_TEL - 门店 业务 负责 人 电话 */
	@Column(name = "SHOP_BUSINESS_RESPONSE_MAN_TEL", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String shopBusinessResponseManTel;
	/** STATE - 状态 */
	@Column(name = "STATE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String state;
	/** SUBMIT_OPINION - 提交 意见 */
	@Column(name = "SUBMIT_OPINION", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String submitOpinion;
	/** QUALITY_MNG_SYSTEM_EVALUATE - 质量 管理 体系 评价 */
	@Column(name = "QUALITY_MNG_SYSTEM_EVALUATE", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String qualityMngSystemEvaluate;
	/** REMARK - 备注 */
	@Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String remark;
	/** APPROVE_STATE_CODE - 审核 状态 代码 */
	@Column(name = "APPROVE_STATE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String approveStateCode;

	public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

	public java.lang.Long getShopId() {
		return this.shopId;
	}

	public void setSupplierDocId(java.lang.Long value) {
		this.supplierDocId = value;
	}

	public java.lang.Long getSupplierDocId() {
		return this.supplierDocId;
	}

	public void setSupplierNm(java.lang.String value) {
		this.supplierNm = value;
	}

	public java.lang.String getSupplierNm() {
		return this.supplierNm;
	}

	public void setPinyin(java.lang.String value) {
		this.pinyin = value;
	}

	public java.lang.String getPinyin() {
		return this.pinyin;
	}

	public void setSupplierCode(java.lang.String value) {
		this.supplierCode = value;
	}

	public java.lang.String getSupplierCode() {
		return this.supplierCode;
	}

	public void setUnitNature(java.lang.String value) {
		this.unitNature = value;
	}

	public java.lang.String getUnitNature() {
		return this.unitNature;
	}

	public void setLegalRepresentative(java.lang.String value) {
		this.legalRepresentative = value;
	}

	public java.lang.String getLegalRepresentative() {
		return this.legalRepresentative;
	}

	public void setQualityResponseManName(java.lang.String value) {
		this.qualityResponseManName = value;
	}

	public java.lang.String getQualityResponseManName() {
		return this.qualityResponseManName;
	}

	public void setBusinessResponseManName(java.lang.String value) {
		this.businessResponseManName = value;
	}

	public java.lang.String getBusinessResponseManName() {
		return this.businessResponseManName;
	}

	public void setBusinessResponseManTel(java.lang.String value) {
		this.businessResponseManTel = value;
	}

	public java.lang.String getBusinessResponseManTel() {
		return this.businessResponseManTel;
	}

	public void setBusinessResponseManEmail(java.lang.String value) {
		this.businessResponseManEmail = value;
	}

	public java.lang.String getBusinessResponseManEmail() {
		return this.businessResponseManEmail;
	}

	public void setRegCapital(java.lang.Double value) {
		this.regCapital = value;
	}

	public java.lang.Double getRegCapital() {
		return this.regCapital;
	}

	public void setRegAddr(java.lang.String value) {
		this.regAddr = value;
	}

	public java.lang.String getRegAddr() {
		return this.regAddr;
	}

	public void setFax(java.lang.String value) {
		this.fax = value;
	}

	public java.lang.String getFax() {
		return this.fax;
	}

	public void setReturnedPurchaseAddr(java.lang.String value) {
		this.returnedPurchaseAddr = value;
	}

	public java.lang.String getReturnedPurchaseAddr() {
		return this.returnedPurchaseAddr;
	}

	public void setBusinessCategory(java.lang.String value) {
		this.businessCategory = value;
	}

	public java.lang.String getBusinessCategory() {
		return this.businessCategory;
	}

	public void setBusinessRange(java.lang.String value) {
		this.businessRange = value;
	}

	public java.lang.String getBusinessRange() {
		return this.businessRange;
	}

	public void setIsFirstCheck(java.lang.String value) {
		this.isFirstCheck = value;
	}

	public java.lang.String getIsFirstCheck() {
		return this.isFirstCheck;
	}

	public void setApplyMan(java.lang.String value) {
		this.applyMan = value;
	}

	public java.lang.String getApplyMan() {
		return this.applyMan;
	}

	public void setApplyDate(java.util.Date value) {
		this.applyDate = value;
	}

	public java.util.Date getApplyDate() {
		return this.applyDate;
	}

	public void setShopBusinessResponseMan(java.lang.String value) {
		this.shopBusinessResponseMan = value;
	}

	public java.lang.String getShopBusinessResponseMan() {
		return this.shopBusinessResponseMan;
	}

	public void setShopBusinessResponseManTel(java.lang.String value) {
		this.shopBusinessResponseManTel = value;
	}

	public java.lang.String getShopBusinessResponseManTel() {
		return this.shopBusinessResponseManTel;
	}

	public void setState(java.lang.String value) {
		this.state = value;
	}

	public java.lang.String getState() {
		return this.state;
	}

	public void setSubmitOpinion(java.lang.String value) {
		this.submitOpinion = value;
	}

	public java.lang.String getSubmitOpinion() {
		return this.submitOpinion;
	}

	public void setQualityMngSystemEvaluate(java.lang.String value) {
		this.qualityMngSystemEvaluate = value;
	}

	public java.lang.String getQualityMngSystemEvaluate() {
		return this.qualityMngSystemEvaluate;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setApproveStateCode(java.lang.String value) {
		this.approveStateCode = value;
	}

	public java.lang.String getApproveStateCode() {
		return this.approveStateCode;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("ID",getId())
				.append("SHOP_ID",getShopId())
				.append("SUPPLIER_DOC_ID",getSupplierDocId())
				.append("SUPPLIER_NM",getSupplierNm())
				.append("PINYIN",getPinyin())
				.append("SUPPLIER_CODE",getSupplierCode())
				.append("UNIT_NATURE",getUnitNature())
				.append("LEGAL_REPRESENTATIVE",getLegalRepresentative())
				.append("QUALITY_RESPONSE_MAN_NAME",getQualityResponseManName())
				.append("BUSINESS_RESPONSE_MAN_NAME",getBusinessResponseManName())
				.append("BUSINESS_RESPONSE_MAN_TEL",getBusinessResponseManTel())
				.append("BUSINESS_RESPONSE_MAN_EMAIL",getBusinessResponseManEmail())
				.append("REG_CAPITAL",getRegCapital())
				.append("REG_ADDR",getRegAddr())
				.append("FAX",getFax())
				.append("RETURNED_PURCHASE_ADDR",getReturnedPurchaseAddr())
				.append("BUSINESS_CATEGORY",getBusinessCategory())
				.append("BUSINESS_RANGE",getBusinessRange())
				.append("IS_FIRST_CHECK",getIsFirstCheck())
				.append("APPLY_MAN",getApplyMan())
				.append("APPLY_DATE",getApplyDate())
				.append("SHOP_BUSINESS_RESPONSE_MAN",getShopBusinessResponseMan())
				.append("SHOP_BUSINESS_RESPONSE_MAN_TEL",getShopBusinessResponseManTel())
				.append("STATE",getState())
				.append("SUBMIT_OPINION",getSubmitOpinion())
				.append("QUALITY_MNG_SYSTEM_EVALUATE",getQualityMngSystemEvaluate())
				.append("REMARK",getRemark())
				.append("CREATE_DATE",getCreateDate())
				.append("CREATE_BY",getCreateBy())
				.append("LAST_MODIFIED_DATE",getLastModifiedDate())
				.append("LAST_MODIFIED_BY",getLastModifiedBy())
				.append("VERSION",getVersion())
				.append("APPROVE_STATE_CODE",getApproveStateCode())
				.toString();
	}

    public String getApplyDateString() {
        return DateTimeUtils.convertDateToString(getApplyDate());
    }

    public void setApplyDateString(String value) throws ParseException {
		setApplyDate(StringUtils.isNotBlank(value) ? DateTimeUtils.convertStringToDate(value) : null);
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
		if(!(obj instanceof Supplier)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		Supplier other = (Supplier)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}
}

