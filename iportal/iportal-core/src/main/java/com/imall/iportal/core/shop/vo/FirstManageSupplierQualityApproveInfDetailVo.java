package com.imall.iportal.core.shop.vo;

/**
 * Created by ou on 2017/7/7.
 */
public class FirstManageSupplierQualityApproveInfDetailVo {

    /**
     * 审核 时间
     */
    private String approveTimeString;
    /**
     *  审核 类型
     */
    private String approveType;
    /**
     * 审核 人
     */
    private String approveMan;

    /**
     *审核 状态
     */
    private String approveState;
    /**
     * 审核 备注
     */
    private String approveRemark;

    public String getApproveTimeString() {
        return approveTimeString;
    }

    public void setApproveTimeString(String approveTimeString) {
        this.approveTimeString = approveTimeString;
    }

    public String getApproveType() {
        return approveType;
    }

    public void setApproveType(String approveType) {
        this.approveType = approveType;
    }

    public String getApproveMan() {
        return approveMan;
    }

    public void setApproveMan(String approveMan) {
        this.approveMan = approveMan;
    }

    public String getApproveState() {
        return approveState;
    }

    public void setApproveState(String approveState) {
        this.approveState = approveState;
    }

    public String getApproveRemark() {
        return approveRemark;
    }

    public void setApproveRemark(String approveRemark) {
        this.approveRemark = approveRemark;
    }
}
