
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
@Table(name = "t_shp_goods_enable_record" )
public class GoodsEnableRecord extends BaseEntity<Long>{

	public static final String GOODS_ID = "goodsId";
	public static final String OPERATION_STATE = "operationState";
	public static final String REASON = "reason";
	public static final String SHOP_ID = "shopId";

    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsId;

	/** SHOP_ID - 门店 ID */
	@Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private Long shopId;
    /** OPERATION_STATE - 操作 状态 */
    @Column(name = "OPERATION_STATE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String operationState;
    /** REASON - 原因 */
    @Column(name = "REASON", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String reason;

    public void setGoodsId(Long value) {
		this.goodsId = value;
	}

    public Long getGoodsId() {
		return this.goodsId;
	}

    public void setOperationState(String value) {
		this.operationState = value;
	}

    public String getOperationState() {
		return this.operationState;
	}

    public void setReason(String value) {
		this.reason = value;
	}

    public String getReason() {
		return this.reason;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("GOODS_ID",getGoodsId())
			.append("SHOP_ID",getShopId())
			.append("OPERATION_STATE",getOperationState())
			.append("REASON",getReason())
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
		if(!(obj instanceof GoodsEnableRecord)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		GoodsEnableRecord other = (GoodsEnableRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

