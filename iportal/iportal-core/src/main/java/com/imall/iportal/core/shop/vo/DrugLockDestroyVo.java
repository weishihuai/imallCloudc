package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/8/8.
 * 药品销毁 Vo对象
 */
public class DrugLockDestroyVo {
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;
    /**
     * 销毁时间
     */
    private String destroyTimeString;
    /**
     * 销毁地点
     */
    private String destroyPlace;
    /**
     * 销毁人
     */
    @NotNull
    private String destroyMan;
    /**
     * 保管员
     */
    private String keeper;
    /**
     * 销毁原因
     */
    private String destroyReason;
    /**
     * 审核 人 ID
     */
    @NotNull
    private Long approveManId;
    /**
     * 复核人 ID
     */
    private Long reviewerId;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getDestroyTimeString() {
        return destroyTimeString;
    }

    public void setDestroyTimeString(String destroyTimeString) {
        this.destroyTimeString = destroyTimeString;
    }

    public String getDestroyPlace() {
        return destroyPlace;
    }

    public void setDestroyPlace(String destroyPlace) {
        this.destroyPlace = destroyPlace;
    }

    public String getDestroyMan() {
        return destroyMan;
    }

    public void setDestroyMan(String destroyMan) {
        this.destroyMan = destroyMan;
    }

    public String getKeeper() {
        return keeper;
    }

    public void setKeeper(String keeper) {
        this.keeper = keeper;
    }

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getDestroyReason() {
        return destroyReason;
    }

    public void setDestroyReason(String destroyReason) {
        this.destroyReason = destroyReason;
    }
}
