package com.imall.iportal.core.main.vo;


import com.imall.iportal.core.main.entity.SysLog;
import com.imall.iportal.core.main.entity.SysLogData;

import java.util.List;

/**
 * Created by yang on 2015-03-10.
 */
public class SysLogVo extends SysLog {

    private String loginOrgNm;

    public String getLoginOrgNm() {
        return loginOrgNm;
    }

    public void setLoginOrgNm(String loginOrgNm) {
        this.loginOrgNm = loginOrgNm;
    }

    private List<SysLogData> logDataList;

    public List<SysLogData> getLogDataList() {
        return logDataList;
    }

    public void setLogDataList(List<SysLogData> logDataList) {
        this.logDataList = logDataList;
    }
}
