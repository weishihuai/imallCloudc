package com.imall.iportal.core.shop.vo;

import java.util.List;

/**
 * Created by wsh on 2017/7/21.
 * 库存盘点 更新实盘数量Vo
 */
public class StockCheckCheckedVo {

    /**
     * 实盘数量
     */
    private List<StockCheckRealCheckQuantityUpdateVo> stockCheckRealCheckQuantityUpdateVoList;

    public List<StockCheckRealCheckQuantityUpdateVo> getStockCheckRealCheckQuantityUpdateVoList() {
        return stockCheckRealCheckQuantityUpdateVoList;
    }

    public void setStockCheckRealCheckQuantityUpdateVoList(List<StockCheckRealCheckQuantityUpdateVo> stockCheckRealCheckQuantityUpdateVoList) {
        this.stockCheckRealCheckQuantityUpdateVoList = stockCheckRealCheckQuantityUpdateVoList;
    }

}
