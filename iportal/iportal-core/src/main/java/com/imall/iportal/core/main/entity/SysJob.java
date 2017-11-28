
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
 * T_PT_SYS_JOB【岗位/职务】实体类
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_job")
public class SysJob extends BaseEntity<Long> {

    public static final String TABLE_ALIAS = "SysJob";
    public static final String JOB_CODE = "jobCode";
    public static final String JOB_NAME = "jobName";
    public static final String DESCRIPTION = "description";
    public static final String ORDERBY = "orderby";
    public static final String IS_AVAILABLE = "isAvailable";
    public static final String ORG_ID = "orgId";
    public static final String IS_DEFAULT_ADMIN = "isDefaultAdmin";

    /**
     * JOB_CODE - 岗位代码
     */
    @Column(name = "JOB_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String jobCode;
    /**
     * JOB_NAME - 岗位名称
     */
    @Column(name = "JOB_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private java.lang.String jobName;
    /**
     * DESCRIPTION - 描述
     */
    @Column(name = "DESCRIPTION", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private java.lang.String description;
    /**
     * ORDERBY - 顺序
     */
    @Column(name = "ORDERBY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long orderby;
    /**
     * AVAILABLE - 是否可用
     */
    @Column(name = "IS_AVAILABLE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private java.lang.String isAvailable;
    /**
     * ORG_ID - 行政所属组织
     */
    @Column(name = "ORG_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    private java.lang.Long orgId;

    /** IS_DEFAULT_ADMIN - 是否 默认 管理员 */
    @Column(name = "IS_DEFAULT_ADMIN", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isDefaultAdmin;

    public void setJobCode(java.lang.String value) {
        this.jobCode = value;
    }

    public java.lang.String getJobCode() {
        return this.jobCode;
    }

    public void setJobName(java.lang.String value) {
        this.jobName = value;
    }

    public java.lang.String getJobName() {
        return this.jobName;
    }

    public void setDescription(java.lang.String value) {
        this.description = value;
    }

    public java.lang.String getDescription() {
        return this.description;
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public void setIsDefaultAdmin(String value) {
        this.isDefaultAdmin = value;
    }

    public String getIsDefaultAdmin() {
        return this.isDefaultAdmin;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("ID", getId())
                .append("JOB_CODE", getJobCode())
                .append("JOB_NAME", getJobName())
                .append("DESCRIPTION", getDescription())
                .append("ORDERBY", getOrderby())
                .append("IsAvailable", getIsAvailable())
                .append("CREATE_DATE", getCreateDate())
                .append("CREATE_BY", getCreateBy())
                .append("LAST_MODIFIED_DATE", getLastModifiedDate())
                .append("LAST_MODIFIED_BY", getLastModifiedBy())
                .append("VERSION", getVersion())
                .append("IS_DEFAULT_ADMIN",getIsDefaultAdmin())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (this.getId() == null) {
            return false;
        }
        if (!(obj instanceof SysJob)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        SysJob other = (SysJob) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

