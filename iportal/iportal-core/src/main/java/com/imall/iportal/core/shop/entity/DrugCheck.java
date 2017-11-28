
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
@Table(name = "t_shp_drug_check" )
public class DrugCheck extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String CHECK_DOCUMENT_NUM = "checkDocumentNum";
	public static final String PLAN_CHECK_TIME = "planCheckTime";
	public static final String CHECK_TYPE_CODE = "checkTypeCode";
	public static final String APPROVE_MAN_ID = "approveManId";
	public static final String CHECK_STATE_CODE = "checkStateCode";
	public static final String CHECK_FINISH_TIME = "checkFinishTime";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** CHECK_DOCUMENT_NUM - 检查 单据 号码 */
    @Column(name = "CHECK_DOCUMENT_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String checkDocumentNum;
    /** PLAN_CHECK_TIME - 计划 检查 时间 */
    @Column(name = "PLAN_CHECK_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date planCheckTime;
    /** CHECK_TYPE_CODE - 检查 类型 代码 */
    @Column(name = "CHECK_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String checkTypeCode;
    /** APPROVE_MAN_ID - 审核 人 ID */
    @Column(name = "APPROVE_MAN_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long approveManId;
    /** CHECK_STATE_CODE - 检查 状态 代码 */
    @Column(name = "CHECK_STATE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String checkStateCode;
    /** CHECK_FINISH_TIME - 检查 完成 时间 */
    @Column(name = "CHECK_FINISH_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date checkFinishTime;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setCheckDocumentNum(String value) {
		this.checkDocumentNum = value;
	}

    public String getCheckDocumentNum() {
		return this.checkDocumentNum;
	}

    public void setPlanCheckTime(java.util.Date value) {
		this.planCheckTime = value;
	}

    public java.util.Date getPlanCheckTime() {
		return this.planCheckTime;
	}

    public void setCheckTypeCode(String value) {
		this.checkTypeCode = value;
	}

    public String getCheckTypeCode() {
		return this.checkTypeCode;
	}

    public void setApproveManId(Long value) {
		this.approveManId = value;
	}

    public Long getApproveManId() {
		return this.approveManId;
	}

    public void setCheckStateCode(String value) {
		this.checkStateCode = value;
	}

    public String getCheckStateCode() {
		return this.checkStateCode;
	}

    public void setCheckFinishTime(java.util.Date value) {
		this.checkFinishTime = value;
	}

    public java.util.Date getCheckFinishTime() {
		return this.checkFinishTime;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("CHECK_DOCUMENT_NUM",getCheckDocumentNum())
			.append("PLAN_CHECK_TIME",getPlanCheckTime())
			.append("CHECK_TYPE_CODE",getCheckTypeCode())
			.append("APPROVE_MAN_ID",getApproveManId())
			.append("CHECK_STATE_CODE",getCheckStateCode())
			.append("CHECK_FINISH_TIME",getCheckFinishTime())
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
		if(!(obj instanceof DrugCheck)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		DrugCheck other = (DrugCheck)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

