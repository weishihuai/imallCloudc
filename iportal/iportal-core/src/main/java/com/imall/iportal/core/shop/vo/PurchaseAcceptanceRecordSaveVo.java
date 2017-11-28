package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by lxh on 2017/7/14.
 * 验收记录保存VO
 */
public class PurchaseAcceptanceRecordSaveVo {
    //采购收货记录ID
    @NotNull
    private Long purchaseReceiveRecordId;
    //门店ID
    @NotNull
    private Long shopId;
    //制单人
    private String docMaker;
    //验收时间
    private String acceptanceTimeString;
    //验收员
    private String acceptanceMan;
    //审核人ID
    @NotNull
    private Long approveManId;
    //备注
    private String remark;
    //验收项
    @Valid
    @NotEmpty
    private List<PurchaseAcceptanceRecordItemSaveVo> itemList;

    public Long getPurchaseReceiveRecordId() {
        return purchaseReceiveRecordId;
    }

    public void setPurchaseReceiveRecordId(Long purchaseReceiveRecordId) {
        this.purchaseReceiveRecordId = purchaseReceiveRecordId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getDocMaker() {
        return docMaker;
    }

    public void setDocMaker(String docMaker) {
        this.docMaker = docMaker;
    }

    public String getAcceptanceTimeString() {
        return acceptanceTimeString;
    }

    public void setAcceptanceTimeString(String acceptanceTimeString) {
        this.acceptanceTimeString = acceptanceTimeString;
    }

    public String getAcceptanceMan() {
        return acceptanceMan;
    }

    public void setAcceptanceMan(String acceptanceMan) {
        this.acceptanceMan = acceptanceMan;
    }

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PurchaseAcceptanceRecordItemSaveVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<PurchaseAcceptanceRecordItemSaveVo> itemList) {
        this.itemList = itemList;
    }
}
