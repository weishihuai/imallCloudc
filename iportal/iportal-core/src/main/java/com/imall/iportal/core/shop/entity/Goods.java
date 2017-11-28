
package com.imall.iportal.core.shop.entity;

import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.UnsupportedEncodingException;

/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_goods" )
public class Goods extends BaseEntity<Long>{

	public static final String GOODS_DOC_ID = "goodsDocId";
	public static final String GOODS_CODE = "goodsCode";
	public static final String GOODS_NM = "goodsNm";
	public static final String SELL_CATEGORY_IDS = "sellCategoryIds";
	public static final String BRAND_NM = "brandNm";
	public static final String BAR_CODE = "barCode";
	public static final String GOODS_TYPE_CODE = "goodsTypeCode";
	public static final String COMMON_NM = "commonNm";
	public static final String SPEC = "spec";
	public static final String UNIT = "unit";
	public static final String TOXICOLOGY_CODE = "toxicologyCode";
	public static final String STORAGE_CONDITION = "storageCondition";
	public static final String PRODUCE_MANUFACTURER = "produceManufacturer";
	public static final String INSTRUCTIONS = "instructions";
	public static final String MEDICATION_GUIDE = "medicationGuide";
	public static final String SHOP_ID = "shopId";
	public static final String IS_DELETE = "isDelete";
	public static final String DISPLAY_POSITION = "displayPosition";
	public static final String IS_ENABLE = "isEnable";
	public static final String IS_FIRST_SELL = "isFirstSell";
	public static final String SUBMIT_IDEA = "submitIdea";
	public static final String REMARK = "remark";
	public static final String PINYIN = "pinyin";
	public static final String APPROVE_STATE_CODE = "approveStateCode";
	public static final String PURCHASE_TAX_RATE = "purchaseTaxRate";
	public static final String SELL_TAX_RATE = "sellTaxRate";
	public static final String SPLIT_ZERO_SOURCE_GOODS_ID = "splitZeroSourceGoodsId";

