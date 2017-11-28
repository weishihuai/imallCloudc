
package com.imall.iportal.core.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.math.BigDecimal;
/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_goods_cosmetic" )
public class GoodsCosmetic extends BaseEntity<Long>{

	public static final String GOODS_ID = "goodsId";
	public static final String APPROVAL_NUMBER = "approvalNumber";
	public static final String MANUFACTURER_ADDR = "manufacturerAddr";

    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long goodsId;
    /** APPROVAL_NUMBER - 批准文号 */
    @Column(name = "APPROVAL_NUMBER", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private java.lang.String approvalNumber;
    /** MANUFACTURER_ADDR - 厂家 地址 */
    @Column(name = "MANUFACTURER_ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private java.lang.String manufacturerAddr;

    public void setGoodsId(java.lang.Long value) {
		this.goodsId = value;
	}

    public java.lang.Long getGoodsId() {
		return this.goodsId;
	}

    public void setApprovalNumber(java.lang.String value) {
		this.approvalNumber = value;
	}

    public java.lang.String getApprovalNumber() {
		return this.approvalNumber;
	}

    public void setManufacturerAddr(java.lang.String value) {
		this.manufacturerAddr = value;
	}

    public java.lang.String getManufacturerAddr() {
		return this.manufacturerAddr;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("GOODS_ID",getGoodsId())
			.append("APPROVAL_NUMBER",getApprovalNumber())
			.append("MANUFACTURER_ADDR",getManufacturerAddr())
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
		if(!(obj instanceof GoodsCosmetic)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		GoodsCosmetic other = (GoodsCosmetic)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

