
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
@Table(name = "t_shp_storage_space" )
public class StorageSpace extends BaseEntity<Long>{

	public static final String STORAGE_SPACE_NM = "storageSpaceNm";
	public static final String STORAGE_SPACE_TYPE = "storageSpaceType";
	public static final String IS_ENABLE = "isEnable";
	public static final String SHOP_ID = "shopId";
	public static final String IS_BUILT_IN = "isBuiltIn";
	public static final String IS_VIRTUAL_WAREHOUSE = "isVirtualWarehouse";

	/** STORAGE_SPACE_NM - 货位 名称 */
	@Column(name = "STORAGE_SPACE_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String storageSpaceNm;
	/** STORAGE_SPACE_TYPE - 货位 类型 */
	@Column(name = "STORAGE_SPACE_TYPE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String storageSpaceType;
	/** IS_ENABLE - 是否 启用 状态 */
	@Column(name = "IS_ENABLE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	private java.lang.String isEnable;
	/** SHOP_ID - 门店 ID */
	@Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long shopId;
	/** IS_BUILT_IN - 是否 内置 */
	@Column(name = "IS_BUILT_IN", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	private java.lang.String isBuiltIn;
	/** IS_VIRTUAL_WAREHOUSE - 是否 虚拟 仓库 */
	@Column(name = "IS_VIRTUAL_WAREHOUSE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	private java.lang.String isVirtualWarehouse;

	public void setStorageSpaceNm(java.lang.String value) {
		this.storageSpaceNm = value;
	}

	public java.lang.String getStorageSpaceNm() {
		return this.storageSpaceNm;
	}

	public void setStorageSpaceType(java.lang.String value) {
		this.storageSpaceType = value;
	}

	public java.lang.String getStorageSpaceType() {
		return this.storageSpaceType;
	}

	public void setIsEnable(java.lang.String value) {
		this.isEnable = value;
	}

	public java.lang.String getIsEnable() {
		return this.isEnable;
	}

	public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

	public java.lang.Long getShopId() {
		return this.shopId;
	}

	public void setIsBuiltIn(java.lang.String value) {
		this.isBuiltIn = value;
	}

	public java.lang.String getIsBuiltIn() {
		return this.isBuiltIn;
	}

	public void setIsVirtualWarehouse(java.lang.String value) {
		this.isVirtualWarehouse = value;
	}

	public java.lang.String getIsVirtualWarehouse() {
		return this.isVirtualWarehouse;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("ID",getId())
				.append("STORAGE_SPACE_NM",getStorageSpaceNm())
				.append("STORAGE_SPACE_TYPE",getStorageSpaceType())
				.append("IS_ENABLE",getIsEnable())
				.append("SHOP_ID",getShopId())
				.append("IS_BUILT_IN",getIsBuiltIn())
				.append("IS_VIRTUAL_WAREHOUSE",getIsVirtualWarehouse())
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
		if(!(obj instanceof StorageSpace)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		StorageSpace other = (StorageSpace)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}
}

