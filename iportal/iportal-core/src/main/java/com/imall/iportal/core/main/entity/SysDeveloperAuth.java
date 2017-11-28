
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
 * T_PT_SYS_DEVELOPER_AUTH【开发者授权】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_developer_auth" )
public class SysDeveloperAuth extends BaseEntity<Long>{

	public static final String TABLE_ALIAS = "SysDeveloperAuth";
	public static final String USER_ID = "userId";
	public static final String APP_ID = "appId";
	public static final String APP_NAME = "appName";
	public static final String HOSTNAME = "hostname";
	public static final String IS_AVAILABLE = "isAvailable";

	/** USER_ID - 用户ID */
	@Column(name = "USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.Long userId;
	/** APP_ID - 应用ID */
	@Column(name = "APP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.Long appId;
	/** APP_NAME - 应用名称 */
	@Column(name = "APP_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	private String appName;
	/** HOSTNAME - HOSTNAME */
	@Column(name = "HOSTNAME", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	private String hostname;
	/** IS_AVAILABLE - 是否可用 */
	@Column(name = "IS_AVAILABLE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	private String isAvailable;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public void setAppName(String value) {
		this.appName = value;
	}

	public String getAppName() {
		return this.appName;
	}

	public void setHostname(String value) {
		this.hostname = value;
	}

	public String getHostname() {
		return this.hostname;
	}

	public void setIsAvailable(String value) {
		this.isAvailable = value;
	}

	public String getIsAvailable() {
		return this.isAvailable;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("ID",getId())
				.append("USER_ID",getUserId())
				.append("APP_ID",getAppId())
				.append("APP_NAME",getAppName())
				.append("HOSTNAME",getHostname())
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
		if(!(obj instanceof SysDeveloperAuth)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysDeveloperAuth other = (SysDeveloperAuth)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}
}

