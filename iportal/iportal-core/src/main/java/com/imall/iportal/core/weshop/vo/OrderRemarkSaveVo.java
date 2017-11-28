package com.imall.iportal.core.weshop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 备注
 */
public class OrderRemarkSaveVo {
    @NotNull
    private Long id;

    /**
     * 微门店 ID
     */
    @NotNull
    private java.lang.Long weShopId;

    @NotEmpty
    @Length(max = 255)
    private String adminRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWeShopId() {
        return weShopId;
    }

    public void setWeShopId(Long weShopId) {
        this.weShopId = weShopId;
    }

    public String getAdminRemark() {
        return adminRemark;
    }

    public void setAdminRemark(String adminRemark) {
        this.adminRemark = adminRemark;
    }
}
