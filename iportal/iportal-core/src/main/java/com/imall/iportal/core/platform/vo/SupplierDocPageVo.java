package com.imall.iportal.core.platform.vo;

/**
 * Created by ou on 2017/7/7.
 */
public class SupplierDocPageVo {
    /**
     * 供应商 id
     */
    private Long id;
    /**
     * 供应商 编码
     */
    private String supplierCode;
    /**
     * 供应商 名称
     */
    private  String supplierNm;
    /**
     * 单位 性质
     */
    private  String unitNatureName;
    /**
     * 业务 负责 人
     */
    private  String businessResponseManName;
    /**
     * 业务 负责  电话
     */
    private String businessResponseManTel;
    /**
     * 状态
     */
    private String state;
    /**
     * 创建时间
     */
    private String createTimeString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public String getUnitNatureName() {
        return unitNatureName;
    }

    public void setUnitNatureName(String unitNatureName) {
        this.unitNatureName = unitNatureName;
    }

    public String getBusinessResponseManName() {
        return businessResponseManName;
    }

    public void setBusinessResponseManName(String businessResponseManName) {
        this.businessResponseManName = businessResponseManName;
    }

    public String getBusinessResponseManTel() {
        return businessResponseManTel;
    }

    public void setBusinessResponseManTel(String businessResponseManTel) {
        this.businessResponseManTel = businessResponseManTel;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateTimeString() {
        return createTimeString;
    }

    public void setCreateTimeString(String createTimeString) {
        this.createTimeString = createTimeString;
    }
}
