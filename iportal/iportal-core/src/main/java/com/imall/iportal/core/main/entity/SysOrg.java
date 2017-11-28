
package com.imall.iportal.core.main.entity;

import com.imall.commons.base.entity.TreeBaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * T_PT_SYS_ORG【组织】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_org" )
public class SysOrg extends TreeBaseEntity<Long> {

	public static final String TABLE_ALIAS = "SysOrg";
	public static final String ORG_CODE = "orgCode";
	public static final String ORG_NAME = "orgName";
	public static final String ADDRESS = "address";
	public static final String PHONE = "phone";
	public static final String FAX = "fax";
	public static final String IS_INTERNAL = "isInternal";
	public static final String ORDERBY = "orderby";
	public static final String IS_AVAILABLE = "isAvailable";


    /** ORG_CODE - 组织编码,3位 */
    @Column(name = "ORG_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String orgCode;
    /** ORG_NAME - 名称 */
    @Column(name = "ORG_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private java.lang.String orgName;
	/** ZONE_CODE - 编码 */
	@Column(name = "ZONE_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	private String zoneCode;
    /** ADDRESS - 组织地址 */
    @Column(name = "ADDRESS", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private java.lang.String address;
    /** PHONE - 电话 */
    @Column(name = "PHONE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String phone;
    /** FAX - 传真 */
    @Column(name = "FAX", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String fax;
    /** IS_INTERNAL - 是否内置组织（内置信息不可编辑），用于IT控制组织是否可被业务人员修改 */
    @Column(name = "IS_INTERNAL", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private java.lang.String isInternal;
    /** ORDERBY - 顺序 */
    @Column(name = "ORDERBY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long orderby;
    /** AVAILABLE - 是否可用 */
	@Column(name = "IS_AVAILABLE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	private java.lang.String isAvailable;

    public void setOrgCode(java.lang.String value) {
		this.orgCode = value;
	}

    public java.lang.String getOrgCode() {
		return this.orgCode;
	}

    public void setOrgName(java.lang.String value) {
		this.orgName = value;
	}

    public java.lang.String getOrgName() {
		return this.orgName;
	}

    public void setAddress(java.lang.String value) {
		this.address = value;
	}

    public java.lang.String getAddress() {
		return this.address;
	}

    public void setPhone(java.lang.String value) {
		this.phone = value;
	}

    public java.lang.String getPhone() {
		return this.phone;
	}

    public void setFax(java.lang.String value) {
		this.fax = value;
	}

    public java.lang.String getFax() {
		return this.fax;
	}

    public void setIsInternal(java.lang.String value) {
		this.isInternal = value;
	}

    public java.lang.String getIsInternal() {
		return this.isInternal;
	}

    public void setOrderby(java.lang.Long value) {
		this.orderby = value;
	}

    public java.lang.Long getOrderby() {
		return this.orderby;
	}

	public String getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("ORG_CODE",getOrgCode())
			.append("ORG_NAME",getOrgName())
			.append("ADDRESS",getAddress())
			.append("PHONE",getPhone())
			.append("FAX",getFax())
			.append("IS_INTERNAL",getIsInternal())
			.append("PARENT_ID",getParentId())
			.append("ORDERBY",getOrderby())
			.append("IS_AVAILABLE",getIsAvailable())
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
		if(!(obj instanceof SysOrg)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysOrg other = (SysOrg)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

