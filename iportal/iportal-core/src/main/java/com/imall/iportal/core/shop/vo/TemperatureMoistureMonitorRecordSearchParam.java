package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by frt on 2017/7/7.
 */
public class TemperatureMoistureMonitorRecordSearchParam {
    /**
     *门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     *货位 ID
     */
    private Long storageSpaceId;

    /**
     *监控开始日期
     */
    private String fromMonitorDateString;

    /**
     *监控结束日期
     */
    private String toMonitorDateString;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getStorageSpaceId() {
        return storageSpaceId;
    }

    public void setStorageSpaceId(Long storageSpaceId) {
        this.storageSpaceId = storageSpaceId;
    }

    public String getFromMonitorDateString() {
        return fromMonitorDateString;
    }

    public void setFromMonitorDateString(String fromMonitorDateString) {
        this.fromMonitorDateString = fromMonitorDateString;
    }

    public String getToMonitorDateString() {
        return toMonitorDateString;
    }

    public void setToMonitorDateString(String toMonitorDateString) {
        this.toMonitorDateString = toMonitorDateString;
    }

    public Date getFromMonitorDate() {
        if(StringUtils.isNotBlank(this.getFromMonitorDateString())){
            return DateTimeUtils.getDayStartTime(this.getFromMonitorDateString());
        }else{
            return null;
        }
    }

    public Date getToMonitorDate() {
        if(StringUtils.isNotBlank(this.getToMonitorDateString())){
            return DateTimeUtils.getDayEndTime(this.getToMonitorDateString());
        }else{
            return null;
        }
    }
}
