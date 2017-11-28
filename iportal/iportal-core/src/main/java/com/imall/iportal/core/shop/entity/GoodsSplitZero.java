
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
@Table(name = "t_shp_goods_split_zero" )
public class GoodsSplitZero extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String GOODS_ID = "goodsId";
	public static final String SKU_ID = "skuId";
	public static final String GOODS_BATCH_ID = "goodsBatchId";
	public static final String SPLIT_ZERO_QUANTITY = "splitZeroQuantity";
	public static final String SPLIT_SMALL_PACKAGE_QUANTITY = "splitSmallPackageQuantity";
	public static final String USAGE_TEXT = "usageText";
	public static final String DOSAGE = "dosage";
	public static final String OPERATOR = "operator";
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
    /** SPLIT_ZERO_QUANTITY - 拆零 数量 */
    @Column(name = "SPLIT_ZERO_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long splitZeroQuantity;
    /** SPLIT_SMALL_PACKAGE_QUANTITY - 拆后 小 包装 数量 */
    @Column(name = "SPLIT_SMALL_PACKAGE_QUANTITY", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long splitSmallPackageQuantity;
    /** USAGE_TEXT - 用法 */
    @Column(name = "USAGE_TEXT", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String usageText;
    /** DOSAGE - 用量 */
    @Column(name = "DOSAGE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String dosage;
    /** OPERATOR - 经办人 */
    @Column(name = "OPERATOR", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String operator;
    /** GOODS_NM - 商品 名称 */
    @Column(name = "GOODS_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String goodsNm;
    /** GOODS_COMMON_NM - 商品 通用 名称 */
    @Column(name = "GOODS_COMMON_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String goodsCommonNm;
    /** GOODS_CODE - 商品 编码 */
    @Column(name = "GOODS_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String goodsCode;
    /** PINYIN - 拼音 */
    @Column(name = "PINYIN", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String pinyin;

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

    public void setSplitZeroQuantity(Long value) {
		this.splitZeroQuantity = value;
	}

    public Long getSplitZeroQuantity() {
		return this.splitZeroQuantity;
	}

    public void setSplitSmallPackageQuantity(Long value) {
		this.splitSmallPackageQuantity = value;
	}

    public Long getSplitSmallPackageQuantity() {
		return this.splitSmallPackageQuantity;
	}

    public void setUsageText(String value) {
		this.usageText = value;
	}

    public String getUsageText() {
		return this.usageText;
	}

    public void setDosage(String value) {
		this.dosage = value;
	}

    public String getDosage() {
		return this.dosage;
	}

    public void setOperator(String value) {
		this.operator = value;
	}

    public String getOperator() {
		return this.operator;
	}

    public void setGoodsNm(String value) {
		this.goodsNm = value;
	}

    public String getGoodsNm() {
		return this.goodsNm;
	}

    public void setGoodsCommonNm(String value) {
		this.goodsCommonNm = value;
	}

    public String getGoodsCommonNm() {
		return this.goodsCommonNm;
	}

    public void setGoodsCode(String value) {
		this.goodsCode = value;
	}

    public String getGoodsCode() {
		return this.goodsCode;
	}

    public void setPinyin(String value) {
		this.pinyin = value;
	}

    public String getPinyin() {
		return this.pinyin;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("GOODS_ID",getGoodsId())
			.append("SKU_ID",getSkuId())
			.append("GOODS_BATCH_ID",getGoodsBatchId())
			.append("SPLIT_ZERO_QUANTITY",getSplitZeroQuantity())
			.append("SPLIT_SMALL_PACKAGE_QUANTITY",getSplitSmallPackageQuantity())
			.append("USAGE_TEXT",getUsageText())
			.append("DOSAGE",getDosage())
			.append("OPERATOR",getOperator())
			.append("GOODS_NM",getGoodsNm())
			.append("GOODS_COMMON_NM",getGoodsCommonNm())
			.append("GOODS_CODE",getGoodsCode())
			.append("PINYIN",getPinyin())
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
		if(!(obj instanceof GoodsSplitZero)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		GoodsSplitZero other = (GoodsSplitZero)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

