package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by wsh on 2017/7/21.
 * 库存盘点保存Vo对象
 */
public class StockCheckSaveVo {

    /**
     * 商品信息
     */
    @NotEmpty
    @Valid
    private List<StockCheckGoodsSaveVo> stockCheckGoodsSaveVoList;

    public List<StockCheckGoodsSaveVo> getStockCheckGoodsSaveVoList() {
        return stockCheckGoodsSaveVoList;
    }

    public void setStockCheckGoodsSaveVoList(List<StockCheckGoodsSaveVo> stockCheckGoodsSaveVoList) {
        this.stockCheckGoodsSaveVoList = stockCheckGoodsSaveVoList;
    }
}
