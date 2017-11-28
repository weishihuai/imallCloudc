package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.BigDecimalUtil;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by lxh on 2017/7/17.
 * 快速收货保存Vo
 */
public class FastReceiveSaveVo {
    //门店ID
    @NotNull
    private Long shopId;
    //供应商ID
    @NotNull
    private Long supplierId;
    //采购人
    private String purchaseMan;
    //制单人
    private String docMaker;
    //订单时间
    private String createDateString;
    //收货人
    private String receiver;
    //收货时间
    private String receiveTimeString;
    //验收员
    private String acceptanceMan;
    //验收时间
    private String acceptanceTimeString;
    //备注
    private String remark;
    //审核人ID
    @NotNull
    private Long approveManId;
    //快速收货项
    @NotEmpty
    @Valid
    private List<FastReceiveItemSaveVo> itemList;

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

    public String getDocMaker() {
        return docMaker;
    }

    public void setDocMaker(String docMaker) {
        this.docMaker = docMaker;
    }

    public String getCreateDateString() {
        return createDateString;
    }

    public void setCreateDateString(String createDateString) {
        this.createDateString = createDateString;
    }

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

    public String getAcceptanceMan() {
        return acceptanceMan;
    }

    public void setAcceptanceMan(String acceptanceMan) {
        this.acceptanceMan = acceptanceMan;
    }

    public String getAcceptanceTimeString() {
        return acceptanceTimeString;
    }

    public void setAcceptanceTimeString(String acceptanceTimeString) {
        this.acceptanceTimeString = acceptanceTimeString;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }

    public List<FastReceiveItemSaveVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<FastReceiveItemSaveVo> itemList) {
        this.itemList = itemList;
    }

    public Double getPurchaseTotalAmount() {
        Double total = 0D;
        if (CollectionUtils.isEmpty(itemList)) {
            return total;
        }
        for (FastReceiveItemSaveVo saveVo : itemList) {
            total = BigDecimalUtil.add(total, BigDecimalUtil.multiply(saveVo.getPurchaseUnitPrice(), saveVo.getGoodsArrivalQuantity()));
        }
        return total;
    }
}
