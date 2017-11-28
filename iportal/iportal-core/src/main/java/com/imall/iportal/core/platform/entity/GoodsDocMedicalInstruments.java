
package com.imall.iportal.core.platform.entity;

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
@Table(name = "t_ptfm_goods_doc_medical_instruments" )
public class GoodsDocMedicalInstruments extends BaseEntity<Long>{

	public static final String GOODS_DOC_ID = "goodsDocId";
	public static final String REG_NUM = "regNum";
	public static final String REG_REGISTRATION_FORM_NUM = "regRegistrationFormNum";
	public static final String MANUFACTURER_ADDR = "manufacturerAddr";
	public static final String APPLY_RANGE = "applyRange";
	public static final String PRODUCT_STANDARD_NUM = "productStandardNum";

    /** GOODS_DOC_ID - 商品 档案 ID */
    @Column(name = "GOODS_DOC_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsDocId;
    /** REG_NUM - 注册 号码 */
    @Column(name = "REG_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String regNum;
    /** REG_REGISTRATION_FORM_NUM - 注册 登记表 号码 */
    @Column(name = "REG_REGISTRATION_FORM_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String regRegistrationFormNum;
    /** MANUFACTURER_ADDR - 厂家 地址 */
    @Column(name = "MANUFACTURER_ADDR", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String manufacturerAddr;
    /** APPLY_RANGE - 适用 范围 */
    @Column(name = "APPLY_RANGE", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
    private String applyRange;
    /** PRODUCT_STANDARD_NUM - 产品 标准 号码 */
    @Column(name = "PRODUCT_STANDARD_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String productStandardNum;

    public void setGoodsDocId(Long value) {
		this.goodsDocId = value;
	}

    public Long getGoodsDocId() {
		return this.goodsDocId;
	}

    public void setRegNum(String value) {
		this.regNum = value;
	}

    public String getRegNum() {
		return this.regNum;
	}

    public void setRegRegistrationFormNum(String value) {
		this.regRegistrationFormNum = value;
	}

    public String getRegRegistrationFormNum() {
		return this.regRegistrationFormNum;
	}

    public void setManufacturerAddr(String value) {
		this.manufacturerAddr = value;
	}

    public String getManufacturerAddr() {
		return this.manufacturerAddr;
	}

    public void setApplyRange(String value) {
		this.applyRange = value;
	}

    public String getApplyRange() {
		return this.applyRange;
	}

    public void setProductStandardNum(String value) {
		this.productStandardNum = value;
	}

    public String getProductStandardNum() {
		return this.productStandardNum;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("GOODS_DOC_ID",getGoodsDocId())
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
		if(!(obj instanceof GoodsDocMedicalInstruments)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		GoodsDocMedicalInstruments other = (GoodsDocMedicalInstruments)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

