package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by frt on 2017/7/15.
 */
public class DrugCuringItemUpdateVo {
    @NotNull
    private Long id;

    /**
     * 养护 数量
     */
    @NotNull
    private Long curingQuantity;

    /**
     * 养护 项目
     */
    @Length(max = 128)
    private String curingPrj;

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

    public Long getCuringQuantity() {
        return curingQuantity;
    }

    public void setCuringQuantity(Long curingQuantity) {
        this.curingQuantity = curingQuantity;
    }

    public String getCuringPrj() {
        return curingPrj;
    }

    public void setCuringPrj(String curingPrj) {
        this.curingPrj = curingPrj;
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
