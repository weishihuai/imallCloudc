package com.imall.iportal.core.shop.vo;

/**
 * Created by frt on 2017/7/19.
 */
public class DrugCombinationPageVo {
    private Long id;

    /**
     * 机构 ID
     */
    private Long orgId;

    /**
     * 当前用户机构 ID
     */
    private Long currentOrgId;

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

    /**
     *用药 原则
     */
    private String drugUsePrinciple;

    /**
     *主导 用药
     */
    private String leadingDrug;

    /**
     *联合 用药
     */
    private String drugCombination;

    /**
     *专业 关怀
     */
    private String majorConcern;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getCurrentOrgId() {
        return currentOrgId;
    }

    public void setCurrentOrgId(Long currentOrgId) {
        this.currentOrgId = currentOrgId;
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
