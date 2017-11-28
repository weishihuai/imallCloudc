package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.OtherInStock;

/**
 * Created by wsh on 2017/7/28.
 */
public class OtherInStockPageVo extends OtherInStock {
    /**
     * 入库人姓名
     */
    private String operatorName;
    /**
     * 入库时间
     */
    private String inStockTimeString;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getInStockTimeString() {
        return inStockTimeString;
    }

    public void setInStockTimeString(String inStockTimeString) {
        this.inStockTimeString = inStockTimeString;
    }
}
