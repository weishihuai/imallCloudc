
package com.imall.iportal.core.shop.entity;

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
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_goods_food_health" )
public class GoodsFoodHealth extends BaseEntity<Long>{

	public static final String GOODS_ID = "goodsId";
	public static final String FOOD_HYGIENE_LICENCE_NUM = "foodHygieneLicenceNum";
	public static final String PRODUCTION_DATE = "productionDate";
	public static final String EXPIRATION_DATE = "expirationDate";
	public static final String HEALTH_CARE_FUNC = "healthCareFunc";
	public static final String APPROPRIATE_CROWD = "appropriateCrowd";
	public static final String EDIBLE_METHOD_AND_DOSAGE = "edibleMethodAndDosage";
	public static final String STORAGE_METHOD = "storageMethod";
	public static final String EXEC_STANDARD = "execStandard";
	public static final String EFFECT_COMPOSITION = "effectComposition";
	public static final String NOTICE = "notice";
	public static final String NOT_APPROPRIATE_CROWD = "notAppropriateCrowd";

    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long goodsId;
    /** FOOD_HYGIENE_LICENCE_NUM - 食品 卫生 许可证 号码 */
    @Column(name = "FOOD_HYGIENE_LICENCE_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private java.lang.String foodHygieneLicenceNum;
    /** PRODUCTION_DATE - 生产 日期 */
    @Column(name = "PRODUCTION_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private java.util.Date productionDate;
    /** EXPIRATION_DATE - 保质期 */
    @Column(name = "EXPIRATION_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date expirationDate;
    /** HEALTH_CARE_FUNC - 保健 功能 */
    @Column(name = "HEALTH_CARE_FUNC", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private java.lang.String healthCareFunc;
    /** APPROPRIATE_CROWD - 适宜 人群 */
    @Column(name = "APPROPRIATE_CROWD", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private java.lang.String appropriateCrowd;
	/** NOT_APPROPRIATE_CROWD - 不 适宜 人群 */
	@Column(name = "NOT_APPROPRIATE_CROWD", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	private java.lang.String notAppropriateCrowd;
    /** EDIBLE_METHOD_AND_DOSAGE - 食用 方法 及 用量 */
    @Column(name = "EDIBLE_METHOD_AND_DOSAGE", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private java.lang.String edibleMethodAndDosage;
    /** STORAGE_METHOD - 贮藏 方法 */
    @Column(name = "STORAGE_METHOD", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private java.lang.String storageMethod;
    /** EXEC_STANDARD - 执行 标准 */
    @Column(name = "EXEC_STANDARD", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private java.lang.String execStandard;
    /** EFFECT_COMPOSITION - 功效 成分 */
    @Column(name = "EFFECT_COMPOSITION", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private java.lang.String effectComposition;
    /** NOTICE - 注意 事项 */
    @Column(name = "NOTICE", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
    private java.lang.String notice;

    public void setGoodsId(java.lang.Long value) {
		this.goodsId = value;
	}

    public java.lang.Long getGoodsId() {
		return this.goodsId;
	}

    public void setFoodHygieneLicenceNum(java.lang.String value) {
		this.foodHygieneLicenceNum = value;
	}

    public java.lang.String getFoodHygieneLicenceNum() {
		return this.foodHygieneLicenceNum;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public void setExpirationDate(java.util.Date value) {
		this.expirationDate = value;
	}

    public java.util.Date getExpirationDate() {
		return this.expirationDate;
	}

	public String getHealthCareFunc() {
		return healthCareFunc;
	}

	public void setHealthCareFunc(String healthCareFunc) {
		this.healthCareFunc = healthCareFunc;
	}

	public String getEdibleMethodAndDosage() {
		return edibleMethodAndDosage;
	}

    public String getNotAppropriateCrowd() {
        return notAppropriateCrowd;
    }

    public void setNotAppropriateCrowd(String notAppropriateCrowd) {
        this.notAppropriateCrowd = notAppropriateCrowd;
    }

    public void setEdibleMethodAndDosage(String edibleMethodAndDosage) {
		this.edibleMethodAndDosage = edibleMethodAndDosage;
	}

	public void setAppropriateCrowd(java.lang.String value) {
		this.appropriateCrowd = value;
	}

    public java.lang.String getAppropriateCrowd() {
		return this.appropriateCrowd;
	}


    public void setStorageMethod(java.lang.String value) {
		this.storageMethod = value;
	}

    public java.lang.String getStorageMethod() {
		return this.storageMethod;
	}

    public void setExecStandard(java.lang.String value) {
		this.execStandard = value;
	}

    public java.lang.String getExecStandard() {
		return this.execStandard;
	}

    public void setEffectComposition(java.lang.String value) {
		this.effectComposition = value;
	}

    public java.lang.String getEffectComposition() {
		return this.effectComposition;
	}

    public void setNotice(java.lang.String value) {
		this.notice = value;
	}

    public java.lang.String getNotice() {
		return this.notice;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("GOODS_ID",getGoodsId())
			.append("FOOD_HYGIENE_LICENCE_NUM",getFoodHygieneLicenceNum())
			.append("PRODUCTION_DATE",getProductionDate())
			.append("EXPIRATION_DATE",getExpirationDate())
			.append("HEALTH_CARE_FUNC",getHealthCareFunc())
			.append("APPROPRIATE_CROWD",getAppropriateCrowd())
			.append("NOT_APPROPRIATE_CROWD",getNotAppropriateCrowd())
			.append("EDIBLE_METHOD_AND_DOSAGE",getEdibleMethodAndDosage())
			.append("STORAGE_METHOD",getStorageMethod())
			.append("EXEC_STANDARD",getExecStandard())
			.append("EFFECT_COMPOSITION",getEffectComposition())
			.append("NOTICE",getNotice())
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
		if(!(obj instanceof GoodsFoodHealth)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		GoodsFoodHealth other = (GoodsFoodHealth)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getProductionDateString() {
		return DateTimeUtils.convertDateToString(getProductionDate());
	}
	public void setProductionDateString(String value) throws ParseException {
		setProductionDate(StringUtils.isBlank(value)?null:DateTimeUtils.convertStringToDate(value));
	}

	public String getExpirationDateString() {
		return DateTimeUtils.convertDateToString(getExpirationDate());
	}
	public void setExpirationDateString(String value) throws ParseException {
		setExpirationDate(StringUtils.isBlank(value)?null:DateTimeUtils.convertStringToDate(value));
	}

}

