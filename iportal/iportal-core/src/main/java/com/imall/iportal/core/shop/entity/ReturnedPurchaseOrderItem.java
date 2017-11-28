
package com.imall.iportal.core.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.math.BigDecimal;
import java.text.ParseException;

/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_returned_purchase_order_item" )
public class ReturnedPurchaseOrderItem extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String RETURNED_PURCHASE_ORDER_ID = "returnedPurchaseOrderId";
	public static final String GOODS_ID = "goodsId";
	public static final String SUPPLIER_ID = "supplierId";
	public static final String GOODS_BATCH = "goodsBatch";
	public static final String GOODS_BATCH_ID = "goodsBatchId";
	public static final String PRODUCTION_DATE = "productionDate";
	public static final String VALIDITY = "validity";
	public static final String RETURNED_PURCHASE_QUANTITY = "returnedPurchaseQuantity";
	public static final String RETURNABLE_QUANTITY = "returnableQuantity";
	public static final String PURCHASE_UNIT_PRICE = "purchaseUnitPrice";
	public static final String AMOUNT = "amount";
	public static final String PURCHASE_ACCEPTANCE_RECORD_ITEM_ID = "purchaseAcceptanceRecordItemId";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** RETURNED_PURCHASE_ORDER_ID - 购进 退出 订单 ID */
    @Column(name = "RETURNED_PURCHASE_ORDER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long returnedPurchaseOrderId;
    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsId;
    /** SUPPLIER_ID - 供应商 ID */
    @Column(name = "SUPPLIER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long supplierId;
    /** GOODS_BATCH - 商品 批次 */
    @Column(name = "GOODS_BATCH", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String goodsBatch;
	/** GOODS_BATCH_ID - 商品 批次 ID*/
	@Column(name = "GOODS_BATCH_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private Long goodsBatchId;
    /** PRODUCTION_DATE - 生产 日期 */
    @Column(name = "PRODUCTION_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date productionDate;
    /** VALIDITY - 有效期 */
    @Column(name = "VALIDITY", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date validity;
    /** RETURNED_PURCHASE_QUANTITY - 退货 数量 */
    @Column(name = "RETURNED_PURCHASE_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long returnedPurchaseQuantity;
    /** RETURNABLE_QUANTITY - 可退 数量 */
    @Column(name = "RETURNABLE_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long returnableQuantity;
    /** PURCHASE_UNIT_PRICE - 采购 单位 价格 */
    @Column(name = "PURCHASE_UNIT_PRICE", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double purchaseUnitPrice;
    /** AMOUNT - 金额 */
    @Column(name = "AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double amount;
	/** PURCHASE_ACCEPTANCE_RECORD_ITEM_ID - 采购 验收 记录 项 ID */
	@Column(name = "PURCHASE_ACCEPTANCE_RECORD_ITEM_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private Long purchaseAcceptanceRecordItemId;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setReturnedPurchaseOrderId(Long value) {
		this.returnedPurchaseOrderId = value;
	}

    public Long getReturnedPurchaseOrderId() {
		return this.returnedPurchaseOrderId;
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

    public void setGoodsBatch(String value) {
		this.goodsBatch = value;
	}

    public String getGoodsBatch() {
		return this.goodsBatch;
	}

	public Long getGoodsBatchId() {
		return goodsBatchId;
	}

	public void setGoodsBatchId(Long goodsBatchId) {
		this.goodsBatchId = goodsBatchId;
	}

	public void setProductionDate(java.util.Date value) {
		this.productionDate = value;
	}

	public void setProductionDateString(String value) throws ParseException {
		this.productionDate = DateTimeUtils.convertStringToDate(value);
	}

	public java.util.Date getProductionDate() {
		return this.productionDate;
	}

	public String getProductionDateString() {
		return DateTimeUtils.convertDateToString(productionDate);
	}

	public void setValidity(java.util.Date value) {
		this.validity = value;
	}

	public void setValidityString(String value) throws ParseException {
		this.validity = DateTimeUtils.convertStringToDate(value);
	}

	public java.util.Date getValidity() {
		return this.validity;
	}

	public String getValidityString() {
		return DateTimeUtils.convertDateToString(validity);
	}

    public void setReturnedPurchaseQuantity(Long value) {
		this.returnedPurchaseQuantity = value;
	}

    public Long getReturnedPurchaseQuantity() {
		return this.returnedPurchaseQuantity;
	}

    public void setReturnableQuantity(Long value) {
		this.returnableQuantity = value;
	}

    public Long getReturnableQuantity() {
		return this.returnableQuantity;
	}

    public void setPurchaseUnitPrice(Double value) {
		this.purchaseUnitPrice = value;
	}

    public Double getPurchaseUnitPrice() {
		return this.purchaseUnitPrice;
	}

    public void setAmount(Double value) {
		this.amount = value;
	}

    public Double getAmount() {
		return this.amount;
	}

	public Long getPurchaseAcceptanceRecordItemId() {
		return purchaseAcceptanceRecordItemId;
	}

	public void setPurchaseAcceptanceRecordItemId(Long purchaseAcceptanceRecordItemId) {
		this.purchaseAcceptanceRecordItemId = purchaseAcceptanceRecordItemId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("RETURNED_PURCHASE_ORDER_ID",getReturnedPurchaseOrderId())
			.append("GOODS_ID",getGoodsId())
			.append("SUPPLIER_ID",getSupplierId())
			.append("GOODS_BATCH",getGoodsBatch())
			.append("GOODS_BATCH_ID",getGoodsBatchId())
			.append("PRODUCTION_DATE",getProductionDate())
			.append("VALIDITY",getValidity())
			.append("RETURNED_PURCHASE_QUANTITY",getReturnedPurchaseQuantity())
			.append("RETURNABLE_QUANTITY",getReturnableQuantity())
			.append("PURCHASE_UNIT_PRICE",getPurchaseUnitPrice())
			.append("AMOUNT",getAmount())
			.append("PURCHASE_ACCEPTANCE_RECORD_ITEM_ID",getPurchaseAcceptanceRecordItemId())
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
		if(!(obj instanceof ReturnedPurchaseOrderItem)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		ReturnedPurchaseOrderItem other = (ReturnedPurchaseOrderItem)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

