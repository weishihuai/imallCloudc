
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
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_ptfm_shop" )
public class Shop extends BaseEntity<Long>{

	public static final String ORG_ID = "orgId";
	public static final String PINYIN = "pinyin";
	public static final String SHOP_CODE = "shopCode";
	public static final String ENT_NM = "entNm";
	public static final String LEGAL_REPRESENTATIVE_MAN = "legalRepresentativeMan";
	public static final String ENT_RESPONSE_MAN = "entResponseMan";
	public static final String ENT_CEO_MOBILE = "entCeoMobile";
	public static final String ENT_TYPE_CODE = "entTypeCode";
	public static final String IS_MEDICAL_INSURANCE_SHOP = "isMedicalInsuranceShop";
	public static final String BUSINESS_WAY = "businessWay";
	public static final String REG_CAPITAL = "regCapital";
	public static final String REG_ADDR = "regAddr";
	public static final String COMPANY_ADDR = "companyAddr";
	public static final String STORAGE_ADDR = "storageAddr";
	public static final String COMPANY_TEL = "companyTel";
	public static final String COMPANY_FAX = "companyFax";
	public static final String TAX_REGISTER_CERT_NUM = "taxRegisterCertNum";
	public static final String ANNUAL_INSPECTION_DATE = "annualInspectionDate";
	public static final String COMPANY_BRIEF = "companyBrief";
	public static final String BUSINESS_RANGE = "businessRange";
	public static final String IS_ENABLE = "isEnable";
	public static final String REMARK = "remark";

