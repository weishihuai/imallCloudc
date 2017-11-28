
package com.imall.iportal.core.shop.entity;

import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.ParseException;

/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_other_in_stock" )
public class OtherInStock extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String GOODS_ID = "goodsId";
	public static final String SKU_ID = "skuId";
	public static final String IN_STOCK_CODE = "inStockCode";
	public static final String IN_STOCK_TIME = "inStockTime";
	public static final String TYPE_CODE = "typeCode";
	public static final String STORAGE_SPACE_ID = "storageSpaceId";
	public static final String SUPPLIER_ID = "supplierId";
	public static final String GOODS_BATCH_ID = "goodsBatchId";
	public static final String QUANTITY = "quantity";
	public static final String UNIT_PRICE = "unitPrice";
	public static final String REAL_CHECK_AMOUNT = "realCheckAmount";
	public static final String PRODUCE_DATE = "produceDate";
	public static final String VALID_DATE = "validDate";
	public static final String APPROVE_MAN_ID = "approveManId";
	public static final String OPERATION_MAN_ID = "operationManId";
	public static final String REMARK = "remark";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsId;
    /** SKU_ID - SKU ID */
    @Column(name = "SKU_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long skuId;
    /** IN_STOCK_CODE - 入库 单号 */
    @Column(name = "IN_STOCK_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String inStockCode;
    /** IN_STOCK_TIME - 入库 时间 */
    @Column(name = "IN_STOCK_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date inStockTime;
    /** TYPE_CODE - 类型 代码 */
    @Column(name = "TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String typeCode;
    /** STORAGE_SPACE_ID - 货位 ID */
    @Column(name = "STORAGE_SPACE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long storageSpaceId;
    /** SUPPLIER_ID - 供应商 ID */
    @Column(name = "SUPPLIER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long supplierId;
	/** GOODS_BATCH_ID - 商品 批次 ID */
	@Column(name = "GOODS_BATCH_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private Long goodsBatchId;
    /** QUANTITY - 数量 */
    @Column(name = "QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long quantity;
    /** UNIT_PRICE - 单位 价格 */
    @Column(name = "UNIT_PRICE", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double unitPrice;
    /** REAL_CHECK_AMOUNT - 金额 */
    @Column(name = "REAL_CHECK_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double realCheckAmount;
    /** PRODUCE_DATE - 生产 日期 */
    @Column(name = "PRODUCE_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date produceDate;
    /** VALID_DATE - 有效 日期 */
    @Column(name = "VALID_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date validDate;
    /** APPROVE_MAN_ID - 审核 人 ID */
    @Column(name = "APPROVE_MAN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long approveManId;
    /** OPERATION_MAN_ID - 入库人 */
    @Column(name = "OPERATION_MAN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long operationManId;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private String remark;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setGoodsId(Long value) {
		this.goodsId = value;
	}

    public Long getGoodsId() {
		return this.goodsId;
	}

    public void setSkuId(Long value) {
		this.skuId = value;
	}

    public Long getSkuId() {
		return this.skuId;
	}

    public void setInStockCode(String value) {
		this.inStockCode = value;
	}

    public String getInStockCode() {
		return this.inStockCode;
	}

    public void setInStockTime(java.util.Date value) {
		this.inStockTime = value;
	}

    public java.util.Date getInStockTime() {
		return this.inStockTime;
	}

    public void setTypeCode(String value) {
		this.typeCode = value;
	}

    public String getTypeCode() {
		return this.typeCode;
	}

    public void setStorageSpaceId(Long value) {
		this.storageSpaceId = value;
	}

    public Long getStorageSpaceId() {
		return this.storageSpaceId;
	}

    public void setSupplierId(Long value) {
		this.supplierId = value;
	}

    public Long getSupplierId() {
		return this.supplierId;
	}

	public Long getGoodsBatchId() {
		return goodsBatchId;
	}

	public void setGoodsBatchId(Long goodsBatchId) {
		this.goodsBatchId = goodsBatchId;
	}

	public void setQuantity(Long value) {
		this.quantity = value;
	}

    public Long getQuantity() {
		return this.quantity;
	}

    public void setUnitPrice(Double value) {
		this.unitPrice = value;
	}

    public Double getUnitPrice() {
		return this.unitPrice;
	}

    public void setRealCheckAmount(Double value) {
		this.realCheckAmount = value;
	}

    public Double getRealCheckAmount() {
		return this.realCheckAmount;
	}

    public void setProduceDate(java.util.Date value) {
		this.produceDate = value;
	}

    public java.util.Date getProduceDate() {
		return this.produceDate;
	}

    public void setValidDate(java.util.Date value) {
		this.validDate = value;
	}

    public java.util.Date getValidDate() {
		return this.validDate;
	}

    public void setApproveManId(Long value) {
		this.approveManId = value;
	}

    public Long getApproveManId() {
		return this.approveManId;
	}

    public void setOperationManId(Long value) {
		this.operationManId = value;
	}

    public Long getOperationManId() {
		return this.operationManId;
	}

    public void setRemark(String value) {
		this.remark = value;
	}

    public String getRemark() {
		return this.remark;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("GOODS_ID",getGoodsId())
			.append("SKU_ID",getSkuId())
			.append("IN_STOCK_CODE",getInStockCode())
			.append("IN_STOCK_TIME",getInStockTime())
			.append("TYPE_CODE",getTypeCode())
			.append("STORAGE_SPACE_ID",getStorageSpaceId())
			.append("SUPPLIER_ID",getSupplierId())
			.append("GOODS_BATCH_ID",getGoodsBatchId())
			.append("QUANTITY",getQuantity())
			.append("UNIT_PRICE",getUnitPrice())
			.append("REAL_CHECK_AMOUNT",getRealCheckAmount())
			.append("PRODUCE_DATE",getProduceDate())
			.append("VALID_DATE",getValidDate())
			.append("APPROVE_MAN_ID",getApproveManId())
			.append("OPERATION_MAN_ID",getOperationManId())
			.append("REMARK",getRemark())
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
		if(!(obj instanceof OtherInStock)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		OtherInStock other = (OtherInStock)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getInStockTimeString() {
		return DateTimeUtils.convertDateToString(this.getInStockTime());
	}

	public void setInStockTimeString(String value) throws ParseException {
		this.setInStockTime(DateTimeUtils.convertStringToDate(value));
	}
}

