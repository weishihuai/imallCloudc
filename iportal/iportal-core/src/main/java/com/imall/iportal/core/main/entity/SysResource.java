
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
 * T_PT_SYS_RESOURCE【资源】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_resource" )
public class SysResource extends TreeBaseEntity<Long> {

	public static final String TABLE_ALIAS = "SysResource";
	public static final String RESOURCE_NAME = "resourceName";
	public static final String PERMISSION_CODE = "permissionCode";
	public static final String ROUTER_KEY = "routerKey";
	public static final String APP_ID = "appId";
	public static final String ORDERBY = "orderby";
	public static final String IS_AVAILABLE = "isAvailable";
	public static final String RESOURCE_TYPE = "resourceType";
	public static final String ROUTER_TEMPLATE_URL = "routerTemplateUrl";
	public static final String ROUTER_TEMPLATE_JS = "routerTemplateJs";


    /** RESOURCE_NAME - 资源名称 */
    @Column(name = "RESOURCE_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
    private java.lang.String resourceName;
    /** PERMISSION_CODE - 唯一，资源标识符 用于权限匹配的 如sys:resource */
    @Column(name = "PERMISSION_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
    private java.lang.String permissionCode;
	/** RESOURCE_TYPE - 资源类型 */
	@Column(name = "RESOURCE_TYPE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String resourceType;
    /** APP_ID - 应用ID */
    @Column(name = "APP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.Long appId;
    /** ORDERBY - 顺序 */
    @Column(name = "ORDERBY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long orderby;
    /** IS_AVAILABLE - 是否可用 */
    @Column(name = "IS_AVAILABLE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private java.lang.String isAvailable;


	/** ROUTER_KEY - 路由key */
	@Column(name = "ROUTER_KEY", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	private java.lang.String routerKey;
	/** ROUTER_TEMPLATE_URL - 路由模版URL */
	@Column(name = "ROUTER_TEMPLATE_URL", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	private java.lang.String routerTemplateUrl;
	/** ROUTER_TEMPLATE_JS - 路由key */
	@Column(name = "ROUTER_TEMPLATE_JS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	private java.lang.String routerTemplateJs;


	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public void setResourceName(java.lang.String value) {
		this.resourceName = value;
	}

    public java.lang.String getResourceName() {
		return this.resourceName;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public String getRouterKey() {
		return routerKey;
	}

	public void setRouterKey(String routerKey) {
		this.routerKey = routerKey;
	}

	public String getRouterTemplateUrl() {
		return routerTemplateUrl;
	}

	public void setRouterTemplateUrl(String routerTemplateUrl) {
		this.routerTemplateUrl = routerTemplateUrl;
	}

	public String getRouterTemplateJs() {
		return routerTemplateJs;
	}

	public void setRouterTemplateJs(String routerTemplateJs) {
		this.routerTemplateJs = routerTemplateJs;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
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

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("RESOURCE_NAME",getResourceName())
			.append("PERMISSION_CODE",getPermissionCode())
			.append("ROUTER_KEY",getRouterKey())
			.append("ROUTER_TEMPLATE_URL",getRouterTemplateUrl())
			.append("ROUTER_TEMPLATE_JS",getRouterTemplateJs())
			.append("PARENT_ID",getParentId())
			.append("APP_ID",getAppId())
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
		if(!(obj instanceof SysResource)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysResource other = (SysResource)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

