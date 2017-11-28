
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
@Table(name = "t_shp_first_manage_supplier_quality_approve" )
public class FirstManageSupplierQualityApprove extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String SUPPLIER_ID = "supplierId";
	public static final String APPROVE_STATE_CODE = "approveStateCode";
	public static final String QUALITY_APPROVE_TIME = "qualityApproveTime";
	public static final String APPLY_MAN_NAME = "applyManName";
	public static final String SUBMIT_ADVICE = "submitAdvice";
	public static final String ENT_REMARK = "entRemark";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long shopId;
    /** SUPPLIER_ID - 供应商 ID */
    @Column(name = "SUPPLIER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long supplierId;
    /** APPROVE_STATE_CODE - 审核 状态 代码 */
    @Column(name = "APPROVE_STATE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String approveStateCode;
    /** QUALITY_APPROVE_TIME - 质量 审核 时间 */
    @Column(name = "QUALITY_APPROVE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date qualityApproveTime;
    /** APPLY_MAN_NAME - 申请 人 姓名 */
    @Column(name = "APPLY_MAN_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String applyManName;
    /** SUBMIT_ADVICE - 提交 意见 */
    @Column(name = "SUBMIT_ADVICE", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private java.lang.String submitAdvice;
    /** ENT_REMARK - 企业 备注 */
    @Column(name = "ENT_REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private java.lang.String entRemark;

    public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

    public java.lang.Long getShopId() {
		return this.shopId;
	}

    public void setSupplierId(java.lang.Long value) {
		this.supplierId = value;
	}

    public java.lang.Long getSupplierId() {
		return this.supplierId;
	}

    public void setApproveStateCode(java.lang.String value) {
		this.approveStateCode = value;
	}

    public java.lang.String getApproveStateCode() {
		return this.approveStateCode;
	}

    public void setQualityApproveTime(String value) throws ParseException {
		this.qualityApproveTime = StringUtils.isNotBlank(value)? DateTimeUtils.convertStringToDate(value):null;
	}

    public String getQualityApproveTime() {
		return this.qualityApproveTime!=null?DateTimeUtils.convertDateToString(this.qualityApproveTime):null;
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

    public void setEntRemark(java.lang.String value) {
		this.entRemark = value;
	}

    public java.lang.String getEntRemark() {
		return this.entRemark;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("SUPPLIER_ID",getSupplierId())
			.append("APPROVE_STATE_CODE",getApproveStateCode())
			.append("QUALITY_APPROVE_TIME",getQualityApproveTime())
			.append("APPLY_MAN_NAME",getApplyManName())
			.append("SUBMIT_ADVICE",getSubmitAdvice())
			.append("ENT_REMARK",getEntRemark())
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
		if(!(obj instanceof FirstManageSupplierQualityApprove)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		FirstManageSupplierQualityApprove other = (FirstManageSupplierQualityApprove)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

