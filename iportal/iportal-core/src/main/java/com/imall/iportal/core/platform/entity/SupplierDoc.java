
package com.imall.iportal.core.platform.entity;

import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 供应商 档案实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_ptfm_supplier_doc" )
public class SupplierDoc extends BaseEntity<Long>{

	public static final String PINYIN = "pinyin";
	public static final String SUPPLIER_NM = "supplierNm";
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
	public static final String STATE = "state";

	/** PINYIN - 拼音 */
	@Column(name = "PINYIN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private String pinyin;
	/** SUPPLIER_NM - 供应商 名称 */
	@Column(name = "SUPPLIER_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private String supplierNm;
	/** SUPPLIER_CODE - 供应商 编码 */
	@Column(name = "SUPPLIER_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private String supplierCode;
	/** UNIT_NATURE - 单位 性质 */
	@Column(name = "UNIT_NATURE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private String unitNature;
	/** LEGAL_REPRESENTATIVE - 法定代表人 */
	@Column(name = "LEGAL_REPRESENTATIVE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private String legalRepresentative;
	/** QUALITY_RESPONSE_MAN_NAME - 质量 负责 人 姓名 */
	@Column(name = "QUALITY_RESPONSE_MAN_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private String qualityResponseManName;
	/** BUSINESS_RESPONSE_MAN_NAME - 业务 负责 人 */
	@Column(name = "BUSINESS_RESPONSE_MAN_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private String businessResponseManName;
	/** BUSINESS_RESPONSE_MAN_TEL - 业务 负责 人 电话 */
	@Column(name = "BUSINESS_RESPONSE_MAN_TEL", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private String businessResponseManTel;
	/** BUSINESS_RESPONSE_MAN_EMAIL - 业务 负责 人 邮箱 */
	@Column(name = "BUSINESS_RESPONSE_MAN_EMAIL", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private String businessResponseManEmail;
	/** REG_CAPITAL - 注册 资本 */
	@Column(name = "REG_CAPITAL", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	private Double regCapital;
	/** REG_ADDR - 注册 地址 */
	@Column(name = "REG_ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private String regAddr;
	/** FAX - 传真 */
	@Column(name = "FAX", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private String fax;
	/** RETURNED_PURCHASE_ADDR - 退货 地址 */
	@Column(name = "RETURNED_PURCHASE_ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private String returnedPurchaseAddr;
	/** BUSINESS_CATEGORY - 经营 分类 */
	@Column(name = "BUSINESS_CATEGORY", unique = false, nullable = false, insertable = true, updatable = true, length = 512)
	private String businessCategory;
	/** BUSINESS_RANGE - 经营 范围 */
	@Column(name = "BUSINESS_RANGE", unique = false, nullable = false, insertable = true, updatable = true, length = 512)
	private String businessRange;
	/** STATE - 状态 */
	@Column(name = "STATE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private String state;

	public void setPinyin(String value) {
		this.pinyin = value;
	}

	public String getPinyin() {
		return this.pinyin;
	}

	public void setSupplierNm(String value) {
		this.supplierNm = value;
	}

	public String getSupplierNm() {
		return this.supplierNm;
	}

	public void setSupplierCode(String value) {
		this.supplierCode = value;
	}

	public String getSupplierCode() {
		return this.supplierCode;
	}

	public void setUnitNature(String value) {
		this.unitNature = value;
	}

	public String getUnitNature() {
		return this.unitNature;
	}

	public void setLegalRepresentative(String value) {
		this.legalRepresentative = value;
	}

	public String getLegalRepresentative() {
		return this.legalRepresentative;
	}

	public void setQualityResponseManName(String value) {
		this.qualityResponseManName = value;
	}

	public String getQualityResponseManName() {
		return this.qualityResponseManName;
	}

	public void setBusinessResponseManName(String value) {
		this.businessResponseManName = value;
	}

	public String getBusinessResponseManName() {
		return this.businessResponseManName;
	}

	public void setBusinessResponseManTel(String value) {
		this.businessResponseManTel = value;
	}

	public String getBusinessResponseManTel() {
		return this.businessResponseManTel;
	}

	public void setBusinessResponseManEmail(String value) {
		this.businessResponseManEmail = value;
	}

	public String getBusinessResponseManEmail() {
		return this.businessResponseManEmail;
	}

	public void setRegCapital(Double value) {
		this.regCapital = value;
	}

	public Double getRegCapital() {
		return this.regCapital;
	}

	public void setRegAddr(String value) {
		this.regAddr = value;
	}

	public String getRegAddr() {
		return this.regAddr;
	}

	public void setFax(String value) {
		this.fax = value;
	}

	public String getFax() {
		return this.fax;
	}

	public void setReturnedPurchaseAddr(String value) {
		this.returnedPurchaseAddr = value;
	}

	public String getReturnedPurchaseAddr() {
		return this.returnedPurchaseAddr;
	}

	public void setBusinessCategory(String value) {
		this.businessCategory = value;
	}

	public String getBusinessCategory() {
		return this.businessCategory;
	}

	public void setBusinessRange(String value) {
		this.businessRange = value;
	}

	public String getBusinessRange() {
		return this.businessRange;
	}

	public void setState(String value) {
		this.state = value;
	}

	public String getState() {
		return this.state;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("ID",getId())
				.append("PINYIN",getPinyin())
				.append("SUPPLIER_NM",getSupplierNm())
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
				.append("CREATE_DATE",getCreateDate())
				.append("CREATE_BY",getCreateBy())
				.append("LAST_MODIFIED_DATE",getLastModifiedDate())
				.append("LAST_MODIFIED_BY",getLastModifiedBy())
				.append("VERSION",getVersion())
				.append("STATE",getState())
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
		if(!(obj instanceof SupplierDoc)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SupplierDoc other = (SupplierDoc)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}
}

