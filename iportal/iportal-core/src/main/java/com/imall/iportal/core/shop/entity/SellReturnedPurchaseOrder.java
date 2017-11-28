
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
 * 销售 退货 订单实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_sell_returned_purchase_order" )
public class SellReturnedPurchaseOrder extends BaseEntity<Long>{

	public static final String ORDER_ID = "orderId";
	public static final String SELL_RETURNED_PURCHASE_ORDER_NUM = "sellReturnedPurchaseOrderNum";
	public static final String SHOP_ID = "shopId";
	public static final String REFUND_TOTAL_AMOUNT = "refundTotalAmount";
	public static final String REAL_RETURN_CASH_AMOUNT = "realReturnCashAmount";
	public static final String RETURN_SMALL = "returnSmall";
	public static final String CASHIER_ID = "cashierId";
	public static final String IS_OVERALL_RETURNED_PURCHASE = "isOverallReturnedPurchase";
	public static final String APPROVE_MAN_ID = "approveManId";
	public static final String REMARK = "remark";
	public static final String RETURNED_PURCHASE_TIME = "returnedPurchaseTime";

    /** ORDER_ID - 订单 ID */
    @Column(name = "ORDER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long orderId;
	/** SELL_RETURNED_PURCHASE_ORDER_NUM - 销售 退货 订单 编号 */
	@Column(name = "SELL_RETURNED_PURCHASE_ORDER_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String sellReturnedPurchaseOrderNum;
	/** SHOP_ID - 子公司 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long shopId;
    /** REFUND_TOTAL_AMOUNT - 应退 总 金额 */
    @Column(name = "REFUND_TOTAL_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private java.lang.Double refundTotalAmount;
    /** REAL_RETURN_CASH_AMOUNT - 实退 现金 金额 */
    @Column(name = "REAL_RETURN_CASH_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private java.lang.Double realReturnCashAmount;
    /** RETURN_SMALL - 找零 */
    @Column(name = "RETURN_SMALL", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private java.lang.Double returnSmall;
    /** CASHIER_ID - 收款员 */
    @Column(name = "CASHIER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long cashierId;
    /** IS_OVERALL_RETURNED_PURCHASE - 是否 整单 退货 */
    @Column(name = "IS_OVERALL_RETURNED_PURCHASE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private java.lang.String isOverallReturnedPurchase;
    /** APPROVE_MAN_ID - 审核 人 ID */
    @Column(name = "APPROVE_MAN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long approveManId;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private java.lang.String remark;
    /** RETURNED_PURCHASE_TIME - 退货 时间 */
    @Column(name = "RETURNED_PURCHASE_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date returnedPurchaseTime;

    public void setOrderId(java.lang.Long value) {
		this.orderId = value;
	}

    public java.lang.Long getOrderId() {
		return this.orderId;
	}

	public String getSellReturnedPurchaseOrderNum() {
		return sellReturnedPurchaseOrderNum;
	}

	public void setSellReturnedPurchaseOrderNum(String sellReturnedPurchaseOrderNum) {
		this.sellReturnedPurchaseOrderNum = sellReturnedPurchaseOrderNum;
	}

	public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

    public java.lang.Long getShopId() {
		return this.shopId;
	}

    public void setRefundTotalAmount(java.lang.Double value) {
		this.refundTotalAmount = value;
	}

    public java.lang.Double getRefundTotalAmount() {
		return this.refundTotalAmount;
	}

    public void setRealReturnCashAmount(java.lang.Double value) {
		this.realReturnCashAmount = value;
	}

    public java.lang.Double getRealReturnCashAmount() {
		return this.realReturnCashAmount;
	}

    public void setReturnSmall(java.lang.Double value) {
		this.returnSmall = value;
	}

    public java.lang.Double getReturnSmall() {
		return this.returnSmall;
	}

    public void setCashierId(java.lang.Long value) {
		this.cashierId = value;
	}

    public java.lang.Long getCashierId() {
		return this.cashierId;
	}

    public void setIsOverallReturnedPurchase(java.lang.String value) {
		this.isOverallReturnedPurchase = value;
	}

    public java.lang.String getIsOverallReturnedPurchase() {
		return this.isOverallReturnedPurchase;
	}

    public void setApproveManId(java.lang.Long value) {
		this.approveManId = value;
	}

    public java.lang.Long getApproveManId() {
		return this.approveManId;
	}

    public void setRemark(java.lang.String value) {
		this.remark = value;
	}

    public java.lang.String getRemark() {
		return this.remark;
	}

    public void setReturnedPurchaseTime(java.util.Date value) {
		this.returnedPurchaseTime = value;
	}

    public java.util.Date getReturnedPurchaseTime() {
		return this.returnedPurchaseTime;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("ORDER_ID",getOrderId())
			.append("SELL_RETURNED_PURCHASE_ORDER_NUM",getSellReturnedPurchaseOrderNum())
			.append("SHOP_ID",getShopId())
			.append("REFUND_TOTAL_AMOUNT",getRefundTotalAmount())
			.append("REAL_RETURN_CASH_AMOUNT",getRealReturnCashAmount())
			.append("RETURN_SMALL",getReturnSmall())
			.append("CASHIER_ID",getCashierId())
			.append("IS_OVERALL_RETURNED_PURCHASE",getIsOverallReturnedPurchase())
			.append("APPROVE_MAN_ID",getApproveManId())
			.append("REMARK",getRemark())
			.append("RETURNED_PURCHASE_TIME",getReturnedPurchaseTime())
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
		if(!(obj instanceof SellReturnedPurchaseOrder)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SellReturnedPurchaseOrder other = (SellReturnedPurchaseOrder)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

