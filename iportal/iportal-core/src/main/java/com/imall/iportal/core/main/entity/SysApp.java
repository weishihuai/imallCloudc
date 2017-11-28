
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
 * T_PT_SYS_APP【应用】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_app" )
public class SysApp extends BaseEntity<Long>{

	public static final String TABLE_ALIAS = "SysApp";
	public static final String APP_NAME = "appName";
	public static final String APP_CNAME = "appCname";
	public static final String APP_KEY = "appKey";
	public static final String APP_SECRET = "appSecret";
	public static final String HOSTNAME = "hostname";
	public static final String WEB_CONTEXT = "webContext";
	public static final String ORDERBY = "orderby";
	public static final String IS_AVAILABLE = "isAvailable";

    /** APP_NAME - 应用名称 */
    @Column(name = "APP_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
    private java.lang.String appName;
    /** APP_CNAME - 应用中文名称 */
    @Column(name = "APP_CNAME", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
    private java.lang.String appCname;
    /** APP_KEY - 应用KEY */
    @Column(name = "APP_KEY", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
    private java.lang.String appKey;
    /** APP_SECRET - APP_SECRET */
    @Column(name = "APP_SECRET", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
    private java.lang.String appSecret;
    /** HOSTNAME - HOSTNAME */
    @Column(name = "HOSTNAME", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
    private java.lang.String hostname;
    /** WEB_CONTEXT - 应用上下文 */
    @Column(name = "WEB_CONTEXT", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
    private java.lang.String webContext;
    /** ORDERBY - 顺序 */
    @Column(name = "ORDERBY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long orderby;
    /** IS_AVAILABLE - 是否可用 */
    @Column(name = "IS_AVAILABLE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private java.lang.String isAvailable;

    public void setAppName(java.lang.String value) {
		this.appName = value;
	}

    public java.lang.String getAppName() {
		return this.appName;
	}

    public void setAppCname(java.lang.String value) {
		this.appCname = value;
	}

    public java.lang.String getAppCname() {
		return this.appCname;
	}

    public void setAppKey(java.lang.String value) {
		this.appKey = value;
	}

    public java.lang.String getAppKey() {
		return this.appKey;
	}

    public void setAppSecret(java.lang.String value) {
		this.appSecret = value;
	}

    public java.lang.String getAppSecret() {
		return this.appSecret;
	}

    public void setHostname(java.lang.String value) {
		this.hostname = value;
	}

    public java.lang.String getHostname() {
		return this.hostname;
	}

    public void setWebContext(java.lang.String value) {
		this.webContext = value;
	}

    public java.lang.String getWebContext() {
		return this.webContext;
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
			.append("APP_NAME",getAppName())
			.append("APP_CNAME",getAppCname())
			.append("APP_KEY",getAppKey())
			.append("APP_SECRET",getAppSecret())
			.append("HOSTNAME",getHostname())
			.append("WEB_CONTEXT",getWebContext())
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
		if(!(obj instanceof SysApp)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysApp other = (SysApp)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

