package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by lxh on 2017/7/15.
 */
public class ReturnedPurchaseOrderSaveVo {
    //门店ID
    @NotNull
    private Long shopId;
    //供应商ID
    @NotNull
    private Long supplierId;
    //采购员
    private String purchaseMan;
    //出库员
    private String outStorageMan;
    //审核人ID
    @NotNull
    private Long approveManId;
    //退货类型
    @NotNull
    @Length(max = 32)
    private String returnedPurchaseType;
    //退货原因
    @NotNull
    @Length(max = 32)
    private String returnedPurchaseReason;
    //备注
    private String remark;
    //退休项
    private List<ReturnedPurchaseOrderItemSaveVo> itemList;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getPurchaseMan() {
        return purchaseMan;
    }

    public void setPurchaseMan(String purchaseMan) {
        this.purchaseMan = purchaseMan;
    }

    public String getOutStorageMan() {
        return outStorageMan;
    }

    public void setOutStorageMan(String outStorageMan) {
        this.outStorageMan = outStorageMan;
    }

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }

    public String getReturnedPurchaseType() {
        return returnedPurchaseType;
    }

    public void setReturnedPurchaseType(String returnedPurchaseType) {
        this.returnedPurchaseType = returnedPurchaseType;
    }

    public String getReturnedPurchaseReason() {
        return returnedPurchaseReason;
    }

    public void setReturnedPurchaseReason(String returnedPurchaseReason) {
        this.returnedPurchaseReason = returnedPurchaseReason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ReturnedPurchaseOrderItemSaveVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<ReturnedPurchaseOrderItemSaveVo> itemList) {
        this.itemList = itemList;
    }
}
