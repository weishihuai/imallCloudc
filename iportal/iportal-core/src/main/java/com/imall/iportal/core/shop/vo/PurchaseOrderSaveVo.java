package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.BigDecimalUtil;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by wsh on 2017/7/7.
 * 采购订单保存VO对象
 */
public class PurchaseOrderSaveVo {
    //门店ID
    @NotNull
    private Long shopId;
    //供应商ID
    @NotNull
    private Long supplierId;
    //采购人
    private String purchaseMan;
    //销售人员
    private String sellMan;
    //联系电话
    private String contactTel;
    //预计 到货 时间
    @NotBlank
    private String expectedArrivalTimeString;
    //备注
    private String remark;
    //订单项
    @Valid
    @NotEmpty
    private List<PurchaseOrderItemSaveVo> itemList;

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

    public String getSellMan() {
        return sellMan;
    }

    public void setSellMan(String sellMan) {
        this.sellMan = sellMan;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getExpectedArrivalTimeString() {
        return expectedArrivalTimeString;
    }

    public void setExpectedArrivalTimeString(String expectedArrivalTimeString) {
        this.expectedArrivalTimeString = expectedArrivalTimeString;
    }

    public Double getPurchaseTotalAmount() {
        Double total = 0D;
        if (CollectionUtils.isEmpty(itemList)){
            return total;
        }
        for (PurchaseOrderItemSaveVo itemSaveVo : itemList){
            total = BigDecimalUtil.add(total, BigDecimalUtil.multiply(itemSaveVo.getPurchaseUnitPrice(), itemSaveVo.getPurchaseQuantity()));
        }
        return total;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PurchaseOrderItemSaveVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<PurchaseOrderItemSaveVo> itemList) {
        this.itemList = itemList;
    }
}
