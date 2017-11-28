package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by ou on 2017/7/7.
 */
public class FirstManageSupplierQualityApproveSaveVo {

    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;
    /**
     * 供应商 ID
     */
    @NotNull
    private Long supplierId;
    /**
     * 质量 审核 时间
     */
    @Length(max = 512)
    private String qualityApproveTimeString;
    /**
     * 申请 人 姓名
     */
    @Length(max = 512)
    private String applyManName;
    /**
     * 提交 意见
     */
    @Length(max = 512)
    private String submitAdvice;
    /**
     *  企业 备注
     */
    @Length(max = 128)
    private String entRemark;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }


    public String getQualityApproveTimeString() {
        return qualityApproveTimeString;
    }

    public void setQualityApproveTimeString(String qualityApproveTimeString) {
        this.qualityApproveTimeString = qualityApproveTimeString;
    }

    public String getApplyManName() {
        return applyManName;
    }

    public void setApplyManName(String applyManName) {
        this.applyManName = applyManName;
    }

    public String getSubmitAdvice() {
        return submitAdvice;
    }

    public void setSubmitAdvice(String submitAdvice) {
        this.submitAdvice = submitAdvice;
    }

    public String getEntRemark() {
        return entRemark;
    }

    public void setEntRemark(String entRemark) {
        this.entRemark = entRemark;
    }
}
