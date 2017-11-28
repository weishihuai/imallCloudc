package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.DisqualificationDrugProcessRecord;

/**
 * Created by wsh on 2017/8/10.
 * 不合格药品处理 列表对象
 */
public class DisqualificationDrugProcessRecordPageVo extends DisqualificationDrugProcessRecord {
    /**
     * 日期
     */
    private String recordDateString;
    /**
     * 有效期至
     */
    private String validDateString;

    @Override
    public String getRecordDateString() {
        return recordDateString;
    }

    @Override
    public void setRecordDateString(String recordDateString) {
        this.recordDateString = recordDateString;
    }

    public String getValidDateString() {
        return validDateString;
    }

    public void setValidDateString(String validDateString) {
        this.validDateString = validDateString;
    }
}
