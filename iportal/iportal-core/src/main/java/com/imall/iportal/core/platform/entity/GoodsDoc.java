
package com.imall.iportal.core.platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;

/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_ptfm_goods_doc" )
public class GoodsDoc extends BaseEntity<Long>{

	public static final String GOODS_CODE = "goodsCode";
	public static final String GOODS_NM = "goodsNm";
	public static final String GOODS_CATEGORY_ID = "goodsCategoryId";
	public static final String BRAND_NM = "brandNm";
	public static final String GOODS_TYPE_CODE = "goodsTypeCode";
	public static final String COMMON_NM = "commonNm";
	public static final String SPEC = "spec";
	public static final String UNIT = "unit";
	public static final String TOXICOLOGY_CODE = "toxicologyCode";
	public static final String STORAGE_CONDITION = "storageCondition";
	public static final String PRODUCE_MANUFACTURER = "produceManufacturer";
	public static final String INSTRUCTIONS = "instructions";
	public static final String MEDICATION_GUIDE = "medicationGuide";
	public static final String IS_DELETE = "isDelete";
	public static final String DISPLAY_POSITION = "displayPosition";
	public static final String PINYIN = "pinyin";
	public static final String APPROVE_STATE_CODE = "approveStateCode";

    /** GOODS_CODE - 商品 编码 */
    @Column(name = "GOODS_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String goodsCode;
    /** GOODS_NM - 商品 名称 */
    @Column(name = "GOODS_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String goodsNm;
    /** GOODS_CATEGORY_ID - 商品 分类 ID */
    @Column(name = "GOODS_CATEGORY_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsCategoryId;
    /** BRAND_NM - 品牌 名称 */
    @Column(name = "BRAND_NM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String brandNm;
	/** BAR_CODE - 条形码 */
	@Column(name = "BAR_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private String barCode;

	/** GOODS_TYPE_CODE - 商品 类型 代码 */
    @Column(name = "GOODS_TYPE_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String goodsTypeCode;
    /** COMMON_NM - 通用 名称 */
    @Column(name = "COMMON_NM", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String commonNm;
    /** SPEC - 规格 */
    @Column(name = "SPEC", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String spec;
    /** UNIT - 单位 */
    @Column(name = "UNIT", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String unit;
    /** TOXICOLOGY_CODE - 毒理 代码 */
    @Column(name = "TOXICOLOGY_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String toxicologyCode;
    /** STORAGE_CONDITION - 储存 条件 代码 */
    @Column(name = "STORAGE_CONDITION", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String storageCondition;
    /** PRODUCE_MANUFACTURER - 生产 厂商 */
    @Column(name = "PRODUCE_MANUFACTURER", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String produceManufacturer;
	/** INSTRUCTIONS - 说明书 */
	@Column(name = "INSTRUCTIONS", unique = false, nullable = true, insertable = true, updatable = true, length = 16777215)
	private byte[] instructions;
	/** MEDICATION_GUIDE - 用药 指导 */
	@Column(name = "MEDICATION_GUIDE", unique = false, nullable = true, insertable = true, updatable = true, length = 16777215)
	private byte[] medicationGuide;
    /** IS_DELETE - 是否 删除 */
    @Column(name = "IS_DELETE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isDelete;
    /** DISPLAY_POSITION - 排序 */
    @Column(name = "DISPLAY_POSITION", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long displayPosition;
    /** PINYIN - 拼音 */
    @Column(name = "PINYIN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String pinyin;
    /** APPROVE_STATE_CODE - 审核 状态 代码 */
    @Column(name = "APPROVE_STATE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String approveStateCode;

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

    public void setGoodsCategoryId(Long value) {
		this.goodsCategoryId = value;
	}

    public Long getGoodsCategoryId() {
		return this.goodsCategoryId;
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

	public void setIsDelete(String value) {
		this.isDelete = value;
	}

    public String getIsDelete() {
		return this.isDelete;
	}

    public void setDisplayPosition(Long value) {
		this.displayPosition = value;
	}

    public Long getDisplayPosition() {
		return this.displayPosition;
	}

    public void setPinyin(String value) {
		this.pinyin = value;
	}

    public String getPinyin() {
		return this.pinyin;
	}

    public void setApproveStateCode(String value) {
		this.approveStateCode = value;
	}

    public String getApproveStateCode() {
		return this.approveStateCode;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("GOODS_CODE",getGoodsCode())
			.append("GOODS_NM",getGoodsNm())
			.append("GOODS_CATEGORY_ID",getGoodsCategoryId())
			.append("BRAND_NM",getBrandNm())
			.append("BAR_CODE",getBarCode())
			.append("GOODS_TYPE_CODE",getGoodsTypeCode())
			.append("COMMON_NM",getCommonNm())
			.append("SPEC",getSpec())
			.append("UNIT",getUnit())
			.append("TOXICOLOGY_CODE",getToxicologyCode())
			.append("STORAGE_CONDITION",getStorageCondition())
			.append("PRODUCE_MANUFACTURER",getProduceManufacturer())
			.append("INSTRUCTIONS",getInstructions())
			.append("MEDICATION_GUIDE",getMedicationGuide())
			.append("IS_DELETE",getIsDelete())
			.append("DISPLAY_POSITION",getDisplayPosition())
			.append("PINYIN",getPinyin())
			.append("APPROVE_STATE_CODE",getApproveStateCode())
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
		if(!(obj instanceof GoodsDoc)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		GoodsDoc other = (GoodsDoc)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
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

	public String getCreateDateString() {
		return DateTimeUtils.convertDateToString(getCreateDate());
	}

	public void setCreateDateString(String createDate) {
		try {
			if(StringUtils.isNotBlank(createDate)){
				setCreateDate(DateTimeUtils.convertStringToDate(createDate));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}

