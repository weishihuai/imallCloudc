
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
@Table(name = "t_shp_use_record" )
public class UseRecord extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String FACILITY_AND_DEVICE_ACCOUNTS_ID = "facilityAndDeviceAccountsId";
	public static final String PURPOSES = "purposes";
	public static final String USE_DATE = "useDate";
	public static final String STOP_TIME = "stopTime";
	public static final String RECORD_DATE = "recordDate";
	public static final String SERVICE_CONDITION = "serviceCondition";
	public static final String OPERATION_MAN = "operationMan";
	public static final String REMARK = "remark";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** FACILITY_AND_DEVICE_ACCOUNTS_ID - 设施 设备 台账 ID */
    @Column(name = "FACILITY_AND_DEVICE_ACCOUNTS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long facilityAndDeviceAccountsId;
    /** PURPOSES - 使用 目的 */
    @Column(name = "PURPOSES", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String purposes;
    /** USE_DATE - 使用 日期 */
    @Column(name = "USE_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date useDate;
    /** STOP_TIME - 停止 时间 */
    @Column(name = "STOP_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date stopTime;
    /** RECORD_DATE - 记录 日期 */
    @Column(name = "RECORD_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date recordDate;
    /** SERVICE_CONDITION - 使用 情况 */
    @Column(name = "SERVICE_CONDITION", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String serviceCondition;
    /** OPERATION_MAN - 操作 人 */
    @Column(name = "OPERATION_MAN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String operationMan;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String remark;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setFacilityAndDeviceAccountsId(Long value) {
		this.facilityAndDeviceAccountsId = value;
	}

    public Long getFacilityAndDeviceAccountsId() {
		return this.facilityAndDeviceAccountsId;
	}

    public void setPurposes(String value) {
		this.purposes = value;
	}

    public String getPurposes() {
		return this.purposes;
	}

    public void setUseDate(java.util.Date value) {
		this.useDate = value;
	}

    public java.util.Date getUseDate() {
		return this.useDate;
	}

    public void setStopTime(java.util.Date value) {
		this.stopTime = value;
	}

    public java.util.Date getStopTime() {
		return this.stopTime;
	}

    public void setRecordDate(java.util.Date value) {
		this.recordDate = value;
	}

    public java.util.Date getRecordDate() {
		return this.recordDate;
	}

    public void setServiceCondition(String value) {
		this.serviceCondition = value;
	}

    public String getServiceCondition() {
		return this.serviceCondition;
	}

    public void setOperationMan(String value) {
		this.operationMan = value;
	}

    public String getOperationMan() {
		return this.operationMan;
	}

    public void setRemark(String value) {
		this.remark = value;
	}

    public String getRemark() {
		return this.remark;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("FACILITY_AND_DEVICE_ACCOUNTS_ID",getFacilityAndDeviceAccountsId())
			.append("PURPOSES",getPurposes())
			.append("USE_DATE",getUseDate())
			.append("STOP_TIME",getStopTime())
			.append("RECORD_DATE",getRecordDate())
			.append("SERVICE_CONDITION",getServiceCondition())
			.append("OPERATION_MAN",getOperationMan())
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
		if(!(obj instanceof UseRecord)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		UseRecord other = (UseRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

