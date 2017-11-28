package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by frt on 2017/7/19.
 */
public class DrugCombinationSaveVo {
    /**
     * 机构 ID
     */
    @NotNull
    private Long orgId;

    /**
     *联合用药分类 ID
     */
    @NotNull
    private Long drugCombinationCategoryId;

    /**
     *病症
     */
    @NotBlank
    @Length(max = 64)
    private String disease;

    /**
     * 症状
     */
    @NotBlank
    @Length(max = 256)
    private String symptom;

    /**
     *常识 判断
     */
    @NotBlank
    @Length(max = 256)
    private String commonSense;

    /**
     *用药 原则
     */
    @Length(max = 256)
    private String drugUsePrinciple;

    /**
     *主导 用药
     */
    @NotBlank
    @Length(max = 256)
    private String leadingDrug;

    /**
     *联合 用药
     */
    @Length(max = 256)
    private String drugCombination;

    /**
     *专业 关怀
     */
    @Length(max = 256)
    private String majorConcern;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getDrugCombinationCategoryId() {
        return drugCombinationCategoryId;
    }

    public void setDrugCombinationCategoryId(Long drugCombinationCategoryId) {
        this.drugCombinationCategoryId = drugCombinationCategoryId;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getCommonSense() {
        return commonSense;
    }

    public void setCommonSense(String commonSense) {
        this.commonSense = commonSense;
    }

    public String getDrugUsePrinciple() {
        return drugUsePrinciple;
    }

    public void setDrugUsePrinciple(String drugUsePrinciple) {
        this.drugUsePrinciple = drugUsePrinciple;
    }

    public String getLeadingDrug() {
        return leadingDrug;
    }

    public void setLeadingDrug(String leadingDrug) {
        this.leadingDrug = leadingDrug;
    }

    public String getDrugCombination() {
        return drugCombination;
    }

    public void setDrugCombination(String drugCombination) {
        this.drugCombination = drugCombination;
    }

    public String getMajorConcern() {
        return majorConcern;
    }

    public void setMajorConcern(String majorConcern) {
        this.majorConcern = majorConcern;
    }
}
