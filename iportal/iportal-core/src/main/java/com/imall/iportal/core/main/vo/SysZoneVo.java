package com.imall.iportal.core.main.vo;


import com.imall.iportal.core.main.entity.SysZone;

import java.util.List;

/**
 * TODO(Vo)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SysZoneVo extends SysZone{
    /**
     * 市级
     */
    private List<SysZone> cityList;
    /**
     * 区级
     */
    private List<SysZone> areaList;

    public List<SysZone> getCityList() {
        return cityList;
    }

    public void setCityList(List<SysZone> cityList) {
        this.cityList = cityList;
    }

    public List<SysZone> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<SysZone> areaList) {
        this.areaList = areaList;
    }
}
