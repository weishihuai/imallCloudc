package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by lxh on 2017/7/13.
 * 收货记录保存VO
 */
public class PurchaseReceiveRecordSaveVo {
    //收货人
    private String receiver;
    //收货时间
    private String receiveTimeString;
    //采购订单ID
    @NotNull
    private Long purchaseOrderId;
    //门店ID
    @NotNull
    private Long shopId;
    //备注
    private String remark;
    //收货记录项
    @NotEmpty
    @Valid
    private List<PurchaseReceiveRecordItemSaveVo> itemList;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiveTimeString() {
        return receiveTimeString;
    }

    public void setReceiveTimeString(String receiveTimeString) {
        this.receiveTimeString = receiveTimeString;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PurchaseReceiveRecordItemSaveVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<PurchaseReceiveRecordItemSaveVo> itemList) {
        this.itemList = itemList;
    }
}
