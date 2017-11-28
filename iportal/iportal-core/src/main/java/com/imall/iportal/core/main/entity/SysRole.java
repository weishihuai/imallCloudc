
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
 * T_PT_SYS_ROLE【角色】实体类
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_role")
public class SysRole extends BaseEntity<Long> {

    public static final String TABLE_ALIAS = "SysRole";
    public static final String ROLE_NAME = "roleName";
    public static final String ROLE_CODE = "roleCode";
    public static final String DESCRIPTION = "description";
    public static final String ORDERBY = "orderby";
    public static final String IS_AVAILABLE = "isAvailable";
    public static final String ORG_ID = "orgId";
    public static final String IS_DEFAULT_ADMIN = "isDefaultAdmin";

    /**
     * ROLE_NAME - 角色名称
     */
    @Column(name = "ROLE_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
    private java.lang.String roleName;
    /**
     * ROLE_CODE - 角色编码
     */
    @Column(name = "ROLE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String roleCode;
    /**
     * DESCRIPTION - 描述
     */
    @Column(name = "DESCRIPTION", unique = false, nullable = false, insertable = true, updatable = true, length = 500)
    private java.lang.String description;
    /**
     * ORDERBY - 顺序
     */
    @Column(name = "ORDERBY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long orderby;
    /**
     * IS_AVAILABLE - 是否可用
     */
    @Column(name = "IS_AVAILABLE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private java.lang.String isAvailable;
    /**
     * ORG_ID - 行政所属组织
     */
    @Column(name = "ORG_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
    private java.lang.Long orgId;
    /**
     * IS_DEFAULT_ADMIN - 是否 默认 管理员
     */
    @Column(name = "IS_DEFAULT_ADMIN", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isDefaultAdmin;

    public void setRoleName(java.lang.String value) {
        this.roleName = value;
    }

    public java.lang.String getRoleName() {
        return this.roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
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

    public void setIsAvailable(java.lang.String value) {
        this.isAvailable = value;
    }

    public java.lang.String getIsAvailable() {
        return this.isAvailable;
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
                .append("ROLE_NAME", getRoleName())
                .append("DESCRIPTION", getDescription())
                .append("ORDERBY", getOrderby())
                .append("IS_AVAILABLE", getIsAvailable())
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
        if (!(obj instanceof SysRole)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        SysRole other = (SysRole) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

