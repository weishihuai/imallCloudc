package com.imall.iportal.core.shop.vo;

/**
 * Created by wsh on 2017/7/25.
 * 货位移动 搜索参数
 */
public class StorageSpaceMoveSearchParam {
    /**
     * 门店ID
     */
    private Long shopId;
    /**
     * 移动人姓名
     */
    private String moveManName;
    /**
     * 移动单号
     */
    private String moveOrderNum;
    /**
     * 移动时间(开始)
     */
    private String moveStartTimeString;
    /**
     * 移动时间(结束)
     */
    private String moveEndTimeString;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getMoveManName() {
        return moveManName;
    }

    public void setMoveManName(String moveManName) {
        this.moveManName = moveManName;
    }

    public String getMoveOrderNum() {
        return moveOrderNum;
    }

    public void setMoveOrderNum(String moveOrderNum) {
        this.moveOrderNum = moveOrderNum;
    }

    public String getMoveStartTimeString() {
        return moveStartTimeString;
    }

    public void setMoveStartTimeString(String moveStartTimeString) {
        this.moveStartTimeString = moveStartTimeString;
    }

    public String getMoveEndTimeString() {
        return moveEndTimeString;
    }

    public void setMoveEndTimeString(String moveEndTimeString) {
        this.moveEndTimeString = moveEndTimeString;
    }
}
