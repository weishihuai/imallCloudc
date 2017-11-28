
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
 * SKU_锁_库存实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_sku_lock_stock" )
public class SkuLockStock extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String SKU_ID = "skuId";
	public static final String QUANTITY = "quantity";
	public static final String LOCK_TIME = "lockTime";
	public static final String LOCK_STATE_CODE = "lockStateCode";
	public static final String MEMBER_ID = "memberId";
	public static final String ORDER_NUM = "orderNum";
	public static final String ORDER_SOURCE_CODE = "orderSourceCode";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long shopId;
    /** SKU_ID - SKU ID */
    @Column(name = "SKU_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private java.lang.Long skuId;
    /** QUANTITY - 数量 */
    @Column(name = "QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long quantity;
    /** LOCK_TIME - 上锁 时间 */
    @Column(name = "LOCK_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date lockTime;
    /** LOCK_STATE_CODE - 锁 状态 代码 */
    @Column(name = "LOCK_STATE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 2147483647)
    private java.lang.String lockStateCode;
    /** MEMBER_ID - 会员 ID */
    @Column(name = "MEMBER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private java.lang.Long memberId;
    /** ORDER_NUM - 订单 编号 */
    @Column(name = "ORDER_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String orderNum;
	/** ORDER_SOURCE_CODE - 订单 来源 代码 */
	@Column(name = "ORDER_SOURCE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String orderSourceCode;

    public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

    public java.lang.Long getShopId() {
		return this.shopId;
	}

    public void setSkuId(java.lang.Long value) {
		this.skuId = value;
	}

    public java.lang.Long getSkuId() {
		return this.skuId;
	}

    public void setQuantity(java.lang.Long value) {
		this.quantity = value;
	}

    public java.lang.Long getQuantity() {
		return this.quantity;
	}

    public void setLockTime(java.util.Date value) {
		this.lockTime = value;
	}

    public java.util.Date getLockTime() {
		return this.lockTime;
	}

    public void setLockStateCode(java.lang.String value) {
		this.lockStateCode = value;
	}

    public java.lang.String getLockStateCode() {
		return this.lockStateCode;
	}

    public void setMemberId(java.lang.Long value) {
		this.memberId = value;
	}

    public java.lang.Long getMemberId() {
		return this.memberId;
	}

    public void setOrderNum(java.lang.String value) {
		this.orderNum = value;
	}

    public java.lang.String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderSourceCode(java.lang.String value) {
		this.orderSourceCode = value;
	}

	public java.lang.String getOrderSourceCode() {
		return this.orderSourceCode;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("SKU_ID",getSkuId())
			.append("QUANTITY",getQuantity())
			.append("LOCK_TIME",getLockTime())
			.append("LOCK_STATE_CODE",getLockStateCode())
			.append("MEMBER_ID",getMemberId())
			.append("ORDER_NUM",getOrderNum())
			.append("ORDER_SOURCE_CODE",getOrderSourceCode())
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
		if(!(obj instanceof SkuLockStock)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SkuLockStock other = (SkuLockStock)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

