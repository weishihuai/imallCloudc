package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by frt on 2017/8/7.
 */
public class FacilityAndDeviceAccountsSearchParam {
    /**
     *门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     *设备 类型 代码
     */
    private String deviceTypeCode;

    /**
     *设备 编号
     */
    private String deviceNum;

    /**
     *设备 名称
     */
    private String deviceNm;

    /**
     *负责 人
     */
    private String responseMan;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getDeviceTypeCode() {
        return deviceTypeCode;
    }

    public void setDeviceTypeCode(String deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getDeviceNm() {
        return deviceNm;
    }

    public void setDeviceNm(String deviceNm) {
        this.deviceNm = deviceNm;
    }

    public String getResponseMan() {
        return responseMan;
    }

    public void setResponseMan(String responseMan) {
        this.responseMan = responseMan;
    }
}
