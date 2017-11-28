package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by frt on 2017/8/1.
 */
public class PrescriptionDispensingVo {
    @NotNull
    private Long id;

    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     * 调剂 员 姓名
     */
    @NotBlank
    @Length(max = 32)
    private String dispensingManName;

    /**
     * 复核 人 ID
     */
    @NotNull
    private Long repeatApproveManId;

    /**
     * 发药 人 姓名
     */
    @NotBlank
    @Length(max = 32)
    private String grantDrugManName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getDispensingManName() {
        return dispensingManName;
    }

    public void setDispensingManName(String dispensingManName) {
        this.dispensingManName = dispensingManName;
    }

    public Long getRepeatApproveManId() {
        return repeatApproveManId;
    }

    public void setRepeatApproveManId(Long repeatApproveManId) {
        this.repeatApproveManId = repeatApproveManId;
    }

    public String getGrantDrugManName() {
        return grantDrugManName;
    }

    public void setGrantDrugManName(String grantDrugManName) {
        this.grantDrugManName = grantDrugManName;
    }
}
