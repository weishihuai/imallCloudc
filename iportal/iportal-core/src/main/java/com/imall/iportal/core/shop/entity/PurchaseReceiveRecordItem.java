
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
@Table(name = "t_shp_purchase_receive_record_item" )
public class PurchaseReceiveRecordItem extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String PURCHASE_RECEIVE_RECORD_ID = "purchaseReceiveRecordId";
	public static final String PURCHASE_ORDER_ID = "purchaseOrderId";
	public static final String GOODS_ID = "goodsId";
	public static final String SUPPLIER_ID = "supplierId";
	public static final String PURCHASE_UNIT_PRICE = "purchaseUnitPrice";
	public static final String RECEIVE_QUANTITY = "receiveQuantity";
	public static final String REJECTION_REASON = "rejectionReason";
	public static final String PURCHASE_QUANTITY = "purchaseQuantity";
	public static final String IS_ACCEPTANCE = "isAcceptance";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** PURCHASE_RECEIVE_RECORD_ID - 采购 收货 记录 ID */
    @Column(name = "PURCHASE_RECEIVE_RECORD_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long purchaseReceiveRecordId;
	/** PURCHASE_ORDER_ID - 采购 订单 ID */
	@Column(name = "PURCHASE_ORDER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private Long purchaseOrderId;
	/** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsId;
    /** SUPPLIER_ID - 供应商 ID */
    @Column(name = "SUPPLIER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long supplierId;
    /** PURCHASE_UNIT_PRICE - 采购 单位 价格 */
    @Column(name = "PURCHASE_UNIT_PRICE", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double purchaseUnitPrice;
    /** RECEIVE_QUANTITY - 收货 数量 */
    @Column(name = "RECEIVE_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long receiveQuantity;
    /** REJECTION_REASON - 拒收 原因 */
    @Column(name = "REJECTION_REASON", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String rejectionReason;
	/** PURCHASE_QUANTITY - 采购 数量 */
	@Column(name = "PURCHASE_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private Long purchaseQuantity;
    /** IS_ACCEPTANCE - 是否 已验收 */
    @Column(name = "IS_ACCEPTANCE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isAcceptance;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setPurchaseReceiveRecordId(Long value) {
		this.purchaseReceiveRecordId = value;
	}

    public Long getPurchaseReceiveRecordId() {
		return this.purchaseReceiveRecordId;
	}

	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public void setGoodsId(Long value) {
		this.goodsId = value;
	}

    public Long getGoodsId() {
		return this.goodsId;
	}

    public void setSupplierId(Long value) {
		this.supplierId = value;
	}

    public Long getSupplierId() {
		return this.supplierId;
	}

    public void setPurchaseUnitPrice(Double value) {
		this.purchaseUnitPrice = value;
	}

    public Double getPurchaseUnitPrice() {
		return this.purchaseUnitPrice;
	}

    public void setReceiveQuantity(Long value) {
		this.receiveQuantity = value;
	}

    public Long getReceiveQuantity() {
		return this.receiveQuantity;
	}

    public void setRejectionReason(String value) {
		this.rejectionReason = value;
	}

    public String getRejectionReason() {
		return this.rejectionReason;
	}

	public Long getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(Long purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public void setIsAcceptance(String value) {
		this.isAcceptance = value;
	}

    public String getIsAcceptance() {
		return this.isAcceptance;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("PURCHASE_RECEIVE_RECORD_ID",getPurchaseReceiveRecordId())
			.append("PURCHASE_ORDER_ID",getPurchaseOrderId())
			.append("GOODS_ID",getGoodsId())
			.append("SUPPLIER_ID",getSupplierId())
			.append("PURCHASE_UNIT_PRICE",getPurchaseUnitPrice())
			.append("RECEIVE_QUANTITY",getReceiveQuantity())
			.append("REJECTION_REASON",getRejectionReason())
			.append("PURCHASE_QUANTITY",getPurchaseQuantity())
			.append("IS_ACCEPTANCE",getIsAcceptance())
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
		if(!(obj instanceof PurchaseReceiveRecordItem)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		PurchaseReceiveRecordItem other = (PurchaseReceiveRecordItem)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

