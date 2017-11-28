package com.imall.iportal.core.shop.vo;

/**
 * Created by ou on 2017/7/7.
 */
public class StaffHealthDocInfDetailVo {
    /**
     * id
     */
    private Long id;
    /**
     * 检查 编号
     */
    private String checkNum;
    /**
     * 检查 日期
     */

    private String checkDateString;
    /**
     * 检查 机构
     */
    private String checkOrg;
    /**
     * 检查 项目
     */
    private String checkPrj;
    /**
     * 检查 结果
     */

    private String checkResult;
    /**
     * 采取 措施
     */
    private String takeMeasures;
    /**
     * 备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
