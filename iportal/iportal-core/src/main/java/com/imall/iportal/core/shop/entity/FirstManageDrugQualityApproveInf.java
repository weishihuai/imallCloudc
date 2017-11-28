
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
@Table(name = "t_shp_first_manage_drug_quality_approve_inf" )
public class FirstManageDrugQualityApproveInf extends BaseEntity<Long>{

	public static final String FIRST_MANAGE_DRUG_QUALITY_APPROVE_ID = "firstManageDrugQualityApproveId";
	public static final String SHOP_ID = "shopId";
	public static final String GOODS_ID = "goodsId";
	public static final String APPROVE_TYPE_CODE = "approveTypeCode";
	public static final String APPROVE_MAN_ID = "approveManId";
	public static final String APPROVE_STATE_CODE = "approveStateCode";
	public static final String APPROVE_REMARK = "approveRemark";
	public static final String APPROVE_TIME = "approveTime";

	/** FIRST_MANAGE_DRUG_QUALITY_APPROVE_ID - 首营 药品 质量 审核 ID */
	@Column(name = "FIRST_MANAGE_DRUG_QUALITY_APPROVE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long firstManageDrugQualityApproveId;
	/** SHOP_ID - 门店 ID */
	@Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long shopId;
	/** GOODS_ID - 商品 ID */
	@Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long goodsId;
	/** APPROVE_TYPE_CODE - 审核 类型 代码 */
	@Column(name = "APPROVE_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String approveTypeCode;
	/** APPROVE_MAN_ID - 审核 人 ID */
	@Column(name = "APPROVE_MAN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long approveManId;
	/** APPROVE_STATE_CODE - 审核 状态 代码 */
	@Column(name = "APPROVE_STATE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String approveStateCode;
	/** APPROVE_REMARK - 审核 备注 */
	@Column(name = "APPROVE_REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String approveRemark;
	/** APPROVE_TIME - 审核 时间 */
	@Column(name = "APPROVE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	private java.util.Date approveTime;

	public void setFirstManageDrugQualityApproveId(java.lang.Long value) {
		this.firstManageDrugQualityApproveId = value;
	}

	public java.lang.Long getFirstManageDrugQualityApproveId() {
		return this.firstManageDrugQualityApproveId;
	}

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

	public void setApproveTypeCode(java.lang.String value) {
		this.approveTypeCode = value;
	}

	public java.lang.String getApproveTypeCode() {
		return this.approveTypeCode;
	}

	public void setApproveManId(java.lang.Long value) {
		this.approveManId = value;
	}

	public java.lang.Long getApproveManId() {
		return this.approveManId;
	}

	public void setApproveStateCode(java.lang.String value) {
		this.approveStateCode = value;
	}

	public java.lang.String getApproveStateCode() {
		return this.approveStateCode;
	}

	public void setApproveRemark(java.lang.String value) {
		this.approveRemark = value;
	}

	public java.lang.String getApproveRemark() {
		return this.approveRemark;
	}

	public void setApproveTime(java.util.Date value) {
		this.approveTime = value;
	}

	public java.util.Date getApproveTime() {
		return this.approveTime;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("ID",getId())
				.append("FIRST_MANAGE_DRUG_QUALITY_APPROVE_ID",getFirstManageDrugQualityApproveId())
				.append("SHOP_ID",getShopId())
				.append("GOODS_ID",getGoodsId())
				.append("APPROVE_TYPE_CODE",getApproveTypeCode())
				.append("APPROVE_MAN_ID",getApproveManId())
				.append("APPROVE_STATE_CODE",getApproveStateCode())
				.append("APPROVE_REMARK",getApproveRemark())
				.append("APPROVE_TIME",getApproveTime())
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
		if(!(obj instanceof FirstManageDrugQualityApproveInf)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		FirstManageDrugQualityApproveInf other = (FirstManageDrugQualityApproveInf)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}

	public String getApproveTimeStr() {
		return DateTimeUtils.convertDateToString(getApproveTime());
	}
	public void setApproveTimeStr(String value) throws ParseException {
		setApproveTime(StringUtils.isBlank(value)?null:DateTimeUtils.convertStringToDate(value));
	}
}

