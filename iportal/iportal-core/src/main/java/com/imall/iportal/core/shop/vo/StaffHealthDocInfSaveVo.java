package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by ou on 2017/7/7.
 */
public class StaffHealthDocInfSaveVo {

    /**
     * 检查 编号
     */
    @NotNull
    private Long staffHealthDocId;
    /**
     * 检查 编号
     */
    @NotBlank
    @Length(max = 32)
    private String checkNum;
    /**
     * 检查 日期
     */
    @NotBlank
    private String checkDateString;
    /**
     * 检查 机构
     */
    @NotBlank
    @Length(max = 32)
    private String checkOrg;
    /**
     * 检查 项目
     */
    @NotBlank
    @Length(max = 32)
    private String checkPrj;
    /**
     * 检查 结果
     */
    @NotBlank
    @Length(max = 32)
    private String checkResult;
    /**
     * 采取 措施
     */
    private String takeMeasures;
    /**
     * 备注
     */
    private String remark;

    public Long getStaffHealthDocId() {
        return staffHealthDocId;
    }

    public void setStaffHealthDocId(Long staffHealthDocId) {
        this.staffHealthDocId = staffHealthDocId;
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    public String getCheckDateString() {
        return checkDateString;
    }

    public void setCheckDateString(String checkDateString) {
        this.checkDateString = checkDateString;
    }

    public String getCheckOrg() {
        return checkOrg;
    }

    public void setCheckOrg(String checkOrg) {
        this.checkOrg = checkOrg;
    }

    public String getCheckPrj() {
        return checkPrj;
    }

    public void setCheckPrj(String checkPrj) {
        this.checkPrj = checkPrj;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getTakeMeasures() {
        return takeMeasures;
    }

    public void setTakeMeasures(String takeMeasures) {
        this.takeMeasures = takeMeasures;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
