
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
@Table(name = "t_shp_decoration_recommend" )
public class DecorationRecommend extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String DECORATION_GROUP_ID = "decorationGroupId";
	public static final String GOODS_ID = "goodsId";
	public static final String SKU_ID = "skuId";
	public static final String DISPLAY_POSITION = "displayPosition";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** DECORATION_GROUP_ID - 装修 分组 ID */
    @Column(name = "DECORATION_GROUP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long decorationGroupId;
    /** GOODS_ID - 商品 ID */
    @Column(name = "GOODS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long goodsId;
    /** SKU_ID - SKU ID */
    @Column(name = "SKU_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long skuId;
    /** DISPLAY_POSITION - 显示 排序 */
    @Column(name = "DISPLAY_POSITION", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long displayPosition;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setDecorationGroupId(Long value) {
		this.decorationGroupId = value;
	}

    public Long getDecorationGroupId() {
		return this.decorationGroupId;
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

    public void setDisplayPosition(Long value) {
		this.displayPosition = value;
	}

    public Long getDisplayPosition() {
		return this.displayPosition;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("DECORATION_GROUP_ID",getDecorationGroupId())
			.append("GOODS_ID",getGoodsId())
			.append("SKU_ID",getSkuId())
			.append("DISPLAY_POSITION",getDisplayPosition())
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
		if(!(obj instanceof DecorationRecommend)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		DecorationRecommend other = (DecorationRecommend)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