    /** GOODS_DOC_ID - 商品 档案 ID */
    @Column(name = "GOODS_DOC_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long goodsDocId;
	/** SPLIT_ZERO_SOURCE_GOODS_ID - 拆零 源 商品 ID */
	@Column(name = "SPLIT_ZERO_SOURCE_GOODS_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	private Long splitZeroSourceGoodsId;
    /** GOODS_CODE - 商品 编码 */
    @Column(name = "GOODS_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String goodsCode;
    /** GOODS_NM - 商品 名称 */
    @Column(name = "GOODS_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String goodsNm;
    /** SELL_CATEGORY_IDS - 销售 分类 IDS */
    @Column(name = "SELL_CATEGORY_IDS", unique = false, nullable = false, insertable = true, updatable = true, length = 1024)
    private String sellCategoryIds;
    /** BRAND_NM - 品牌 名称 */
    @Column(name = "BRAND_NM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private String brandNm;
	/** BAR_CODE - 条形码*/
	@Column(name = "BAR_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private String barCode;
	/** GOODS_TYPE_CODE - 商品 类型 代码 */
    @Column(name = "GOODS_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String goodsTypeCode;
    /** COMMON_NM - 通用 名称 */
    @Column(name = "COMMON_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String commonNm;
    /** SPEC - 规格 */
    @Column(name = "SPEC", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String spec;
    /** UNIT - 单位 */
    @Column(name = "UNIT", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String unit;
    /** TOXICOLOGY_CODE - 毒理 代码 */
    @Column(name = "TOXICOLOGY_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String toxicologyCode;
    /** STORAGE_CONDITION - 储存 条件 代码 */
    @Column(name = "STORAGE_CONDITION", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String storageCondition;
    /** PRODUCE_MANUFACTURER - 生产 厂商 */
    @Column(name = "PRODUCE_MANUFACTURER", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String produceManufacturer;
    /** INSTRUCTIONS - 说明书 */
    @Column(name = "INSTRUCTIONS", unique = false, nullable = true, insertable = true, updatable = true, length = 16777215)
    private byte[] instructions;
    /** MEDICATION_GUIDE - 用药 指导 */
    @Column(name = "MEDICATION_GUIDE", unique = false, nullable = true, insertable = true, updatable = true, length = 16777215)
    private byte[] medicationGuide;
    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** IS_DELETE - 是否 删除 */
    @Column(name = "IS_DELETE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isDelete;
    /** DISPLAY_POSITION - 排序 */
    @Column(name = "DISPLAY_POSITION", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long displayPosition;
    /** IS_ENABLE - 药品状态 */
    @Column(name = "IS_ENABLE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isEnable;
    /** IS_FIRST_SELL - 是否 首营 */
    @Column(name = "IS_FIRST_SELL", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isFirstSell;
    /** SUBMIT_IDEA - 提交 意见 */
    @Column(name = "SUBMIT_IDEA", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String submitIdea;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String remark;
    /** PINYIN - 拼音 */
    @Column(name = "PINYIN", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String pinyin;
    /** APPROVE_STAT_CODE - 审核 状态 代码 */
    @Column(name = "APPROVE_STATE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String approveStateCode;
	/** PURCHASE_TAX_RATE - 采购 税率 */
	@Column(name = "PURCHASE_TAX_RATE", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	private java.lang.Double purchaseTaxRate;
	/** SELL_TAX_RATE - 销售 税率 */
	@Column(name = "SELL_TAX_RATE", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	private java.lang.Double sellTaxRate;

    public void setGoodsDocId(Long value) {
		this.goodsDocId = value;
	}

    public Long getGoodsDocId() {
		return this.goodsDocId;
	}

    public void setGoodsCode(String value) {
		this.goodsCode = value;
	}

    public String getGoodsCode() {
		return this.goodsCode;
	}

    public void setGoodsNm(String value) {
		this.goodsNm = value;
	}

    public String getGoodsNm() {
		return this.goodsNm;
	}

    public void setSellCategoryIds(String value) {
		this.sellCategoryIds = value;
	}

    public String getSellCategoryIds() {
		return this.sellCategoryIds;
	}

    public void setBrandNm(String value) {
		this.brandNm = value;
	}

    public String getBrandNm() {
		return this.brandNm;
	}

    public void setGoodsTypeCode(String value) {
		this.goodsTypeCode = value;
	}

    public String getGoodsTypeCode() {
		return this.goodsTypeCode;
	}

    public void setCommonNm(String value) {
		this.commonNm = value;
	}

    public String getCommonNm() {
		return this.commonNm;
	}

    public void setSpec(String value) {
		this.spec = value;
	}

    public String getSpec() {
		return this.spec;
	}

    public void setUnit(String value) {
		this.unit = value;
	}

    public String getUnit() {
		return this.unit;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public void setToxicologyCode(String value) {
		this.toxicologyCode = value;
	}

    public String getToxicologyCode() {
		return this.toxicologyCode;
	}

    public void setStorageCondition(String value) {
		this.storageCondition = value;
	}

    public String getStorageCondition() {
		return this.storageCondition;
	}

    public void setProduceManufacturer(String value) {
		this.produceManufacturer = value;
	}

    public String getProduceManufacturer() {
		return this.produceManufacturer;
	}

	public byte[] getInstructions() {
		return instructions;
	}

	public void setInstructions(byte[] instructions) {
		this.instructions = instructions;
	}

	public byte[] getMedicationGuide() {
		return medicationGuide;
	}

	public void setMedicationGuide(byte[] medicationGuide) {
		this.medicationGuide = medicationGuide;
	}

	public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setIsDelete(String value) {
		this.isDelete = value;
	}

    public String getIsDelete() {
		return this.isDelete;
	}

    public void setDisplayPosition(Long value) {
		this.displayPosition = value;
	}

	public Double getPurchaseTaxRate() {
		return purchaseTaxRate;
	}

	public void setPurchaseTaxRate(Double purchaseTaxRate) {
		this.purchaseTaxRate = purchaseTaxRate;
	}

	public Double getSellTaxRate() {
		return sellTaxRate;
	}

	public void setSellTaxRate(Double sellTaxRate) {
		this.sellTaxRate = sellTaxRate;
	}

	public Long getDisplayPosition() {
		return this.displayPosition;
	}

    public void setIsEnable(String value) {
		this.isEnable = value;
	}

    public String getIsEnable() {
		return this.isEnable;
	}

    public void setIsFirstSell(String value) {
		this.isFirstSell = value;
	}

    public String getIsFirstSell() {
		return this.isFirstSell;
	}

    public void setSubmitIdea(String value) {
		this.submitIdea = value;
	}

    public String getSubmitIdea() {
		return this.submitIdea;
	}

    public void setRemark(String value) {
		this.remark = value;
	}

    public String getRemark() {
		return this.remark;
	}

    public void setPinyin(String value) {
		this.pinyin = value;
	}

    public String getPinyin() {
		return this.pinyin;
	}

	public String getApproveStateCode() {
		return approveStateCode;
	}

	public void setApproveStateCode(String approveStateCode) {
		this.approveStateCode = approveStateCode;
	}

	public Long getSplitZeroSourceGoodsId() {
		return splitZeroSourceGoodsId;
	}

	public void setSplitZeroSourceGoodsId(Long splitZeroSourceGoodsId) {
		this.splitZeroSourceGoodsId = splitZeroSourceGoodsId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("GOODS_DOC_ID",getGoodsDocId())
			.append("GOODS_CODE",getGoodsCode())
			.append("SPLIT_ZERO_SOURCE_GOODS_ID",getSplitZeroSourceGoodsId())
			.append("GOODS_NM",getGoodsNm())
			.append("BAR_CODE",getBarCode())
			.append("SELL_CATEGORY_IDS",getSellCategoryIds())
			.append("BRAND_NM",getBrandNm())
			.append("GOODS_TYPE_CODE",getGoodsTypeCode())
			.append("COMMON_NM",getCommonNm())
			.append("SPEC",getSpec())
			.append("UNIT",getUnit())
			.append("TOXICOLOGY_CODE",getToxicologyCode())
			.append("STORAGE_CONDITION",getStorageCondition())
			.append("PRODUCE_MANUFACTURER",getProduceManufacturer())
			.append("INSTRUCTIONS",getInstructions())
			.append("MEDICATION_GUIDE",getMedicationGuide())
			.append("SHOP_ID",getShopId())
			.append("IS_DELETE",getIsDelete())
			.append("DISPLAY_POSITION",getDisplayPosition())
			.append("IS_ENABLE",getIsEnable())
			.append("IS_FIRST_SELL",getIsFirstSell())
			.append("SUBMIT_IDEA",getSubmitIdea())
			.append("PURCHASE_TAX_RATE",getPurchaseTaxRate())
			.append("SELL_TAX_RATE",getSellTaxRate())
			.append("REMARK",getRemark())
			.append("PINYIN",getPinyin())
			.append("APPROVE_STAT_CODE",getApproveStateCode())
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

	public String getInstructionsStr() {
		try {
			return this.instructions == null ? "" : new String(this.instructions, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(); //NOSONAR
			return "";
		}
	}

	public void setInstructionsStr(String value) {
		if(value == null){
			this.instructions = null;
		}else{
			try {
				this.instructions = value.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace(); //NOSONAR
			}
		}
	}

	public String getMedicationGuideStr() {
		try {
			return this.medicationGuide == null ? "" : new String(this.medicationGuide, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(); //NOSONAR
			return "";
		}
	}

	public void setMedicationGuideStr(String value) {
		if(value == null){
			this.medicationGuide = null;
		}else{
			try {
				this.medicationGuide = value.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace(); //NOSONAR
			}
		}
	}

	
	public boolean equals(Object obj) {
		if(this.getId() == null){
			return false;
		}
		if(!(obj instanceof Goods)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		Goods other = (Goods)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

}

