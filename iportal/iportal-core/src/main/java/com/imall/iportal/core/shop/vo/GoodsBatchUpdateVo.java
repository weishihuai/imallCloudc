package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/8.
 * 商品批号更新 - VO对象
 */
public class GoodsBatchUpdateVo {
    /**
     * 主键ID
     */
    @NotNull
    private Long id;
    /**
     * 商品编码
     */
    @NotBlank
    @Length(max = 64)
    private String newBatch;
    /**
     * 生产日期
     */
    @NotBlank
    private String newProduceDateString;
    /**
     * 有效日期
     */
    @NotBlank
    private String newValidDateString;
    /**
     * 审核人ID
     */
    private Long approveManId;

    public String getNewBatch() {
        return newBatch;
    }

    public void setNewBatch(String newBatch) {
        this.newBatch = newBatch;
    }

    public String getNewProduceDateString() {
        return newProduceDateString;
    }

    public void setNewProduceDateString(String newProduceDateString) {
        this.newProduceDateString = newProduceDateString;
    }

    public String getNewValidDateString() {
        return newValidDateString;
    }

    public void setNewValidDateString(String newValidDateString) {
        this.newValidDateString = newValidDateString;
    }

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
