
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
 * T_PT_SYS_RESOUCE_URL【资源URL】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_resource_url" )
public class SysResourceUrl extends BaseEntity<Long>{

	public static final String TABLE_ALIAS = "SysResourceUrl";
	public static final String URL = "url";
	public static final String RESOURCE_ID = "resourceId";


	/** URL - 角色ID */
    @Column(name = "URL", unique = false, nullable = false, insertable = true, updatable = true, length = 256)
    private java.lang.String url;
    /** RESOURCE_ID - 菜单ID */
    @Column(name = "RESOURCE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.Long resourceId;


	public void setUrl(java.lang.String value) {
		this.url = value;
	}

    public java.lang.String getUrl() {
		return this.url;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("URL",getUrl())
			.append("RESOURCE_ID",getResourceId())
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
		if(!(obj instanceof SysResourceUrl)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysResourceUrl other = (SysResourceUrl)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

