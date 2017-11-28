
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
@Table(name = "t_shp_decoration_recommend_group" )
public class DecorationRecommendGroup extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String GROUP_NM = "groupNm";
	public static final String DISPLAY_POSITION = "displayPosition";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** GROUP_NM - 分组 名称 */
    @Column(name = "GROUP_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String groupNm;
    /** DISPLAY_POSITION - 显示 排序 */
    @Column(name = "DISPLAY_POSITION", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long displayPosition;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setGroupNm(String value) {
		this.groupNm = value;
	}

    public String getGroupNm() {
		return this.groupNm;
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
			.append("GROUP_NM",getGroupNm())
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
		if(!(obj instanceof DecorationRecommendGroup)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		DecorationRecommendGroup other = (DecorationRecommendGroup)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

