package com.imall.iportal.core.main.vo;

import java.util.List;

/**
 * Created by Donie on 2015/10/14.
 */
public class ZoneTreeVo {

    /** ZONE_NAME - 地区名称 */
    private String zoneName;
    /** ZONE_CODE - 编码 */
    private String zoneCode;

    List<ZoneSubVo> cityList;

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public List<ZoneSubVo> getCityList() {
        return cityList;
    }

    public void setCityList(List<ZoneSubVo> cityList) {
        this.cityList = cityList;
    }
}
