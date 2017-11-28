
package com.imall.iportal.core.shop.entity;

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
@Table(name = "t_shp_bad_reaction_drug_inf" )
public class BadReactionDrugInf extends BaseEntity<Long>{

	public static final String BAD_REACTION_REP_ID = "badReactionRepId";
	public static final String DRUG_INF_TYPE_CODE = "drugInfTypeCode";
	public static final String APPROVAL_NUMBER = "approvalNumber";
	public static final String GOODS_NM = "goodsNm";
	public static final String COMMON_NM = "commonNm";
	public static final String PRODUCE_MANUFACTURER = "produceManufacturer";
	public static final String BATCH = "batch";
	public static final String USAGE_AND_DOSAGE = "usageAndDosage";
	public static final String DRUG_USE_TIME = "drugUseTime";
	public static final String DRUG_USE_REASON = "drugUseReason";

    /** BAD_REACTION_REP_ID - 不良 反应 报告 ID */
    @Column(name = "BAD_REACTION_REP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long badReactionRepId;
    /** DRUG_INF_TYPE_CODE - 药品 信息 类型 代码 */
    @Column(name = "DRUG_INF_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String drugInfTypeCode;
    /** APPROVAL_NUMBER - 批准文号 */
    @Column(name = "APPROVAL_NUMBER", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String approvalNumber;
    /** GOODS_NM - 商品 名称 */
    @Column(name = "GOODS_NM", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String goodsNm;
    /** COMMON_NM - 通用 名称 */
    @Column(name = "COMMON_NM", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String commonNm;
    /** PRODUCE_MANUFACTURER - 生产厂家 */
    @Column(name = "PRODUCE_MANUFACTURER", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String produceManufacturer;
    /** BATCH - 生产 批号 */
    @Column(name = "BATCH", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String batch;
    /** USAGE_AND_DOSAGE - 用法用量 */
    @Column(name = "USAGE_AND_DOSAGE", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String usageAndDosage;
    /** DRUG_USE_TIME - 用药 起止 时间 */
    @Column(name = "DRUG_USE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String drugUseTime;
    /** DRUG_USE_REASON - 用药 原因 */
    @Column(name = "DRUG_USE_REASON", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String drugUseReason;

    public void setBadReactionRepId(Long value) {
		this.badReactionRepId = value;
	}

    public Long getBadReactionRepId() {
		return this.badReactionRepId;
	}

    public void setDrugInfTypeCode(String value) {
		this.drugInfTypeCode = value;
	}

    public String getDrugInfTypeCode() {
		return this.drugInfTypeCode;
	}

    public void setApprovalNumber(String value) {
		this.approvalNumber = value;
	}

    public String getApprovalNumber() {
		return this.approvalNumber;
	}

    public void setGoodsNm(String value) {
		this.goodsNm = value;
	}

    public String getGoodsNm() {
		return this.goodsNm;
	}

    public void setCommonNm(String value) {
		this.commonNm = value;
	}

    public String getCommonNm() {
		return this.commonNm;
	}

    public void setProduceManufacturer(String value) {
		this.produceManufacturer = value;
	}

    public String getProduceManufacturer() {
		return this.produceManufacturer;
	}

    public void setBatch(String value) {
		this.batch = value;
	}

    public String getBatch() {
		return this.batch;
	}

    public void setUsageAndDosage(String value) {
		this.usageAndDosage = value;
	}

    public String getUsageAndDosage() {
		return this.usageAndDosage;
	}

    public void setDrugUseTime(String value) {
		this.drugUseTime = value;
	}

    public String getDrugUseTime() {
		return this.drugUseTime;
	}

    public void setDrugUseReason(String value) {
		this.drugUseReason = value;
	}

    public String getDrugUseReason() {
		return this.drugUseReason;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("BAD_REACTION_REP_ID",getBadReactionRepId())
			.append("DRUG_INF_TYPE_CODE",getDrugInfTypeCode())
			.append("APPROVAL_NUMBER",getApprovalNumber())
			.append("GOODS_NM",getGoodsNm())
			.append("COMMON_NM",getCommonNm())
			.append("PRODUCE_MANUFACTURER",getProduceManufacturer())
			.append("BATCH",getBatch())
			.append("USAGE_AND_DOSAGE",getUsageAndDosage())
			.append("DRUG_USE_TIME",getDrugUseTime())
			.append("DRUG_USE_REASON",getDrugUseReason())
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
		if(!(obj instanceof BadReactionDrugInf)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		BadReactionDrugInf other = (BadReactionDrugInf)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

