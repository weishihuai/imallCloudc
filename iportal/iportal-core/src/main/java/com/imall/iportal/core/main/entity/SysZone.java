
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
 * T_PT_SYS_ZONE【地区表】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_zone" )
public class SysZone extends TreeBaseEntity<Long> {

	public static final String ZONE_NAME = "zoneName";
	public static final String ORDERBY = "orderby";

    /** ZONE_NAME - 地区名称 */
    @Column(name = "ZONE_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String zoneName;
    /** ORDERBY - 顺序 */
    @Column(name = "ORDERBY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long orderby;

    public void setZoneName(String value) {
		this.zoneName = value;
	}

    public String getZoneName() {
		return this.zoneName;
	}

    public void setZoneCode(String value) {
		this.setNodeCode(value);
	}

    public String getZoneCode() {
		return this.getNodeCode();
	}

    public void setOrderby(Long value) {
		this.orderby = value;
	}

    public Long getOrderby() {
		return this.orderby;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("ZONE_NAME",getZoneName())
			.append("ZONE_CODE",getZoneCode())
			.append("PARENT_ID",getParentId())
			.append("ORDERBY",getOrderby())
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
		if(!(obj instanceof SysZone)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysZone other = (SysZone)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

