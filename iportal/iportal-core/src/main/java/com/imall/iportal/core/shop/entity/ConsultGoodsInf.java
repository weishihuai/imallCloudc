
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
@Table(name = "t_shp_consult_goods_inf" )
public class ConsultGoodsInf extends BaseEntity<Long>{

	public static final String CONSULT_SERVICE_ID = "consultServiceId";
	public static final String GOODS_ID = "goodsId";

    /** CONSULT_SERVICE_ID - 咨询 服务 ID */
    @Column(name = "CONSULT_SERVICE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long consultServiceId;
    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long goodsId;

    public void setConsultServiceId(java.lang.Long value) {
		this.consultServiceId = value;
	}

    public java.lang.Long getConsultServiceId() {
		return this.consultServiceId;
	}

    public void setGoodsId(java.lang.Long value) {
		this.goodsId = value;
	}

    public java.lang.Long getGoodsId() {
		return this.goodsId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("CONSULT_SERVICE_ID",getConsultServiceId())
			.append("GOODS_ID",getGoodsId())
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
		if(!(obj instanceof ConsultGoodsInf)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		ConsultGoodsInf other = (ConsultGoodsInf)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

