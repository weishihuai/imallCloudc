package com.imall.iportal.core.platform.vo;

import com.imall.iportal.core.platform.entity.SupplierDoc;

/**
 * Created by ygw on 2017/8/24.
 */
public class SupplierDocExcelVo extends SupplierDoc {

    private String businessCategoryName;
    private String businessRangeName;
    private String unitNatureName;
    private String stateName;

    public String getBusinessRangeName() {
        return businessRangeName;
    }

    public void setBusinessRangeName(String businessRangeName) {
        this.businessRangeName = businessRangeName;
    }

    public String getBusinessCategoryName() {
        return businessCategoryName;
    }

    public void setBusinessCategoryName(String businessCategoryName) {
        this.businessCategoryName = businessCategoryName;
    }

    public String getUnitNatureName() {
        return unitNatureName;
    }

    public void setUnitNatureName(String unitNatureName) {
        this.unitNatureName = unitNatureName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
