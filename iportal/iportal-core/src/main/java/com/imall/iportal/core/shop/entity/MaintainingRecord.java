
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
@Table(name = "t_shp_maintaining_record" )
public class MaintainingRecord extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String FACILITY_AND_DEVICE_ACCOUNTS_ID = "facilityAndDeviceAccountsId";
	public static final String MAINTAIN_DATE = "maintainDate";
	public static final String MAINTAIN_CONT = "maintainCont";
	public static final String MAINTAIN_RESULT = "maintainResult";
	public static final String WORK_STATE = "workState";
	public static final String MAINTAIN_RESPONSE_MAN = "maintainResponseMan";
	public static final String APPROVER_NM = "approverNm";
	public static final String REMARK = "remark";
	public static final String APPROVE_MAN_ID = "approveManId";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** FACILITY_AND_DEVICE_ACCOUNTS_ID - 设施 设备 台账 ID */
    @Column(name = "FACILITY_AND_DEVICE_ACCOUNTS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long facilityAndDeviceAccountsId;
    /** MAINTAIN_DATE - 维护 日期 */
    @Column(name = "MAINTAIN_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date maintainDate;
    /** MAINTAIN_CONT - 维护 内容 */
    @Column(name = "MAINTAIN_CONT", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String maintainCont;
    /** MAINTAIN_RESULT - 维护 结果 */
    @Column(name = "MAINTAIN_RESULT", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String maintainResult;
    /** WORK_STATE - 工作 状况 */
    @Column(name = "WORK_STATE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String workState;
    /** MAINTAIN_RESPONSE_MAN - 检修 负责 人 */
    @Column(name = "MAINTAIN_RESPONSE_MAN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String maintainResponseMan;
    /** APPROVER_NM - 审批 人 名称 */
    @Column(name = "APPROVER_NM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String approverNm;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String remark;
    /** APPROVE_MAN_ID - 审核 人 ID */
    @Column(name = "APPROVE_MAN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long approveManId;

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

    public void setMaintainDate(java.util.Date value) {
		this.maintainDate = value;
	}

    public java.util.Date getMaintainDate() {
		return this.maintainDate;
	}

    public void setMaintainCont(String value) {
		this.maintainCont = value;
	}

    public String getMaintainCont() {
		return this.maintainCont;
	}

    public void setMaintainResult(String value) {
		this.maintainResult = value;
	}

    public String getMaintainResult() {
		return this.maintainResult;
	}

    public void setWorkState(String value) {
		this.workState = value;
	}

    public String getWorkState() {
		return this.workState;
	}

    public void setMaintainResponseMan(String value) {
		this.maintainResponseMan = value;
	}

    public String getMaintainResponseMan() {
		return this.maintainResponseMan;
	}

    public void setApproverNm(String value) {
		this.approverNm = value;
	}

    public String getApproverNm() {
		return this.approverNm;
	}

    public void setRemark(String value) {
		this.remark = value;
	}

    public String getRemark() {
		return this.remark;
	}

    public void setApproveManId(Long value) {
		this.approveManId = value;
	}

    public Long getApproveManId() {
		return this.approveManId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("FACILITY_AND_DEVICE_ACCOUNTS_ID",getFacilityAndDeviceAccountsId())
			.append("MAINTAIN_DATE",getMaintainDate())
			.append("MAINTAIN_CONT",getMaintainCont())
			.append("MAINTAIN_RESULT",getMaintainResult())
			.append("WORK_STATE",getWorkState())
			.append("MAINTAIN_RESPONSE_MAN",getMaintainResponseMan())
			.append("APPROVER_NM",getApproverNm())
			.append("REMARK",getRemark())
			.append("APPROVE_MAN_ID",getApproveManId())
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
		if(!(obj instanceof MaintainingRecord)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		MaintainingRecord other = (MaintainingRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

