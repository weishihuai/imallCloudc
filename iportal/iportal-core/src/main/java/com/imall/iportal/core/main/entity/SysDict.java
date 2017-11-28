
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
 * T_PT_SYS_DICT【数据字典】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_dict" )
public class SysDict extends BaseEntity<Long>{

	public static final String TABLE_ALIAS = "SysDict";
	public static final String DICT_TYPE = "dictType";
	public static final String DICT_NM = "dictNm";
	public static final String ORDERBY = "orderby";
	public static final String IS_AVAILABLE = "isAvailable";

    /** DICT_TYPE - 字典 类型 */
    @Column(name = "DICT_TYPE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String dictType;
    /** DICT_NM - 字典 名称 */
    @Column(name = "DICT_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private java.lang.String dictNm;
    /** ORDERBY - 顺序 */
    @Column(name = "ORDERBY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long orderby;
    /** AVAILABLE - 是否可用 */
    @Column(name = "IS_AVAILABLE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private java.lang.String isAvailable;
	/** IS_INTERNAL - 是否内置字典（内置信息不可编辑） */
	@Column(name = "IS_INTERNAL", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	private java.lang.String isInternal;

    public void setDictType(java.lang.String value) {
		this.dictType = value;
	}

    public java.lang.String getDictType() {
		return this.dictType;
	}

    public void setDictNm(java.lang.String value) {
		this.dictNm = value;
	}

    public java.lang.String getDictNm() {
		return this.dictNm;
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

	public String getIsInternal() {
		return isInternal;
	}

	public void setIsInternal(String isInternal) {
		this.isInternal = isInternal;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("DICT_TYPE",getDictType())
			.append("DICT_NM",getDictNm())
			.append("ORDERBY",getOrderby())
			.append("AVAILABLE",getIsAvailable())
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
		if(!(obj instanceof SysDict)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysDict other = (SysDict)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

