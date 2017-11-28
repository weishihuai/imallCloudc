
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
 * T_PT_SYS_MENU【菜单】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_menu" )
public class SysMenu extends TreeBaseEntity<Long> {

	public static final String TABLE_ALIAS = "SysMenu";
	public static final String MENU_NAME = "menuName";
	public static final String RESOURCE_ID = "resourceId";
	public static final String ICON = "icon";
	public static final String ORDERBY = "orderby";
	public static final String IS_AVAILABLE = "isAvailable";
	public static final String MENU_TYPE = "menuType";


    /** MENU_NAME - 名称 */
    @Column(name = "MENU_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
    private java.lang.String menuName;
    /** RESOURCE_ID - 资源ID */
    @Column(name = "RESOURCE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private java.lang.Long resourceId;
    /** ICON - 图标 */
    @Column(name = "ICON", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
    private java.lang.String icon;
    /** ORDERBY - 顺序 */
    @Column(name = "ORDERBY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long orderby;
    /** IS_AVAILABLE - 是否可用(0否1是) */
    @Column(name = "IS_AVAILABLE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private java.lang.String isAvailable;
    /** MENU_TYPE - 0虚拟菜单，1功能菜单 */
    @Column(name = "MENU_TYPE", unique = false, nullable = false, insertable = true, updatable = true, length = 4)
    private java.lang.String menuType;

    public void setMenuName(java.lang.String value) {
		this.menuName = value;
	}

    public java.lang.String getMenuName() {
		return this.menuName;
	}

    public void setResourceId(java.lang.Long value) {
		this.resourceId = value;
	}

    public java.lang.Long getResourceId() {
		return this.resourceId;
	}

    public void setIcon(java.lang.String value) {
		this.icon = value;
	}

    public java.lang.String getIcon() {
		return this.icon;
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

    public void setMenuType(java.lang.String value) {
		this.menuType = value;
	}

    public java.lang.String getMenuType() {
		return this.menuType;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("MENU_NAME",getMenuName())
			.append("PARENT_ID",getParentId())
			.append("RESOURCE_ID",getResourceId())
			.append("ICON",getIcon())
			.append("ORDERBY",getOrderby())
			.append("IS_AVAILABLE",getIsAvailable())
			.append("MENU_TYPE",getMenuType())
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
		if(!(obj instanceof SysMenu)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysMenu other = (SysMenu)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

