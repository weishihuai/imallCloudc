package com.imall.iportal.core.shop.vo;

/**
 * Created by frt on 2017/7/17.
 */
public class DrugCombinationCategoryPageVo {
    private Long id;

    private Long orgId;

    /**
     * 当前用户机构 ID
     */
    private Long currentOrgId;

    private String categoryNm;

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

    public String getCategoryNm() {
        return categoryNm;
    }

    public void setCategoryNm(String categoryNm) {
        this.categoryNm = categoryNm;
    }
}
