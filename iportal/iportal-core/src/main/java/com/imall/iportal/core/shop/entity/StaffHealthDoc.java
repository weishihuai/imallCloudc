
package com.imall.iportal.core.shop.entity;

import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_staff_health_doc" )
public class StaffHealthDoc extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String STAFF_ID = "staffId";

	/** SHOP_ID - 门店 ID */
	@Column(name = "SHOP_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	private java.lang.Long shopId;
	/** STAFF_ID - 员工 ID */
	@Column(name = "STAFF_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long staffId;

    public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

    public java.lang.Long getShopId() {
		return this.shopId;
	}

    public void setStaffId(java.lang.Long value) {
		this.staffId = value;
	}

    public java.lang.Long getStaffId() {
		return this.staffId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("STAFF_ID",getStaffId())
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
		if(!(obj instanceof StaffHealthDoc)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		StaffHealthDoc other = (StaffHealthDoc)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

