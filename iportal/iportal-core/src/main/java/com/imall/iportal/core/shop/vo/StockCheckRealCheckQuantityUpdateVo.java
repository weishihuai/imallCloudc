package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/21.
 * 库存盘点 更新实盘数量Vo
 */
public class StockCheckRealCheckQuantityUpdateVo {
    /**
     * 主键ID
     */
    @NotNull
    private Long id;
    /**
     * 实盘数量
     */
    @NotNull
    private Long realCheckQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRealCheckQuantity() {
        return realCheckQuantity;
    }

    public void setRealCheckQuantity(Long realCheckQuantity) {
        this.realCheckQuantity = realCheckQuantity;
    }
}
