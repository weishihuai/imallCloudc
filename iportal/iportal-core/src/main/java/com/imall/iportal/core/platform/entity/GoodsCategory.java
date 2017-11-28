
package com.imall.iportal.core.platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 商品 分类实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_ptfm_goods_category" )
public class GoodsCategory extends BaseEntity<Long>{

	public static final String CATEGORY_NAME = "categoryName";
	public static final String DISPLAY_POSITION = "displayPosition";
	public static final String IS_DELETE = "isDelete";
	public static final String PARENT_ID = "parentId";
	public static final String NODE_CODE = "nodeCode";

    /** CATEGORY_NAME - 分类 名称 */
    @Column(name = "CATEGORY_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String categoryName;
    /** DISPLAY_POSITION - 显示 排序 */
    @Column(name = "DISPLAY_POSITION", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long displayPosition;
    /** IS_DELETE - 是否 删除 */
    @Column(name = "IS_DELETE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isDelete;
    /** PARENT_ID - 父 ID */
    @Column(name = "PARENT_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long parentId;
    /** NODE_CODE - 节点 编码 */
    @Column(name = "NODE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 1024)
    private String nodeCode;

    public void setCategoryName(String value) {
		this.categoryName = value;
	}

    public String getCategoryName() {
		return this.categoryName;
	}

	public Long getDisplayPosition() {
		return displayPosition;
	}

	public void setDisplayPosition(Long displayPosition) {
		this.displayPosition = displayPosition;
	}

	public void setIsDelete(String value) {
		this.isDelete = value;
	}

    public String getIsDelete() {
		return this.isDelete;
	}

    public void setParentId(Long value) {
		this.parentId = value;
	}

    public Long getParentId() {
		return this.parentId;
	}

    public void setNodeCode(String value) {
		this.nodeCode = value;
	}

    public String getNodeCode() {
		return this.nodeCode;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("CATEGORY_NAME",getCategoryName())
			.append("DISPLAY_POSITION",getDisplayPosition())
			.append("IS_DELETE", getIsDelete())
			.append("PARENT_ID",getParentId())
			.append("NODE_CODE",getNodeCode())
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
		if(!(obj instanceof GoodsCategory)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		GoodsCategory other = (GoodsCategory)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

