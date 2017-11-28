package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by lxh on 2017/7/15.
 * 退货项保存VO
 */
public class ReturnedPurchaseOrderItemSaveVo {
    //采购验收记录项ID
    @NotNull
    private Long purchaseAcceptanceRecordItemId;
    //退货数量
    @NotNull
    @Min(1)
    private Long returnedPurchaseQuantity;

    public Long getPurchaseAcceptanceRecordItemId() {
        return purchaseAcceptanceRecordItemId;
    }

    public void setPurchaseAcceptanceRecordItemId(Long purchaseAcceptanceRecordItemId) {
        this.purchaseAcceptanceRecordItemId = purchaseAcceptanceRecordItemId;
    }

    public Long getReturnedPurchaseQuantity() {
        return returnedPurchaseQuantity;
    }

    public void setReturnedPurchaseQuantity(Long returnedPurchaseQuantity) {
        this.returnedPurchaseQuantity = returnedPurchaseQuantity;
    }
}
