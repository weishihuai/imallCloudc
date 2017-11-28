
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
 * 实体类 购物车持久化
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_shopping_cart_store" )
public class ShoppingCartStore extends BaseEntity<Long>{

	public static final String CART_TYPE_CODE = "cartTypeCode";
	public static final String CART_UNIQUE_KEY = "cartUniqueKey";
	public static final String SHOP_ID = "shopId";
	public static final String WE_CHAT_USER_ID = "weChatUserId";
	public static final String GOODS_ID = "goodsId";
	public static final String SKU_ID = "skuId";
	public static final String GOODS_BATCH_ID = "goodsBatchId";
	public static final String QUANTITY = "quantity";
	public static final String SALES_MAN_ID = "salesManId";

    /** CART_TYPE_CODE - 购物 车 类型 代码 */
    @Column(name = "CART_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String cartTypeCode;
    /** CART_UNIQUE_KEY - 购物 车 唯一 Key */
    @Column(name = "CART_UNIQUE_KEY", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String cartUniqueKey;
    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long shopId;
    /** WE_CHAT_USER_ID - 微信 用户 ID */
    @Column(name = "WE_CHAT_USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long weChatUserId;
    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long goodsId;
    /** SKU_ID - SKU ID */
    @Column(name = "SKU_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long skuId;
    /** GOODS_BATCH_ID - 批次 ID */
    @Column(name = "GOODS_BATCH_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private java.lang.Long goodsBatchId;
    /** QUANTITY - 数量 */
    @Column(name = "QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long quantity;
    /** SALES_MAN_ID - 营业员 ID */
    @Column(name = "SALES_MAN_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private java.lang.Long salesManId;

    public void setCartTypeCode(java.lang.String value) {
		this.cartTypeCode = value;
	}

    public java.lang.String getCartTypeCode() {
		return this.cartTypeCode;
	}

    public void setCartUniqueKey(java.lang.String value) {
		this.cartUniqueKey = value;
	}

    public java.lang.String getCartUniqueKey() {
		return this.cartUniqueKey;
	}

    public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

    public java.lang.Long getShopId() {
		return this.shopId;
	}

	public Long getWeChatUserId() {
		return weChatUserId;
	}

	public void setWeChatUserId(Long weChatUserId) {
		this.weChatUserId = weChatUserId;
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

    public void setGoodsBatchId(java.lang.Long value) {
		this.goodsBatchId = value;
	}

    public java.lang.Long getGoodsBatchId() {
		return this.goodsBatchId;
	}

    public void setQuantity(java.lang.Long value) {
		this.quantity = value;
	}

    public java.lang.Long getQuantity() {
		return this.quantity;
	}

    public void setSalesManId(java.lang.Long value) {
		this.salesManId = value;
	}

    public java.lang.Long getSalesManId() {
		return this.salesManId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("CART_TYPE_CODE",getCartTypeCode())
			.append("CART_UNIQUE_KEY",getCartUniqueKey())
			.append("SHOP_ID",getShopId())
			.append("WE_CHAT_USER_ID",getWeChatUserId())
			.append("GOODS_ID",getGoodsId())
			.append("SKU_ID",getSkuId())
			.append("GOODS_BATCH_ID",getGoodsBatchId())
			.append("QUANTITY",getQuantity())
			.append("SALES_MAN_ID",getSalesManId())
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
		if(!(obj instanceof ShoppingCartStore)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		ShoppingCartStore other = (ShoppingCartStore)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

