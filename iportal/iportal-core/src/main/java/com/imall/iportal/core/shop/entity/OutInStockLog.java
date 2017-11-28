
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
@Table(name = "t_shp_out_in_stock_log" )
public class OutInStockLog extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String GOODS_ID = "goodsId";
	public static final String SKU_ID = "skuId";
	public static final String TYPE_CODE = "typeCode";
	public static final String GOODS_BATCH_ID = "goodsBatchId";
	public static final String STORAGE_SPACE_ID = "storageSpaceId";
	public static final String QUANTITY = "quantity";
	public static final String AMOUNT = "amount";
	public static final String CLEARING_PREV_QUANTITY = "clearingPrevQuantity";
	public static final String CLEARING_PREV_AMOUNT = "clearingPrevAmount";
	public static final String LOG_SOURCE_TYPE_CODE = "logSourceTypeCode";
	public static final String LOG_SOURCE_OBJECT_ID = "logSourceObjectId";
	public static final String OBJECT_ORDER_NUM = "objectOrderNum";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long shopId;
    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long goodsId;
    /** SKU_ID - SKU ID */
    @Column(name = "SKU_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long skuId;
    /** TYPE_CODE - 出入库 类型 代码 */
    @Column(name = "TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String typeCode;
    /** GOODS_BATCH_ID - 商品 批次 ID */
    @Column(name = "GOODS_BATCH_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long goodsBatchId;
    /** STORAGE_SPACE_ID - 货位 ID */
    @Column(name = "STORAGE_SPACE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long storageSpaceId;
    /** QUANTITY - 数量 */
    @Column(name = "QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long quantity;
    /** AMOUNT - 金额 */
    @Column(name = "AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private java.lang.Double amount;
    /** CLEARING_PREV_QUANTITY - 结算 前 数量 */
    @Column(name = "CLEARING_PREV_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long clearingPrevQuantity;
    /** CLEARING_PREV_AMOUNT - 结算 前 金额 */
    @Column(name = "CLEARING_PREV_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private java.lang.Double clearingPrevAmount;
    /** LOG_SOURCE_TYPE_CODE - 日志 来源 类型 代码 */
    @Column(name = "LOG_SOURCE_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String logSourceTypeCode;
    /** LOG_SOURCE_OBJECT_ID - 日志 来源 对象 ID */
    @Column(name = "LOG_SOURCE_OBJECT_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long logSourceObjectId;
	/** OBJECT_ORDER_NUM - 对象 单号 */
	@Column(name = "OBJECT_ORDER_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private String objectOrderNum;

	public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

    public java.lang.Long getShopId() {
		return this.shopId;
	}

    public void setGoodsId(java.lang.Long value) {
		this.goodsId = value;
	}

    public java.lang.Long getGoodsId() {
		return this.goodsId;
	}

    public void setSkuId(java.lang.Long value) {
		this.skuId = value;
	}

    public java.lang.Long getSkuId() {
		return this.skuId;
	}

    public void setTypeCode(java.lang.String value) {
		this.typeCode = value;
	}

    public java.lang.String getTypeCode() {
		return this.typeCode;
	}

    public void setGoodsBatchId(java.lang.Long value) {
		this.goodsBatchId = value;
	}

    public java.lang.Long getGoodsBatchId() {
		return this.goodsBatchId;
	}

    public void setStorageSpaceId(java.lang.Long value) {
		this.storageSpaceId = value;
	}

    public java.lang.Long getStorageSpaceId() {
		return this.storageSpaceId;
	}

    public void setQuantity(java.lang.Long value) {
		this.quantity = value;
	}

    public java.lang.Long getQuantity() {
		return this.quantity;
	}

    public void setAmount(java.lang.Double value) {
		this.amount = value;
	}

    public java.lang.Double getAmount() {
		return this.amount;
	}

    public void setClearingPrevQuantity(java.lang.Long value) {
		this.clearingPrevQuantity = value;
	}

    public java.lang.Long getClearingPrevQuantity() {
		return this.clearingPrevQuantity;
	}

    public void setClearingPrevAmount(java.lang.Double value) {
		this.clearingPrevAmount = value;
	}

    public java.lang.Double getClearingPrevAmount() {
		return this.clearingPrevAmount;
	}

    public void setLogSourceTypeCode(java.lang.String value) {
		this.logSourceTypeCode = value;
	}

    public java.lang.String getLogSourceTypeCode() {
		return this.logSourceTypeCode;
	}

    public void setLogSourceObjectId(java.lang.Long value) {
		this.logSourceObjectId = value;
	}

    public java.lang.Long getLogSourceObjectId() {
		return this.logSourceObjectId;
	}

	public String getObjectOrderNum() {
		return objectOrderNum;
	}

	public void setObjectOrderNum(String objectOrderNum) {
		this.objectOrderNum = objectOrderNum;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("GOODS_ID",getGoodsId())
			.append("SKU_ID",getSkuId())
			.append("TYPE_CODE",getTypeCode())
			.append("GOODS_BATCH_ID",getGoodsBatchId())
			.append("STORAGE_SPACE_ID",getStorageSpaceId())
			.append("QUANTITY",getQuantity())
			.append("AMOUNT",getAmount())
			.append("CLEARING_PREV_QUANTITY",getClearingPrevQuantity())
			.append("CLEARING_PREV_AMOUNT",getClearingPrevAmount())
			.append("LOG_SOURCE_TYPE_CODE",getLogSourceTypeCode())
			.append("LOG_SOURCE_OBJECT_ID",getLogSourceObjectId())
			.append("OBJECT_ORDER_NUM",getObjectOrderNum())
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
		if(!(obj instanceof OutInStockLog)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		OutInStockLog other = (OutInStockLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

