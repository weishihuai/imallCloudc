package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.StockCheck;

/**
 * Created by wsh on 2017/7/20.
 * 库存盘点信息
 */
public class StockCheckPageVo extends StockCheck {
    /**
     * 操作时间(String)
     */
    private String operationTimeString;
    /**
     * 操作人姓名
     */
    private String operatorName;

    public String getOperationTimeString() {
        return operationTimeString;
    }

    public void setOperationTimeString(String operationTimeString) {
        this.operationTimeString = operationTimeString;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
