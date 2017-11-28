
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
@Table(name = "t_ptfm_goods_doc_cosmetic" )
public class GoodsDocCosmetic extends BaseEntity<Long>{

	public static final String GOODS_DOC_ID = "goodsDocId";
	public static final String APPROVAL_NUMBER = "approvalNumber";
	public static final String MANUFACTURER_ADDR = "manufacturerAddr";

    /** GOODS_DOC_ID - 商品 档案 ID */
    @Column(name = "GOODS_DOC_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsDocId;
    /** APPROVAL_NUMBER - 批准文号 */
    @Column(name = "APPROVAL_NUMBER", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String approvalNumber;
    /** MANUFACTURER_ADDR - 厂家 地址 */
    @Column(name = "MANUFACTURER_ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String manufacturerAddr;

    public void setGoodsDocId(Long value) {
		this.goodsDocId = value;
	}

    public Long getGoodsDocId() {
		return this.goodsDocId;
	}

    public void setApprovalNumber(String value) {
		this.approvalNumber = value;
	}

    public String getApprovalNumber() {
		return this.approvalNumber;
	}

    public void setManufacturerAddr(String value) {
		this.manufacturerAddr = value;
	}

    public String getManufacturerAddr() {
		return this.manufacturerAddr;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("GOODS_DOC_ID",getGoodsDocId())
			.append("APPROVAL_NUMBER",getApprovalNumber())
			.append("MANUFACTURER_ADDR",getManufacturerAddr())
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
		if(!(obj instanceof GoodsDocCosmetic)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		GoodsDocCosmetic other = (GoodsDocCosmetic)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

