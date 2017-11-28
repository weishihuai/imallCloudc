
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
 * T_PT_SYS_ROLE_MENU【角色菜单】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_role_menu" )
public class SysRoleMenu extends BaseEntity<Long>{

	public static final String TABLE_ALIAS = "SysRoleMenu";
	public static final String ROLE_ID = "roleId";
	public static final String MENU_ID = "menuId";

    /** ROLE_ID - 角色ID */
    @Column(name = "ROLE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long roleId;
    /** MENU_ID - 菜单ID */
    @Column(name = "MENU_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long menuId;

    public void setRoleId(java.lang.Long value) {
		this.roleId = value;
	}

    public java.lang.Long getRoleId() {
		return this.roleId;
	}

    public void setMenuId(java.lang.Long value) {
		this.menuId = value;
	}

    public java.lang.Long getMenuId() {
		return this.menuId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("ROLE_ID",getRoleId())
			.append("MENU_ID",getMenuId())
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
		if(!(obj instanceof SysRoleMenu)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysRoleMenu other = (SysRoleMenu)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

