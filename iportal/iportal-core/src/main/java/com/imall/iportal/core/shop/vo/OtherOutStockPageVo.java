package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.OtherOutStock;

/**
 * Created by wsh on 2017/7/25.
 */
public class OtherOutStockPageVo extends OtherOutStock {
    /**
     * 出库人姓名
     */
    private String operatorName;
    /**
     * 出库时间
     */
    private String outStockTimeString;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public String getOutStockTimeString() {
        return outStockTimeString;
    }

    @Override
    public void setOutStockTimeString(String outStockTimeString) {
        this.outStockTimeString = outStockTimeString;
    }
}
