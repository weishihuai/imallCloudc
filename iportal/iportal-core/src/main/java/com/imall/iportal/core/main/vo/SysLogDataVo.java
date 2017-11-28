package com.imall.iportal.core.main.vo;

import com.imall.commons.base.util.DateTimeUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by ygw on 2017/8/30.
 */
public class SysLogDataVo {

    private Long id;
    private String logInnerCode;
    private String lastModifiedBy;
    private Date operationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogInnerCode() {
        return logInnerCode;
    }

    public void setLogInnerCode(String logInnerCode) {
        this.logInnerCode = logInnerCode;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationTimeString() {
        return DateTimeUtils.convertDateTimeToString(getOperationTime());
    }
    public void setOperationTimeString(String value) throws ParseException {
        setOperationTime(DateTimeUtils.convertStringToDateTime(value));
    }
}
