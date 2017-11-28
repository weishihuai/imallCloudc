package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应 搜索参数
 */
public class BadReactionRegSearchParam {
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;
    /**
     * 报告 类型
     */
    private String repType;
    /**
     * 患者 姓名
     */
    private String patientName;
    /**
     * 报告日期(开始时间)
     */
    private String repStartDateString;
    /**
     * 报告日期(结束时间)
     */
    private String repEndDateString;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getRepType() {
        return repType;
    }

    public void setRepType(String repType) {
        this.repType = repType;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getRepStartDateString() {
        return repStartDateString;
    }

    public void setRepStartDateString(String repStartDateString) {
        this.repStartDateString = repStartDateString;
    }

    public String getRepEndDateString() {
        return repEndDateString;
    }

    public void setRepEndDateString(String repEndDateString) {
        this.repEndDateString = repEndDateString;
    }
}
