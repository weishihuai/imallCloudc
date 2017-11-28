
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
 * T_PT_SYS_FILE_CATEGORY 【文件分类】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_file_category" )
public class SysFileCategory extends TreeBaseEntity<Long> {

	public static final String CATEGORY_NAME = "categoryName";
	public static final String ORG_ID = "orgId";

    /** CATEGORY_NAME - 分类名称 */
    @Column(name = "CATEGORY_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String categoryName;
	/** ORG_ID - orgId */
	@Column(name = "ORG_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private Long orgId;

    public void setCategoryName(String value) {
		this.categoryName = value;
	}

    public String getCategoryName() {
		return this.categoryName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("CATEGORY_NAME",getCategoryName())
			.append("ORG_ID",getOrgId())
			.append("PARENT_ID",getParentId())
			.append("NODE_CODE",getNodeCode())
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
		if(!(obj instanceof SysFileCategory)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysFileCategory other = (SysFileCategory)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

