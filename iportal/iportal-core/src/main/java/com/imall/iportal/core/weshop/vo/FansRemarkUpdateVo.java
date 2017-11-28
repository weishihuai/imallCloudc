package com.imall.iportal.core.weshop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/8/16.
 * 微店粉丝
 */
public class FansRemarkUpdateVo {
    /**
     * 主键ID
     */
    @NotNull
    private Long id;
    /**
     * SHOP_ID - 门店 ID
     */
    @NotNull
    private Long shopId;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
