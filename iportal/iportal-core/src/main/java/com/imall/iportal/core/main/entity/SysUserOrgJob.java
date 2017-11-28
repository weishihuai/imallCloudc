
package com.imall.iportal.core.main.entity;

import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * T_PT_SYS_USER_ORG_JOB【组织岗位】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_user_org_job" )
public class SysUserOrgJob extends BaseEntity<Long>{

	public static final String TABLE_ALIAS = "SysUserOrgJob";
	public static final String USER_ID = "userId";
	public static final String ORG_ID = "orgId";
	public static final String JOB_ID = "jobId";
	public static final String ISMAIN = "ismain";

    /** USER_ID - 用户ID */
    @Column(name = "USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.Long userId;
    /** ORG_ID - 组织ID */
    @Column(name = "ORG_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.Long orgId;
    /** JOB_ID - 岗位ID */
    @Column(name = "JOB_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.Long jobId;
    /** ISMAIN - 区分兼职时主次职位 */
    @Column(name = "ISMAIN", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private java.lang.String ismain;

    public void setUserId(java.lang.Long value) {
		this.userId = value;
	}

    public java.lang.Long getUserId() {
		return this.userId;
	}

    public void setOrgId(java.lang.Long value) {
		this.orgId = value;
	}

    public java.lang.Long getOrgId() {
		return this.orgId;
	}

    public void setJobId(java.lang.Long value) {
		this.jobId = value;
	}

    public java.lang.Long getJobId() {
		return this.jobId;
	}

    public void setIsmain(java.lang.String value) {
		this.ismain = value;
	}

    public java.lang.String getIsmain() {
		return this.ismain;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("USER_ID",getUserId())
			.append("ORG_ID",getOrgId())
			.append("JOB_ID",getJobId())
			.append("ISMAIN",getIsmain())
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
		if(!(obj instanceof SysUserOrgJob)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysUserOrgJob other = (SysUserOrgJob)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

