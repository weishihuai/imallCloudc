
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
 * 销售 分类实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_sales_category" )
public class SalesCategory extends BaseEntity<Long>{

	public static final String CATEGORY_NAME = "categoryName";
	public static final String IS_FRONTEND_SHOW = "isFrontendShow";
	public static final String DISPALY_POSITION = "dispalyPosition";
	public static final String IS_ENABLE = "isEnable";
	public static final String SHOP_ID = "shopId";

    /** CATEGORY_NAME - 分类 名称 */
    @Column(name = "CATEGORY_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String categoryName;
    /** IS_FRONTEND_SHOW - 是否 前台 展示 */
    @Column(name = "IS_FRONTEND_SHOW", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isFrontendShow;
    /** DISPALY_POSITION - 显示 排序 */
    @Column(name = "DISPALY_POSITION", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long dispalyPosition;
    /** IS_ENABLE - 是否 启用 */
    @Column(name = "IS_ENABLE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isEnable;
    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;

    public void setCategoryName(String value) {
		this.categoryName = value;
	}

    public String getCategoryName() {
		return this.categoryName;
	}

    public void setIsFrontendShow(String value) {
		this.isFrontendShow = value;
	}

    public String getIsFrontendShow() {
		return this.isFrontendShow;
	}

    public void setDispalyPosition(Long value) {
		this.dispalyPosition = value;
	}

    public Long getDispalyPosition() {
		return this.dispalyPosition;
	}

    public void setIsEnable(String value) {
		this.isEnable = value;
	}

    public String getIsEnable() {
		return this.isEnable;
	}

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("CATEGORY_NAME",getCategoryName())
			.append("IS_FRONTEND_SHOW",getIsFrontendShow())
			.append("DISPALY_POSITION",getDispalyPosition())
			.append("IS_ENABLE",getIsEnable())
			.append("SHOP_ID",getShopId())
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
		if(!(obj instanceof SalesCategory)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SalesCategory other = (SalesCategory)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

