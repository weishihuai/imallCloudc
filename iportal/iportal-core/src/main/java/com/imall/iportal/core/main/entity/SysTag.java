
package com.imall.iportal.core.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * 标签实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_tag" )
public class SysTag extends BaseEntity<Long>{

	public static final String TAG_TYPE_CODE = "tagTypeCode";
	public static final String TAG_VALUE = "tagValue";
	public static final String DISPLAY_POSITION = "displayPosition";
	public static final String SYS_ORG_ID = "sysOrgId";

    /** TAG_TYPE_CODE - 标签类型代码 */
    @Column(name = "TAG_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String tagTypeCode;
    /** TAG_VALUE - 标签值 */
    @Column(name = "TAG_VALUE", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String tagValue;
    /** DISPLAY_POSITION - 排序 */
    @Column(name = "DISPLAY_POSITION", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long displayPosition;

	/** SYS_ORG_ID - sysOrgId */
	@Column(name = "SYS_ORG_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long sysOrgId;

	public Long getSysOrgId() {
		return sysOrgId;
	}

	public void setSysOrgId(Long sysOrgId) {
		this.sysOrgId = sysOrgId;
	}

	public void setTagTypeCode(String value) {
		this.tagTypeCode = value;
	}

    public String getTagTypeCode() {
		return this.tagTypeCode;
	}

    public void setTagValue(String value) {
		this.tagValue = value;
	}

    public String getTagValue() {
		return this.tagValue;
	}

    public void setDisplayPosition(Long value) {
		this.displayPosition = value;
	}

    public Long getDisplayPosition() {
		return this.displayPosition;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("TAG_TYPE_CODE",getTagTypeCode())
			.append("TAG_VALUE",getTagValue())
			.append("DISPLAY_POSITION",getDisplayPosition())
			.append("CREATE_BY",getCreateBy())
			.append("CREATE_DATE",getCreateDate())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
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
		if(!(obj instanceof SysTag)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysTag other = (SysTag)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

