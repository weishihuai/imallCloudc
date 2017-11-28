package com.imall.iportal.core.shop.vo;

/**
 * Created by wsh on 2017/8/4.
 * 计量器具 搜索参数
 */
public class MeasuringDeviceAccountsSearchParam {
    /**
     * 门店ID
     */
    private Long shopId;
    /**
     * 计量器具编号
     */
    private String measuringDeviceNum;
    /**
     * 出厂编号
     */
    private String manufacturingNum;
    /**
     * 使用状态代码
     */
    private String useStateCode;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getMeasuringDeviceNum() {
        return measuringDeviceNum;
    }

    public void setMeasuringDeviceNum(String measuringDeviceNum) {
        this.measuringDeviceNum = measuringDeviceNum;
    }

    public String getManufacturingNum() {
        return manufacturingNum;
    }

    public void setManufacturingNum(String manufacturingNum) {
        this.manufacturingNum = manufacturingNum;
    }

    public String getUseStateCode() {
        return useStateCode;
    }

    public void setUseStateCode(String useStateCode) {
        this.useStateCode = useStateCode;
    }
}
