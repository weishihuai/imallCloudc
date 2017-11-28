
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
 * 销售 退货 订单 项实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_sell_returned_purchase_order_item" )
public class SellReturnedPurchaseOrderItem extends BaseEntity<Long>{

	public static final String ORDER_ITEM_ID = "orderItemId";
	public static final String RETURNED_PURCHASE_ORDER_ID = "returnedPurchaseOrderId";
	public static final String RETURNED_PURCHASE_QUANTITY = "returnedPurchaseQuantity";
	public static final String UNIT_PRICE = "unitPrice";
	public static final String TOTAL_AMOUNT = "totalAmount";
	public static final String GOODS_BATCH_ID = "goodsBatchId";
	public static final String BATCH_NUM = "batchNum";

    /** ORDER_ITEM_ID - 订单 项 ID */
    @Column(name = "ORDER_ITEM_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long orderItemId;
    /** RETURNED_PURCHASE_ORDER_ID - 退货 订单 ID */
    @Column(name = "RETURNED_PURCHASE_ORDER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long returnedPurchaseOrderId;
    /** RETURNED_PURCHASE_QUANTITY - 退货 数量 */
    @Column(name = "RETURNED_PURCHASE_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    private java.lang.Long returnedPurchaseQuantity;
    /** UNIT_PRICE - 单位 价格 */
    @Column(name = "UNIT_PRICE", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private java.lang.Double unitPrice;
    /** TOTAL_AMOUNT - 总 金额 */
    @Column(name = "TOTAL_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private java.lang.Double totalAmount;
	/** GOODS_BATCH_ID - 商品 批次 ID */
	@Column(name = "GOODS_BATCH_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long goodsBatchId;
    /** BATCH_NUM - 批号 */
    @Column(name = "BATCH_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String batchNum;

    public void setOrderItemId(java.lang.Long value) {
		this.orderItemId = value;
	}

    public java.lang.Long getOrderItemId() {
		return this.orderItemId;
	}

    public void setReturnedPurchaseOrderId(java.lang.Long value) {
		this.returnedPurchaseOrderId = value;
	}

    public java.lang.Long getReturnedPurchaseOrderId() {
		return this.returnedPurchaseOrderId;
	}

    public void setReturnedPurchaseQuantity(java.lang.Long value) {
		this.returnedPurchaseQuantity = value;
	}

    public java.lang.Long getReturnedPurchaseQuantity() {
		return this.returnedPurchaseQuantity;
	}

    public void setUnitPrice(java.lang.Double value) {
		this.unitPrice = value;
	}

    public java.lang.Double getUnitPrice() {
		return this.unitPrice;
	}

    public void setTotalAmount(java.lang.Double value) {
		this.totalAmount = value;
	}

    public java.lang.Double getTotalAmount() {
		return this.totalAmount;
	}

    public void setBatchNum(java.lang.String value) {
		this.batchNum = value;
	}

    public java.lang.String getBatchNum() {
		return this.batchNum;
	}

	public Long getGoodsBatchId() {
		return goodsBatchId;
	}

	public void setGoodsBatchId(Long goodsBatchId) {
		this.goodsBatchId = goodsBatchId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("ORDER_ITEM_ID",getOrderItemId())
			.append("RETURNED_PURCHASE_ORDER_ID",getReturnedPurchaseOrderId())
			.append("RETURNED_PURCHASE_QUANTITY",getReturnedPurchaseQuantity())
			.append("UNIT_PRICE",getUnitPrice())
			.append("TOTAL_AMOUNT",getTotalAmount())
			.append("GOODS_BATCH_ID",getGoodsBatchId())
			.append("BATCH_NUM",getBatchNum())
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
		if(!(obj instanceof SellReturnedPurchaseOrderItem)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SellReturnedPurchaseOrderItem other = (SellReturnedPurchaseOrderItem)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

