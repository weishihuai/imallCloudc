
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
@Table(name = "t_shp_order_send_out_batch" )
public class OrderSendOutBatch extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String WE_SHOP_ID = "weShopId";
	public static final String ORDER_ID = "orderId";
	public static final String ORDER_ITEM_ID = "orderItemId";
	public static final String GOODS_BATCH_ID = "goodsBatchId";
	public static final String BATCH = "batch";
	public static final String QUANTITY = "quantity";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long shopId;
    /** WE_SHOP_ID - 微门店 ID */
    @Column(name = "WE_SHOP_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private java.lang.Long weShopId;
    /** ORDER_ID - 订单 ID */
    @Column(name = "ORDER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long orderId;
    /** ORDER_ITEM_ID - 订单 项 ID */
    @Column(name = "ORDER_ITEM_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long orderItemId;
    /** GOODS_BATCH_ID - 商品 批次 ID */
    @Column(name = "GOODS_BATCH_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long goodsBatchId;
    /** BATCH - 批号 */
    @Column(name = "BATCH", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String batch;
    /** QUANTITY - 数量 */
    @Column(name = "QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long quantity;

    public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

    public java.lang.Long getShopId() {
		return this.shopId;
	}

    public void setWeShopId(java.lang.Long value) {
		this.weShopId = value;
	}

    public java.lang.Long getWeShopId() {
		return this.weShopId;
	}

    public void setOrderId(java.lang.Long value) {
		this.orderId = value;
	}

    public java.lang.Long getOrderId() {
		return this.orderId;
	}

    public void setOrderItemId(java.lang.Long value) {
		this.orderItemId = value;
	}

    public java.lang.Long getOrderItemId() {
		return this.orderItemId;
	}

    public void setGoodsBatchId(java.lang.Long value) {
		this.goodsBatchId = value;
	}

    public java.lang.Long getGoodsBatchId() {
		return this.goodsBatchId;
	}

    public void setBatch(java.lang.String value) {
		this.batch = value;
	}

    public java.lang.String getBatch() {
		return this.batch;
	}

    public void setQuantity(java.lang.Long value) {
		this.quantity = value;
	}

    public java.lang.Long getQuantity() {
		return this.quantity;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("WE_SHOP_ID",getWeShopId())
			.append("ORDER_ID",getOrderId())
			.append("ORDER_ITEM_ID",getOrderItemId())
			.append("GOODS_BATCH_ID",getGoodsBatchId())
			.append("BATCH",getBatch())
			.append("QUANTITY",getQuantity())
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
		if(!(obj instanceof OrderSendOutBatch)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		OrderSendOutBatch other = (OrderSendOutBatch)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