	/** ORG_ID - 机构 ID */
	@Column(name = "ORG_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long orgId;
	/** PINYIN - 拼音 */
	@Column(name = "PINYIN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String pinyin;
	/** SHOP_CODE - 门店 编码 */
	@Column(name = "SHOP_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String shopCode;
	/** ENT_NM - 企业 名称 */
	@Column(name = "ENT_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String entNm;
	/** LEGAL_REPRESENTATIVE_MAN - 法定代表人 */
	@Column(name = "LEGAL_REPRESENTATIVE_MAN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String legalRepresentativeMan;
	/** ENT_RESPONSE_MAN - 企业 负责 人 姓名 */
	@Column(name = "ENT_RESPONSE_MAN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String entResponseMan;
	/** ENT_CEO_MOBILE - 企业 负责人 手机 */
	@Column(name = "ENT_CEO_MOBILE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String entCeoMobile;
	/** ENT_TYPE_CODE - 企业 类型 代码 */
	@Column(name = "ENT_TYPE_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String entTypeCode;
	/** IS_MEDICAL_INSURANCE_SHOP - 是否 医保 门店 */
	@Column(name = "IS_MEDICAL_INSURANCE_SHOP", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String isMedicalInsuranceShop;
	/** BUSINESS_WAY - 经营 方式 */
	@Column(name = "BUSINESS_WAY", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String businessWay;
	/** REG_CAPITAL - 注册 资本 */
	@Column(name = "REG_CAPITAL", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	private java.lang.Double regCapital;
	/** REG_ADDR - 注册 地址 */
	@Column(name = "REG_ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String regAddr;
	/** COMPANY_ADDR - 公司 地址 */
	@Column(name = "COMPANY_ADDR", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
	private java.lang.String companyAddr;
	/** STORAGE_ADDR - 仓库 地址 */
	@Column(name = "STORAGE_ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String storageAddr;
	/** COMPANY_TEL - 公司 电话 */
	@Column(name = "COMPANY_TEL", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String companyTel;
	/** COMPANY_FAX - 公司 传真 */
	@Column(name = "COMPANY_FAX", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String companyFax;
	/** TAX_REGISTER_CERT_NUM - 税务 登记 证件 编号 */
	@Column(name = "TAX_REGISTER_CERT_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String taxRegisterCertNum;
	/** ANNUAL_INSPECTION_DATE - 年检 日期 */
	@Column(name = "ANNUAL_INSPECTION_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	private java.util.Date annualInspectionDate;
	/** COMPANY_BRIEF - 公司 简介 */
	@Column(name = "COMPANY_BRIEF", unique = false, nullable = true, insertable = true, updatable = true, length = 1024)
	private java.lang.String companyBrief;
	/** BUSINESS_RANGE - 经营 范围 */
	@Column(name = "BUSINESS_RANGE", unique = false, nullable = true, insertable = true, updatable = true, length = 1024)
	private java.lang.String businessRange;
	/** IS_ENABLE - 是否 启用 */
	@Column(name = "IS_ENABLE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	private java.lang.String isEnable;
	/** REMARK - 备注 */
	@Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	private java.lang.String remark;

	public void setOrgId(java.lang.Long value) {
		this.orgId = value;
	}

	public java.lang.Long getOrgId() {
		return this.orgId;
	}

	public void setPinyin(java.lang.String value) {
		this.pinyin = value;
	}

	public java.lang.String getPinyin() {
		return this.pinyin;
	}

	public void setShopCode(java.lang.String value) {
		this.shopCode = value;
	}

	public java.lang.String getShopCode() {
		return this.shopCode;
	}

	public void setEntNm(java.lang.String value) {
		this.entNm = value;
	}

	public java.lang.String getEntNm() {
		return this.entNm;
	}

	public void setLegalRepresentativeMan(java.lang.String value) {
		this.legalRepresentativeMan = value;
	}

	public java.lang.String getLegalRepresentativeMan() {
		return this.legalRepresentativeMan;
	}

	public void setEntResponseMan(java.lang.String value) {
		this.entResponseMan = value;
	}

	public java.lang.String getEntResponseMan() {
		return this.entResponseMan;
	}

	public void setEntCeoMobile(java.lang.String value) {
		this.entCeoMobile = value;
	}

	public java.lang.String getEntCeoMobile() {
		return this.entCeoMobile;
	}

	public void setEntTypeCode(java.lang.String value) {
		this.entTypeCode = value;
	}

	public java.lang.String getEntTypeCode() {
		return this.entTypeCode;
	}

	public void setIsMedicalInsuranceShop(java.lang.String value) {
		this.isMedicalInsuranceShop = value;
	}

	public java.lang.String getIsMedicalInsuranceShop() {
		return this.isMedicalInsuranceShop;
	}

	public void setBusinessWay(java.lang.String value) {
		this.businessWay = value;
	}

	public java.lang.String getBusinessWay() {
		return this.businessWay;
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

	public void setCompanyAddr(java.lang.String value) {
		this.companyAddr = value;
	}

	public java.lang.String getCompanyAddr() {
		return this.companyAddr;
	}

	public void setStorageAddr(java.lang.String value) {
		this.storageAddr = value;
	}

	public java.lang.String getStorageAddr() {
		return this.storageAddr;
	}

	public void setCompanyTel(java.lang.String value) {
		this.companyTel = value;
	}

	public java.lang.String getCompanyTel() {
		return this.companyTel;
	}

	public void setCompanyFax(java.lang.String value) {
		this.companyFax = value;
	}

	public java.lang.String getCompanyFax() {
		return this.companyFax;
	}

	public void setTaxRegisterCertNum(java.lang.String value) {
		this.taxRegisterCertNum = value;
	}

	public java.lang.String getTaxRegisterCertNum() {
		return this.taxRegisterCertNum;
	}

	public void setAnnualInspectionDate(java.util.Date value) {
		this.annualInspectionDate = value;
	}

	public java.util.Date getAnnualInspectionDate() {
		return this.annualInspectionDate;
	}

	public void setCompanyBrief(java.lang.String value) {
		this.companyBrief = value;
	}

	public java.lang.String getCompanyBrief() {
		return this.companyBrief;
	}

	public void setBusinessRange(java.lang.String value) {
		this.businessRange = value;
	}

	public java.lang.String getBusinessRange() {
		return this.businessRange;
	}

	public void setIsEnable(java.lang.String value) {
		this.isEnable = value;
	}

	public java.lang.String getIsEnable() {
		return this.isEnable;
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
				.append("ORG_ID",getOrgId())
				.append("PINYIN",getPinyin())
				.append("SHOP_CODE",getShopCode())
				.append("ENT_NM",getEntNm())
				.append("LEGAL_REPRESENTATIVE_MAN",getLegalRepresentativeMan())
				.append("ENT_RESPONSE_MAN",getEntResponseMan())
				.append("ENT_CEO_MOBILE",getEntCeoMobile())
				.append("ENT_TYPE_CODE",getEntTypeCode())
				.append("IS_MEDICAL_INSURANCE_SHOP",getIsMedicalInsuranceShop())
				.append("BUSINESS_WAY",getBusinessWay())
				.append("REG_CAPITAL",getRegCapital())
				.append("REG_ADDR",getRegAddr())
				.append("COMPANY_ADDR",getCompanyAddr())
				.append("STORAGE_ADDR",getStorageAddr())
				.append("COMPANY_TEL",getCompanyTel())
				.append("COMPANY_FAX",getCompanyFax())
				.append("TAX_REGISTER_CERT_NUM",getTaxRegisterCertNum())
				.append("ANNUAL_INSPECTION_DATE",getAnnualInspectionDate())
				.append("COMPANY_BRIEF",getCompanyBrief())
				.append("BUSINESS_RANGE",getBusinessRange())
				.append("IS_ENABLE",getIsEnable())
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
		if(!(obj instanceof Shop)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		Shop other = (Shop)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}
}

