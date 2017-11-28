package com.imall.iportal.core.shop.vo;

/**
 * Created by ygw on 2017/8/7.
 */
public class SRPReadyOrderItemVo extends OrderItemVo {

    //允许退货的数量
    private Long allowReturnedQuantity;

    private Long returnedQuantity;

    public Long getReturnedQuantity() {
        return returnedQuantity;
    }

    public void setReturnedQuantity(Long returnedQuantity) {
        this.returnedQuantity = returnedQuantity;
    }

    public Long getAllowReturnedQuantity() {
        return allowReturnedQuantity;
    }

    public void setAllowReturnedQuantity(Long allowReturnedQuantity) {
        this.allowReturnedQuantity = allowReturnedQuantity;
    }
}
