
package com.imall.iportal.core.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.util.DateTimeUtils;
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
@Table(name = "t_shp_release_goods_inf" )
public class ReleaseGoodsInf extends BaseEntity<Long>{

	public static final String DRUG_RELEASE_NOTICE_ID = "drugReleaseNoticeId";
	public static final String GOODS_NM = "goodsNm";
	public static final String GOODS_CODE = "goodsCode";
	public static final String COMMON_NM = "commonNm";
	public static final String GOODS_PINYIN = "goodsPinyin";
	public static final String SPEC = "spec";
	public static final String DOSAGE_FORM = "dosageForm";
	public static final String UNIT = "unit";
	public static final String PRODUCE_MANUFACTURER= "produceManufacturer";
	public static final String PRODUCTION_PLACE = "productionPlace";
	public static final String APPROVAL_NUMBER = "approvalNumber";
	public static final String BATCH = "batch";
	public static final String VALID_DATE = "validDate";

    /** DRUG_STOP_SALE_NOTICE_ID - 药品 解停 通知单 ID */
    @Column(name = "DRUG_RELEASE_NOTICE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long drugReleaseNoticeId;
    /** GOODS_NM - 商品 名称 */
    @Column(name = "GOODS_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String goodsNm;
    /** GOODS_CODE - 商品 编码 */
    @Column(name = "GOODS_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String goodsCode;
    /** COMMON_NM - 通用 名称 */
    @Column(name = "COMMON_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String commonNm;
    /** GOODS_PINYIN - 商品 拼音 */
    @Column(name = "GOODS_PINYIN", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String goodsPinyin;
    /** SPEC - 规格 */
    @Column(name = "SPEC", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String spec;
    /** DOSAGE_FORM - 剂型 */
    @Column(name = "DOSAGE_FORM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String dosageForm;
    /** UNIT - 单位 */
    @Column(name = "UNIT", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String unit;
    /** MANUFACTURE - 生产厂商 */
    @Column(name = "PRODUCE_MANUFACTURER", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String produceManufacturer;
    /** PRODUCTION_PLACE - 产地 */
    @Column(name = "PRODUCTION_PLACE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String productionPlace;
    /** APPROVAL_NUMBER - 批准文号 */
    @Column(name = "APPROVAL_NUMBER", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String approvalNumber;
    /** BATCH - 批号 */
    @Column(name = "BATCH", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String batch;
    /** VALID_DATE - 有效期至 */
    @Column(name = "VALID_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date validDate;

	public Long getDrugReleaseNoticeId() {
		return drugReleaseNoticeId;
	}

	public void setDrugReleaseNoticeId(Long drugReleaseNoticeId) {
		this.drugReleaseNoticeId = drugReleaseNoticeId;
	}

	public void setGoodsNm(String value) {
		this.goodsNm = value;
	}

    public String getGoodsNm() {
		return this.goodsNm;
	}

    public void setGoodsCode(String value) {
		this.goodsCode = value;
	}

    public String getGoodsCode() {
		return this.goodsCode;
	}

    public void setCommonNm(String value) {
		this.commonNm = value;
	}

    public String getCommonNm() {
		return this.commonNm;
	}

    public void setGoodsPinyin(String value) {
		this.goodsPinyin = value;
	}

    public String getGoodsPinyin() {
		return this.goodsPinyin;
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

	public String getProduceManufacturer() {
		return produceManufacturer;
	}

	public void setProduceManufacturer(String produceManufacturer) {
		this.produceManufacturer = produceManufacturer;
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

    public void setBatch(String value) {
		this.batch = value;
	}

    public String getBatch() {
		return this.batch;
	}

    public void setValidDate(java.util.Date value) {
		this.validDate = value;
	}

    public java.util.Date getValidDate() {
		return this.validDate;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("DRUG_RELEASE_NOTICE_ID",getDrugReleaseNoticeId())
			.append("GOODS_NM",getGoodsNm())
			.append("GOODS_CODE",getGoodsCode())
			.append("COMMON_NM",getCommonNm())
			.append("GOODS_PINYIN",getGoodsPinyin())
			.append("SPEC",getSpec())
			.append("DOSAGE_FORM",getDosageForm())
			.append("UNIT",getUnit())
			.append("PRODUCE_MANUFACTURER",getProduceManufacturer())
			.append("PRODUCTION_PLACE",getProductionPlace())
			.append("APPROVAL_NUMBER",getApprovalNumber())
			.append("BATCH",getBatch())
			.append("VALID_DATE",getValidDate())
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
		if(!(obj instanceof ReleaseGoodsInf)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		ReleaseGoodsInf other = (ReleaseGoodsInf)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	public String getValidDateStr() {
		return DateTimeUtils.convertDateToString(getValidDate());
	}
	public void setValidDateStr(String value) throws ParseException {
		setValidDate(DateTimeUtils.convertStringToDate(value));
	}

}

