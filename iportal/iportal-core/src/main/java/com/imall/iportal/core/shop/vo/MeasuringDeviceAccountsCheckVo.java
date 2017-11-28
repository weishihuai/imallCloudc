package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/8/4.
 * 计量器具检测 Vo对象
 */
public class MeasuringDeviceAccountsCheckVo {
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;
    /**
     * 检测 日期
     */
    @NotBlank
    private String measureDateString;
    /**
     * 开始 时间
     */
    @NotBlank
    private String startTimeString;
    /**
     * 结束 时间
     */
    @NotBlank
    private String endTimeString;
    /**
     * 鉴定 项目
     */
    private String identifyPrj;
    /**
     * 技术 要求
     */
    private String skillRequirement;
    /**
     * 检测 方法
     */
    private String measureMethod;
    /**
     * 鉴定 结论
     */
    private String identifyConclusion;
    /**
     * 检测 人
     */
    private String measureMan;
    /**
     * 复检人 名称
     */
    private String reviewMan;
    /**
     * 备注
     */
    private String remark;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getMeasureDateString() {
        return measureDateString;
    }

    public void setMeasureDateString(String measureDateString) {
        this.measureDateString = measureDateString;
    }

    public String getStartTimeString() {
        return startTimeString;
    }

    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    public String getEndTimeString() {
        return endTimeString;
    }

    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }

    public String getIdentifyPrj() {
        return identifyPrj;
    }

    public void setIdentifyPrj(String identifyPrj) {
        this.identifyPrj = identifyPrj;
    }

    public String getSkillRequirement() {
        return skillRequirement;
    }

    public void setSkillRequirement(String skillRequirement) {
        this.skillRequirement = skillRequirement;
    }

    public String getMeasureMethod() {
        return measureMethod;
    }

    public void setMeasureMethod(String measureMethod) {
        this.measureMethod = measureMethod;
    }

    public String getIdentifyConclusion() {
        return identifyConclusion;
    }

    public void setIdentifyConclusion(String identifyConclusion) {
        this.identifyConclusion = identifyConclusion;
    }

    public String getMeasureMan() {
        return measureMan;
    }

    public void setMeasureMan(String measureMan) {
        this.measureMan = measureMan;
    }

    public String getReviewMan() {
        return reviewMan;
    }

    public void setReviewMan(String reviewMan) {
        this.reviewMan = reviewMan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
