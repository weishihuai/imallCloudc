package com.imall.iportal.core.main.log;


import com.imall.commons.base.util.DateTimeUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * User: ygw
 * Date: 17-8-29
 */
public class LogDataVo {

    private Long logDataId;
    private String logInnerCode;
    private Date operationTime;
    private String dataOperationTypeCode;

    private String tableKey;
    private String tableName;
    private String columnKey;
    private String columnName;
    private String columnType; //正常 主键 外键
    private String columnBeforeOperationValue;
    private String columnAfterOperationValue;



    public String getColumnAfterOperationValue() {
        return columnAfterOperationValue;
    }

    public void setColumnAfterOperationValue(String columnAfterOperationValue) {
        this.columnAfterOperationValue = columnAfterOperationValue;
    }

    public String getColumnBeforeOperationValue() {
        return columnBeforeOperationValue;
    }

    public void setColumnBeforeOperationValue(String columnBeforeOperationValue) {
        this.columnBeforeOperationValue = columnBeforeOperationValue;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableKey() {
        return tableKey;
    }

    public void setTableKey(String tableKey) {
        this.tableKey = tableKey;
    }

    public Long getLogDataId() {
        return logDataId;
    }

    public void setLogDataId(Long logDataId) {
        this.logDataId = logDataId;
    }

    public String getLogInnerCode() {
        return logInnerCode;
    }

    public void setLogInnerCode(String logInnerCode) {
        this.logInnerCode = logInnerCode;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getDataOperationTypeCode() {
        return dataOperationTypeCode;
    }

    public void setDataOperationTypeCode(String dataOperationTypeCode) {
        this.dataOperationTypeCode = dataOperationTypeCode;
    }

    public String getOperationTimeString() {
        return DateTimeUtils.convertDateTimeToString(getOperationTime());
    }
    public void setOperationTimeString(String value) {
        try {
            setOperationTime(DateTimeUtils.convertStringToDateTime(value));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
}
