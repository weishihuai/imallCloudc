
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
@Table(name = "t_shp_order_item" )
public class OrderItem extends BaseEntity<Long>{

	public static final String ORDER_ID = "orderId";
	public static final String GOODS_ID = "goodsId";
	public static final String SKU_ID = "skuId";
	public static final String GOODS_CODING = "goodsCoding";
	public static final String COMMON_NM = "commonNm";
	public static final String GOODS_PINYIN = "goodsPinyin";
	public static final String GOODS_NM = "goodsNm";
	public static final String PRODUCE_MANUFACTURER = "produceManufacturer";
	public static final String SPEC = "spec";
	public static final String UNIT = "unit";
	public static final String QUANTITY = "quantity";
	public static final String GOODS_UNIT_PRICE = "goodsUnitPrice";
	public static final String GOODS_COST_TOTAL_AMOUNT = "goodsCostTotalAmount";
	public static final String GOODS_TOTAL_AMOUNT = "goodsTotalAmount";
	public static final String REMARK = "remark";
	public static final String SELLER = "seller";

    /** ORDER_ID - 订单 ID */
    @Column(name = "ORDER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long orderId;
    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsId;
    /** SKU_ID - SKU ID */
    @Column(name = "SKU_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long skuId;
    /** GOODS_CODING - 商品 编码 */
    @Column(name = "GOODS_CODING", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String goodsCoding;
    /** COMMON_NM - 通用 名称 */
    @Column(name = "COMMON_NM", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String commonNm;
	/** GOODS_PINYIN - 通用 名称 首字母*/
	@Column(name = "GOODS_PINYIN", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	private String goodsPinyin;
    /** GOODS_NM - 商品 名称 */
	@Column(name = "GOODS_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String goodsNm;
	/** PRODUCE_MANUFACTURER - 生产 厂商 */
	@Column(name = "PRODUCE_MANUFACTURER", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	private String produceManufacturer;
    /** SPAN - 规格 */
	@Column(name = "SPEC", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private String spec;
    /** UNIT - 单位 */
    @Column(name = "UNIT", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String unit;

    /** QUANTITY - 数量 */
    @Column(name = "QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    private Long quantity;
    /** GOODS_UNIT_PRICE - 商品 单位 价格 */
    @Column(name = "GOODS_UNIT_PRICE", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double goodsUnitPrice;
    /** GOODS_COST_TOTAL_AMOUNT - 商品 成本 总 金额 */
    @Column(name = "GOODS_COST_TOTAL_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double goodsCostTotalAmount;
    /** GOODS_TOTAL_AMOUNT - 商品 总 金额 */
    @Column(name = "GOODS_TOTAL_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double goodsTotalAmount;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String remark;
    /** SELLER - 营业员 */
    @Column(name = "SELLER", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long seller;

    public void setOrderId(Long value) {
		this.orderId = value;
	}

    public Long getOrderId() {
		return this.orderId;
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

    public void setGoodsCoding(String value) {
		this.goodsCoding = value;
	}

    public String getGoodsCoding() {
		return this.goodsCoding;
	}

    public void setCommonNm(String value) {
		this.commonNm = value;
	}

    public String getCommonNm() {
		return this.commonNm;
	}

	public String getGoodsPinyin() {
		return goodsPinyin;
	}

	public void setGoodsPinyin(String goodsPinyin) {
		this.goodsPinyin = goodsPinyin;
	}

	public void setGoodsNm(String value) {
		this.goodsNm = value;
	}

    public String getGoodsNm() {
		return this.goodsNm;
	}

	public String getProduceManufacturer() {
		return produceManufacturer;
	}

	public void setProduceManufacturer(String produceManufacturer) {
		this.produceManufacturer = produceManufacturer;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public void setUnit(String value) {
		this.unit = value;
	}

    public String getUnit() {
		return this.unit;
	}

    public void setQuantity(Long value) {
		this.quantity = value;
	}

    public Long getQuantity() {
		return this.quantity;
	}

    public void setGoodsUnitPrice(Double value) {
		this.goodsUnitPrice = value;
	}

    public Double getGoodsUnitPrice() {
		return this.goodsUnitPrice;
	}

	public Double getGoodsCostTotalAmount() {
		return goodsCostTotalAmount;
	}

	public void setGoodsCostTotalAmount(Double goodsCostTotalAmount) {
		this.goodsCostTotalAmount = goodsCostTotalAmount;
	}

	public void setGoodsTotalAmount(Double value) {
		this.goodsTotalAmount = value;
	}

    public Double getGoodsTotalAmount() {
		return this.goodsTotalAmount;
	}

    public void setRemark(String value) {
		this.remark = value;
	}

    public String getRemark() {
		return this.remark;
	}

    public void setSeller(Long value) {
		this.seller = value;
	}

    public Long getSeller() {
		return this.seller;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("ORDER_ID",getOrderId())
			.append("GOODS_ID",getGoodsId())
			.append("SKU_ID",getSkuId())
			.append("GOODS_CODING",getGoodsCoding())
			.append("COMMON_NM",getCommonNm())
			.append("GOODS_PINYIN",getGoodsPinyin())
			.append("GOODS_NM",getGoodsNm())
			.append("PRODUCE_MANUFACTURER",getProduceManufacturer())
			.append("SPEC",getSpec())
			.append("UNIT",getUnit())
			.append("QUANTITY",getQuantity())
			.append("GOODS_UNIT_PRICE",getGoodsUnitPrice())
			.append("GOODS_COST_TOTAL_AMOUNT",getGoodsCostTotalAmount())
			.append("GOODS_TOTAL_AMOUNT",getGoodsTotalAmount())
			.append("REMARK",getRemark())
			.append("SELLER",getSeller())
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
		if(!(obj instanceof OrderItem)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		OrderItem other = (OrderItem)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

