
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
@Table(name = "t_shp_sku" )
public class Sku extends BaseEntity<Long>{

	public static final String GOODS_ID = "goodsId";
	public static final String RETAIL_PRICE = "retailPrice";
	public static final String MEMBER_PRICE = "memberPrice";
	public static final String MARKET_PRICE = "marketPrice";
	public static final String COST_PRICE = "costPrice";
	public static final String CURRENT_STOCK = "currentStock";
	public static final String SECURITY_STOCK = "securityStock";
	public static final String LOCK_STOCK_QUANTITY = "lockStockQuantity";
	public static final String IS_SPECIAL_PRICE_GOODS = "isSpecialPriceGoods";
	public static final String IS_SPLIT_ZERO = "isSplitZero";
	public static final String SPLIT_ZERO_UNIT = "splitZeroUnit";
	public static final String SPLIT_ZERO_QUANTITY = "splitZeroQuantity";
	public static final String SPLIT_ZERO_SPEC = "splitZeroSpec";
	public static final String SALES_VOLUME = "salesVolume";
	public static final String SPLIT_ZERO_RETAIL_PRICE = "splitZeroRetailPrice";
	public static final String SPLIT_ZERO_MEMBER_PRICE = "splitZeroMemberPrice";

    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long goodsId;
	/** SALES_VOLUME - 销量*/
    @Column(name = "SALES_VOLUME", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long salesVolume;
    /** RETAIL_PRICE - 零售 价格 */
    @Column(name = "RETAIL_PRICE", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private java.lang.Double retailPrice;
    /** MEMBER_PRICE - 会员 价格 */
    @Column(name = "MEMBER_PRICE", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private java.lang.Double memberPrice;
    /** MARKET_PRICE - 市场 价格 */
    @Column(name = "MARKET_PRICE", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private java.lang.Double marketPrice;
    /** COST_PRICE - 成本 价格 */
    @Column(name = "COST_PRICE", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private java.lang.Double costPrice;
    /** CURRENT_STOCK - 当前 库存 */
    @Column(name = "CURRENT_STOCK", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long currentStock;
    /** SECURITY_STOCK - 安全 库存 */
    @Column(name = "SECURITY_STOCK", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long securityStock;
	/** LOCK_STOCK_QUANTITY - 锁 库存 数量 */
	@Column(name = "LOCK_STOCK_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long lockStockQuantity;
    /** IS_SPECIAL_PRICE_GOODS - 是否 特价 商品 */
    @Column(name = "IS_SPECIAL_PRICE_GOODS", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
    private java.lang.String isSpecialPriceGoods;
    /** IS_SPLIT_ZERO - 是否 拆零 */
    @Column(name = "IS_SPLIT_ZERO", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
    private java.lang.String isSplitZero;
    /** SPLIT_ZERO_UNIT - 拆零 单位 */
    @Column(name = "SPLIT_ZERO_UNIT", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String splitZeroUnit;
    /** SPLIT_ZERO_QUANTITY - 拆零 数量 */
    @Column(name = "SPLIT_ZERO_QUANTITY", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private java.lang.Long splitZeroQuantity;
    /** SPLIT_ZERO_SPEC - 拆零 规格 */
    @Column(name = "SPLIT_ZERO_SPEC", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String splitZeroSpec;
	/** SPLIT_ZERO_RETAIL_PRICE - 拆零 零售 价格 */
	@Column(name = "SPLIT_ZERO_RETAIL_PRICE", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	private java.lang.Double splitZeroRetailPrice;
	/** SPLIT_ZERO_MEMBER_PRICE - 拆零 会员 价格 */
	@Column(name = "SPLIT_ZERO_MEMBER_PRICE", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	private java.lang.Double splitZeroMemberPrice;

    public void setGoodsId(java.lang.Long value) {
		this.goodsId = value;
	}

    public java.lang.Long getGoodsId() {
		return this.goodsId;
	}

    public void setRetailPrice(java.lang.Double value) {
		this.retailPrice = value;
	}

    public java.lang.Double getRetailPrice() {
		return this.retailPrice;
	}

    public void setMemberPrice(java.lang.Double value) {
		this.memberPrice = value;
	}

    public java.lang.Double getMemberPrice() {
		return this.memberPrice;
	}

    public void setMarketPrice(java.lang.Double value) {
		this.marketPrice = value;
	}

    public java.lang.Double getMarketPrice() {
		return this.marketPrice;
	}

    public void setCostPrice(java.lang.Double value) {
		this.costPrice = value;
	}

    public java.lang.Double getCostPrice() {
		return this.costPrice;
	}

    public void setCurrentStock(java.lang.Long value) {
		this.currentStock = value;
	}

    public java.lang.Long getCurrentStock() {
		return this.currentStock;
	}

	public Long getSecurityStock() {
		return securityStock;
	}

	public void setSecurityStock(Long securityStock) {
		this.securityStock = securityStock;
	}

	public Long getLockStockQuantity() {
		return lockStockQuantity;
	}

	public void setLockStockQuantity(Long lockStockQuantity) {
		this.lockStockQuantity = lockStockQuantity;
	}

	public String getIsSpecialPriceGoods() {
		return isSpecialPriceGoods;
	}

	public void setIsSpecialPriceGoods(String isSpecialPriceGoods) {
		this.isSpecialPriceGoods = isSpecialPriceGoods;
	}

	public void setIsSplitZero(java.lang.String value) {
		this.isSplitZero = value;
	}

    public java.lang.String getIsSplitZero() {
		return this.isSplitZero;
	}

    public void setSplitZeroUnit(java.lang.String value) {
		this.splitZeroUnit = value;
	}

    public java.lang.String getSplitZeroUnit() {
		return this.splitZeroUnit;
	}

    public void setSplitZeroQuantity(java.lang.Long value) {
		this.splitZeroQuantity = value;
	}

    public java.lang.Long getSplitZeroQuantity() {
		return this.splitZeroQuantity;
	}

    public void setSplitZeroSpec(java.lang.String value) {
		this.splitZeroSpec = value;
	}

    public java.lang.String getSplitZeroSpec() {
		return this.splitZeroSpec;
	}

	public Long getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Long salesVolume) {
		this.salesVolume = salesVolume;
	}

	public Double getSplitZeroRetailPrice() {
		return splitZeroRetailPrice;
	}

	public void setSplitZeroRetailPrice(Double splitZeroRetailPrice) {
		this.splitZeroRetailPrice = splitZeroRetailPrice;
	}

	public Double getSplitZeroMemberPrice() {
		return splitZeroMemberPrice;
	}

	public void setSplitZeroMemberPrice(Double splitZeroMemberPrice) {
		this.splitZeroMemberPrice = splitZeroMemberPrice;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("GOODS_ID",getGoodsId())
			.append("SALES_VOLUME",getSalesVolume())
			.append("RETAIL_PRICE",getRetailPrice())
			.append("MEMBER_PRICE",getMemberPrice())
			.append("MARKET_PRICE",getMarketPrice())
			.append("COST_PRICE",getCostPrice())
			.append("CURRENT_STOCK",getCurrentStock())
			.append("SECURITY_STOCK",getSecurityStock())
			.append("LOCK_STOCK_QUANTITY",getLockStockQuantity())
			.append("IS_SPECIAL_PRICE_GOODS",getIsSpecialPriceGoods())
			.append("IS_SPLIT_ZERO",getIsSplitZero())
			.append("SPLIT_ZERO_UNIT",getSplitZeroUnit())
			.append("SPLIT_ZERO_QUANTITY",getSplitZeroQuantity())
			.append("SPLIT_ZERO_SPEC",getSplitZeroSpec())
			.append("SPLIT_ZERO_RETAIL_PRICE",getSplitZeroRetailPrice())
			.append("SPLIT_ZERO_MEMBER_PRICE",getSplitZeroMemberPrice())
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
		if(!(obj instanceof Sku)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		Sku other = (Sku)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

