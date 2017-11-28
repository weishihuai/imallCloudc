package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by lxh on 2017/7/13.
 * 收货记录项保存VO
 */
public class PurchaseReceiveRecordItemSaveVo {
    //商品ID
    @NotNull
    private Long goodsId;
    //收货数量
    @NotNull
    @Min(0)
    private Long receiveQuantity;
    //拒收原因
    private String rejectionReason;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getReceiveQuantity() {
        return receiveQuantity;
    }

    public void setReceiveQuantity(Long receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
}
