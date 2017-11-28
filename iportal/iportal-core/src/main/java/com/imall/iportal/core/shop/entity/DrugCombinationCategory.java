
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
@Table(name = "t_shp_drug_combination_category" )
public class DrugCombinationCategory extends BaseEntity<Long>{

	public static final String CATEGORY_NM = "categoryNm";
	public static final String ORG_ID = "orgId";

    /** CATEGORY_NM - categoryNm */
    @Column(name = "CATEGORY_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String categoryNm;
    /** ORG_ID - 组织 ID */
    @Column(name = "ORG_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long orgId;

    public void setCategoryNm(String value) {
		this.categoryNm = value;
	}

    public String getCategoryNm() {
		return this.categoryNm;
	}

    public void setOrgId(Long value) {
		this.orgId = value;
	}

    public Long getOrgId() {
		return this.orgId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("CATEGORY_NM",getCategoryNm())
			.append("CREATE_DATE",getCreateDate())
			.append("CREATE_BY",getCreateBy())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("VERSION",getVersion())
			.append("ORG_ID",getOrgId())
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
		if(!(obj instanceof DrugCombinationCategory)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		DrugCombinationCategory other = (DrugCombinationCategory)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

