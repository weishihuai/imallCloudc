
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
@Table(name = "t_shp_drug_combination" )
public class DrugCombination extends BaseEntity<Long>{

	public static final String DRUG_COMBINATION_CATEGORY_ID = "drugCombinationCategoryId";
	public static final String DISEASE = "disease";
	public static final String SYMPTOM = "symptom";
	public static final String COMMON_SENSE = "commonSense";
	public static final String DRUG_USE_PRINCIPLE = "drugUsePrinciple";
	public static final String LEADING_DRUG = "leadingDrug";
	public static final String DRUG_COMBINATION = "drugCombination";
	public static final String MAJOR_CONCERN = "majorConcern";
	public static final String ORG_ID = "orgId";

    /** DRUG_COMBINATION_CATEGORY_ID - 联合用药 分类 ID */
    @Column(name = "DRUG_COMBINATION_CATEGORY_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long drugCombinationCategoryId;
    /** DISEASE - 病症 */
    @Column(name = "DISEASE", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String disease;
    /** SYMPTOM - 症状 */
    @Column(name = "SYMPTOM", unique = false, nullable = false, insertable = true, updatable = true, length = 256)
    private String symptom;
    /** COMMON_SENSE - 常识 判断 */
    @Column(name = "COMMON_SENSE", unique = false, nullable = false, insertable = true, updatable = true, length = 256)
    private String commonSense;
    /** DRUG_USE_PRINCIPLE - 用药 原则 */
    @Column(name = "DRUG_USE_PRINCIPLE", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private String drugUsePrinciple;
    /** LEADING_DRUG - 主导 用药 */
    @Column(name = "LEADING_DRUG", unique = false, nullable = false, insertable = true, updatable = true, length = 256)
    private String leadingDrug;
    /** DRUG_COMBINATION - 联合 用药 */
    @Column(name = "DRUG_COMBINATION", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private String drugCombination;
    /** MAJOR_CONCERN - 专业 关怀 */
    @Column(name = "MAJOR_CONCERN", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private String majorConcern;
    /** ORG_ID - 组织 ID */
    @Column(name = "ORG_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long orgId;

    public void setDrugCombinationCategoryId(Long value) {
		this.drugCombinationCategoryId = value;
	}

    public Long getDrugCombinationCategoryId() {
		return this.drugCombinationCategoryId;
	}

    public void setDisease(String value) {
		this.disease = value;
	}

    public String getDisease() {
		return this.disease;
	}

    public void setSymptom(String value) {
		this.symptom = value;
	}

    public String getSymptom() {
		return this.symptom;
	}

    public void setCommonSense(String value) {
		this.commonSense = value;
	}

    public String getCommonSense() {
		return this.commonSense;
	}

    public void setDrugUsePrinciple(String value) {
		this.drugUsePrinciple = value;
	}

    public String getDrugUsePrinciple() {
		return this.drugUsePrinciple;
	}

    public void setLeadingDrug(String value) {
		this.leadingDrug = value;
	}

    public String getLeadingDrug() {
		return this.leadingDrug;
	}

    public void setDrugCombination(String value) {
		this.drugCombination = value;
	}

    public String getDrugCombination() {
		return this.drugCombination;
	}

    public void setMajorConcern(String value) {
		this.majorConcern = value;
	}

    public String getMajorConcern() {
		return this.majorConcern;
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
			.append("DRUG_COMBINATION_CATEGORY_ID",getDrugCombinationCategoryId())
			.append("DISEASE",getDisease())
			.append("SYMPTOM",getSymptom())
			.append("COMMON_SENSE",getCommonSense())
			.append("DRUG_USE_PRINCIPLE",getDrugUsePrinciple())
			.append("LEADING_DRUG",getLeadingDrug())
			.append("DRUG_COMBINATION",getDrugCombination())
			.append("MAJOR_CONCERN",getMajorConcern())
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
		if(!(obj instanceof DrugCombination)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		DrugCombination other = (DrugCombination)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

