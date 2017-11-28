
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
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_goods_medical_instruments" )
public class GoodsMedicalInstruments extends BaseEntity<Long>{

	public static final String GOODS_ID = "goodsId";
	public static final String REG_NUM = "regNum";
	public static final String REG_REGISTRATION_FORM_NUM = "regRegistrationFormNum";
	public static final String MANUFACTURER_ADDR = "manufacturerAddr";
	public static final String APPLY_RANGE = "applyRange";
	public static final String PRODUCT_STANDARD_NUM = "productStandardNum";

    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long goodsId;
    /** REG_NUM - 注册 号码 */
    @Column(name = "REG_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private java.lang.String regNum;
    /** REG_REGISTRATION_FORM_NUM - 注册 登记表 号码 */
    @Column(name = "REG_REGISTRATION_FORM_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private java.lang.String regRegistrationFormNum;
    /** MANUFACTURER_ADDR - 厂家 地址 */
    @Column(name = "MANUFACTURER_ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String manufacturerAddr;
    /** APPLY_RANGE - 适用 范围 */
    @Column(name = "APPLY_RANGE", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
    private java.lang.String applyRange;
    /** PRODUCT_STANDARD_NUM - 产品 标准 号码 */
    @Column(name = "PRODUCT_STANDARD_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String productStandardNum;

    public void setGoodsId(java.lang.Long value) {
		this.goodsId = value;
	}

    public java.lang.Long getGoodsId() {
		return this.goodsId;
	}

    public void setRegNum(java.lang.String value) {
		this.regNum = value;
	}

    public java.lang.String getRegNum() {
		return this.regNum;
	}

    public void setRegRegistrationFormNum(java.lang.String value) {
		this.regRegistrationFormNum = value;
	}

    public java.lang.String getRegRegistrationFormNum() {
		return this.regRegistrationFormNum;
	}

    public void setManufacturerAddr(java.lang.String value) {
		this.manufacturerAddr = value;
	}

    public java.lang.String getManufacturerAddr() {
		return this.manufacturerAddr;
	}

    public void setApplyRange(java.lang.String value) {
		this.applyRange = value;
	}

    public java.lang.String getApplyRange() {
		return this.applyRange;
	}

    public void setProductStandardNum(java.lang.String value) {
		this.productStandardNum = value;
	}

    public java.lang.String getProductStandardNum() {
		return this.productStandardNum;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("GOODS_ID",getGoodsId())
			.append("REG_NUM",getRegNum())
			.append("REG_REGISTRATION_FORM_NUM",getRegRegistrationFormNum())
			.append("MANUFACTURER_ADDR",getManufacturerAddr())
			.append("APPLY_RANGE",getApplyRange())
			.append("CREATE_DATE",getCreateDate())
			.append("CREATE_BY",getCreateBy())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("VERSION",getVersion())
			.append("PRODUCT_STANDARD_NUM",getProductStandardNum())
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
		if(!(obj instanceof GoodsMedicalInstruments)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		GoodsMedicalInstruments other = (GoodsMedicalInstruments)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

