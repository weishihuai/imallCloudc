package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by caidapao on 2017/8/1.
 */
public class GoodsUpdateIsEnableParam {

    @NotNull
    private Long shopId;

    @NotBlank
    private String operationState;        //启用/禁用

    @NotNull
    private Long id;

    @NotBlank
    private String reason;          //修改原因

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getOperationState() {
        return operationState;
    }

    public void setOperationState(String operationState) {
        this.operationState = operationState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
