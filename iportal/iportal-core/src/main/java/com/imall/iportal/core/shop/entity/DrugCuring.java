
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
@Table(name = "t_shp_drug_curing" )
public class DrugCuring extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String CURING_DOCUMENT_NUM = "curingDocumentNum";
	public static final String PLAN_CURING_TIME = "planCuringTime";
	public static final String CURING_TYPE_CODE = "curingTypeCode";
	public static final String CURING_MAN_ID = "curingManId";
	public static final String CURING_STATE_CODE = "curingStateCode";
	public static final String CURING_FINISH_TIME = "curingFinishTime";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** CURING_DOCUMENT_NUM - 养护 单据 号码 */
    @Column(name = "CURING_DOCUMENT_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String curingDocumentNum;
    /** PLAN_CURING_TIME - 计划 养护 时间 */
    @Column(name = "PLAN_CURING_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date planCuringTime;
    /** CURING_TYPE_CODE - 养护 类型 代码 */
    @Column(name = "CURING_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String curingTypeCode;
    /** CURING_MAN_ID - 审核 人 ID */
    @Column(name = "CURING_MAN_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long curingManId;
    /** CURING_STATE_CODE - 养护 状态 代码 */
    @Column(name = "CURING_STATE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String curingStateCode;
    /** CURING_FINISH_TIME - 养护 完成 时间 */
    @Column(name = "CURING_FINISH_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date curingFinishTime;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setCuringDocumentNum(String value) {
		this.curingDocumentNum = value;
	}

    public String getCuringDocumentNum() {
		return this.curingDocumentNum;
	}

    public void setPlanCuringTime(java.util.Date value) {
		this.planCuringTime = value;
	}

    public java.util.Date getPlanCuringTime() {
		return this.planCuringTime;
	}

    public void setCuringTypeCode(String value) {
		this.curingTypeCode = value;
	}

    public String getCuringTypeCode() {
		return this.curingTypeCode;
	}

    public void setCuringManId(Long value) {
		this.curingManId = value;
	}

    public Long getCuringManId() {
		return this.curingManId;
	}

    public void setCuringStateCode(String value) {
		this.curingStateCode = value;
	}

    public String getCuringStateCode() {
		return this.curingStateCode;
	}

    public void setCuringFinishTime(java.util.Date value) {
		this.curingFinishTime = value;
	}

    public java.util.Date getCuringFinishTime() {
		return this.curingFinishTime;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("CURING_DOCUMENT_NUM",getCuringDocumentNum())
			.append("PLAN_CURING_TIME",getPlanCuringTime())
			.append("CURING_TYPE_CODE",getCuringTypeCode())
			.append("CURING_MAN_ID",getCuringManId())
			.append("CURING_STATE_CODE",getCuringStateCode())
			.append("CURING_FINISH_TIME",getCuringFinishTime())
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
		if(!(obj instanceof DrugCuring)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		DrugCuring other = (DrugCuring)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

