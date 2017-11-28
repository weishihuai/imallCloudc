package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by frt on 2017/7/19.
 */
public class DrugCombinationSearchParam {
    /**
     * 机构 ID
     */
    @NotNull
    private Long orgId;

    /**
     *联合用药分类 ID
     */
    private Long drugCombinationCategoryId;

    /**
     *病症
     */
    private String disease;

    /**
     * 症状
     */
    private String symptom;

    /**
     *常识 判断
     */
    private String commonSense;

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
}
