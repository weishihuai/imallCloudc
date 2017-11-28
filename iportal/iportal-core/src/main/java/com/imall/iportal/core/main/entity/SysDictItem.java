
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
 * T_PT_SYS_DICT_ITEM【数据字典项】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_dict_item" )
public class SysDictItem extends BaseEntity<Long>{

	public static final String TABLE_ALIAS = "SysDictItem";
	public static final String DICT_ITEM_CODE = "dictItemCode";
	public static final String DICT_ITEM_NM = "dictItemNm";
	public static final String DATA_DICT_ID = "dataDictId";
	public static final String IS_DEFAULT = "isDefault";
	public static final String ORDERBY = "orderby";
	public static final String IS_AVAILABLE = "isAvailable";

    /** DICT_ITEM_CODE - 字典 项 编码 */
    @Column(name = "DICT_ITEM_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String dictItemCode;
    /** DICT_ITEM_NM - 字典 项 名称 */
    @Column(name = "DICT_ITEM_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private java.lang.String dictItemNm;
    /** DATA_DICT_ID - 数据 字典 ID */
    @Column(name = "DATA_DICT_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long dataDictId;
    /** IS_DEFAULT - 是否 默认 */
    @Column(name = "IS_DEFAULT", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private java.lang.String isDefault;
    /** ORDERBY - 顺序 */
    @Column(name = "ORDERBY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long orderby;
    /** AVAILABLE - 是否可用 */
    @Column(name = "IS_AVAILABLE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private java.lang.String isAvailable;

    public void setDictItemCode(java.lang.String value) {
		this.dictItemCode = value;
	}

    public java.lang.String getDictItemCode() {
		return this.dictItemCode;
	}

    public void setDictItemNm(java.lang.String value) {
		this.dictItemNm = value;
	}

    public java.lang.String getDictItemNm() {
		return this.dictItemNm;
	}

    public void setDataDictId(java.lang.Long value) {
		this.dataDictId = value;
	}

    public java.lang.Long getDataDictId() {
		return this.dataDictId;
	}

    public void setIsDefault(java.lang.String value) {
		this.isDefault = value;
	}

    public java.lang.String getIsDefault() {
		return this.isDefault;
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

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("DICT_ITEM_CODE",getDictItemCode())
			.append("DICT_ITEM_NM",getDictItemNm())
			.append("DATA_DICT_ID",getDataDictId())
			.append("IS_DEFAULT",getIsDefault())
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
		if(!(obj instanceof SysDictItem)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysDictItem other = (SysDictItem)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

