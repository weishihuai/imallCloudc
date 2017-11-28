
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
@Table(name = "t_shp_stock_check" )
public class StockCheck extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String GOODS_ID = "goodsId";
	public static final String SKU_ID = "skuId";
	public static final String GOODS_BATCH_ID = "goodsBatchId";
	public static final String CHECK_ORDER_NUM = "checkOrderNum";
	public static final String CURRENT_STOCK = "currentStock";
	public static final String REAL_CHECK_QUANTITY = "realCheckQuantity";
	public static final String REAL_CHECK_AMOUNT = "realCheckAmount";
	public static final String LOSS_QUANTITY = "lossQuantity";
	public static final String LOSS_AMOUNT = "lossAmount";
	public static final String CHECKED_STATE_CODE = "checkedStateCode";
	public static final String IS_POSTING = "isPosting";
	public static final String OPERATION_TIME = "operationTime";
	public static final String OPERATOR_ID = "operatorId";
	public static final String GOODS_NM = "goodsNm";
	public static final String GOODS_COMMON_NM = "goodsCommonNm";
	public static final String GOODS_CODE = "goodsCode";
	public static final String PINYIN = "pinyin";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsId;
    /** SKU_ID - SKU ID */
    @Column(name = "SKU_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long skuId;
    /** GOODS_BATCH_ID - 商品 批次 ID */
    @Column(name = "GOODS_BATCH_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsBatchId;
    /** CHECK_ORDER_NUM - 盘点 单号 */
    @Column(name = "CHECK_ORDER_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String checkOrderNum;
    /** CURRENT_STOCK - 当前 库存 */
    @Column(name = "CURRENT_STOCK", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long currentStock;
    /** REAL_CHECK_QUANTITY - 实盘 数量 */
    @Column(name = "REAL_CHECK_QUANTITY", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long realCheckQuantity;
    /** REAL_CHECK_AMOUNT - 实盘 金额 */
    @Column(name = "REAL_CHECK_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Double realCheckAmount;
    /** LOSS_QUANTITY - 损益 数量 */
    @Column(name = "LOSS_QUANTITY", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long lossQuantity;
    /** LOSS_AMOUNT - 损益 金额 */
    @Column(name = "LOSS_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Double lossAmount;
    /** CHECKED_STATE_CODE - 盘点 状态 代码 */
    @Column(name = "CHECKED_STATE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String checkedStateCode;
    /** IS_POSTING - 是否 已过帐 */
    @Column(name = "IS_POSTING", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isPosting;
    /** OPERATION_TIME - 操作 时间 */
    @Column(name = "OPERATION_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date operationTime;
    /** OPERATOR_ID - 操作人 ID */
    @Column(name = "OPERATOR_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long operatorId;
	/** GOODS_NM - 商品 名称 */
	@Column(name = "GOODS_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
	private java.lang.String goodsNm;
	/** GOODS_COMMON_NM - 商品 通用 名称 */
	@Column(name = "GOODS_COMMON_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
	private java.lang.String goodsCommonNm;
	/** GOODS_CODE - 商品 编码 */
	@Column(name = "GOODS_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String goodsCode;
	/** PINYIN - 拼音 */
	@Column(name = "PINYIN", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
	private java.lang.String pinyin;

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

    public void setGoodsBatchId(Long value) {
		this.goodsBatchId = value;
	}

    public Long getGoodsBatchId() {
		return this.goodsBatchId;
	}

    public void setCheckOrderNum(String value) {
		this.checkOrderNum = value;
	}

    public String getCheckOrderNum() {
		return this.checkOrderNum;
	}

    public void setCurrentStock(Long value) {
		this.currentStock = value;
	}

    public Long getCurrentStock() {
		return this.currentStock;
	}

    public void setRealCheckQuantity(Long value) {
		this.realCheckQuantity = value;
	}

    public Long getRealCheckQuantity() {
		return this.realCheckQuantity;
	}

    public void setRealCheckAmount(Double value) {
		this.realCheckAmount = value;
	}

    public Double getRealCheckAmount() {
		return this.realCheckAmount;
	}

    public void setLossQuantity(Long value) {
		this.lossQuantity = value;
	}

    public Long getLossQuantity() {
		return this.lossQuantity;
	}

    public void setLossAmount(Double value) {
		this.lossAmount = value;
	}

    public Double getLossAmount() {
		return this.lossAmount;
	}

    public void setCheckedStateCode(String value) {
		this.checkedStateCode = value;
	}

    public String getCheckedStateCode() {
		return this.checkedStateCode;
	}

    public void setIsPosting(String value) {
		this.isPosting = value;
	}

    public String getIsPosting() {
		return this.isPosting;
	}

    public void setOperationTime(java.util.Date value) {
		this.operationTime = value;
	}

    public java.util.Date getOperationTime() {
		return this.operationTime;
	}

    public void setOperatorId(Long value) {
		this.operatorId = value;
	}

    public Long getOperatorId() {
		return this.operatorId;
	}

	public String getGoodsNm() {
		return goodsNm;
	}

	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}

	public String getGoodsCommonNm() {
		return goodsCommonNm;
	}

	public void setGoodsCommonNm(String goodsCommonNm) {
		this.goodsCommonNm = goodsCommonNm;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("GOODS_ID",getGoodsId())
			.append("SKU_ID",getSkuId())
			.append("GOODS_BATCH_ID",getGoodsBatchId())
			.append("CHECK_ORDER_NUM",getCheckOrderNum())
			.append("CURRENT_STOCK",getCurrentStock())
			.append("REAL_CHECK_QUANTITY",getRealCheckQuantity())
			.append("REAL_CHECK_AMOUNT",getRealCheckAmount())
			.append("LOSS_QUANTITY",getLossQuantity())
			.append("LOSS_AMOUNT",getLossAmount())
			.append("CHECKED_STATE_CODE",getCheckedStateCode())
			.append("IS_POSTING",getIsPosting())
			.append("OPERATION_TIME",getOperationTime())
			.append("OPERATOR_ID",getOperatorId())
			.append("CREATE_DATE",getCreateDate())
			.append("CREATE_BY",getCreateBy())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("VERSION",getVersion())
			.append("GOODS_NM",getGoodsNm())
			.append("GOODS_COMMON_NM",getGoodsCommonNm())
			.append("GOODS_CODE",getGoodsCode())
			.append("PINYIN",getPinyin())
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
		if(!(obj instanceof StockCheck)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		StockCheck other = (StockCheck)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getOperationTimeString() {
		return DateTimeUtils.convertDateToString(this.getOperationTime());
	}

	public void setOperationTimeString(String value) throws ParseException {
		this.setOperationTime(DateTimeUtils.convertStringToDate(value));
	}

}

