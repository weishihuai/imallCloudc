
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
 * T_PT_SYS_AUTH【授权】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_auth" )
public class SysAuth extends BaseEntity<Long>{

	public static final String TABLE_ALIAS = "SysAuth";
	public static final String JOB_ID = "jobId";
	public static final String ROLE_ID = "roleId";

    /** JOB_ID - 岗位ID */
    @Column(name = "JOB_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long jobId;
    /** ROLE_ID - 角色ID */
    @Column(name = "ROLE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long roleId;

    public void setJobId(java.lang.Long value) {
		this.jobId = value;
	}

    public java.lang.Long getJobId() {
		return this.jobId;
	}

    public void setRoleId(java.lang.Long value) {
		this.roleId = value;
	}

    public java.lang.Long getRoleId() {
		return this.roleId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("JOB_ID",getJobId())
			.append("ROLE_ID",getRoleId())
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
		if(!(obj instanceof SysAuth)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysAuth other = (SysAuth)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

