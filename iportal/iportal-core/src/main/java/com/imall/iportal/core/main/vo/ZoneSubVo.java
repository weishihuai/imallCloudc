package com.imall.iportal.core.main.vo;

import java.util.List;

/**
 * Created by Donie on 2015/10/14.
 */
public class ZoneSubVo {
    private Long id;
    private String zoneName;
    private String zoneCode;
    List<ZoneSubVo> areaList;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ZoneSubVo> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<ZoneSubVo> areaList) {
        this.areaList = areaList;
    }
}
