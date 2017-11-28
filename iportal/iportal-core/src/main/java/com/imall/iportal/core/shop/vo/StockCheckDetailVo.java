package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.StockCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsh on 2017/7/20.
 * 库存盘点详情信息
 */
public class StockCheckDetailVo extends StockCheck {

    /**
     * 操作人
     */
    private String operatorName;
    /**
     * 操作时间
     */
    private String operationTimeString;
    /**
     * 商品信息集合
     */
    private List<StockCheckGoodsVo> stockCheckGoodsVoList = new ArrayList<>();

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public List<StockCheckGoodsVo> getStockCheckGoodsVoList() {
        return stockCheckGoodsVoList;
    }

    public void setStockCheckGoodsVoList(List<StockCheckGoodsVo> stockCheckGoodsVoList) {
        this.stockCheckGoodsVoList = stockCheckGoodsVoList;
    }

    @Override
    public String getOperationTimeString() {
        return operationTimeString;
    }

    @Override
    public void setOperationTimeString(String operationTimeString) {
        this.operationTimeString = operationTimeString;
    }
}
