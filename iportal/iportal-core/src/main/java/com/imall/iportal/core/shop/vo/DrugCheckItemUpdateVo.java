package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by frt on 2017/7/14.
 */
public class DrugCheckItemUpdateVo {
    @NotNull
    private Long id;

    /**
     * 检查 数量
     */
    @NotNull
    private Long checkQuantity;

    /**
     * 检查 项目
     */
    @Length(max = 128)
    private String checkPrj;

    /**
     * 不 合格 数量
     */
    @NotNull
    private Long notQualifiedQuantity;

    /**
     * 处理 意见
     */
    @Length(max = 128)
    private String processOpinion;

    /**
     * 结论
     */
    @Length(max = 128)
    private String conclusion;

    /**
     * 备注
     */
    @Length(max = 256)
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCheckQuantity() {
        return checkQuantity;
    }

    public void setCheckQuantity(Long checkQuantity) {
        this.checkQuantity = checkQuantity;
    }

    public String getCheckPrj() {
        return checkPrj;
    }

    public void setCheckPrj(String checkPrj) {
        this.checkPrj = checkPrj;
    }

    public Long getNotQualifiedQuantity() {
        return notQualifiedQuantity;
    }

    public void setNotQualifiedQuantity(Long notQualifiedQuantity) {
        this.notQualifiedQuantity = notQualifiedQuantity;
    }

    public String getProcessOpinion() {
        return processOpinion;
    }

    public void setProcessOpinion(String processOpinion) {
        this.processOpinion = processOpinion;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
