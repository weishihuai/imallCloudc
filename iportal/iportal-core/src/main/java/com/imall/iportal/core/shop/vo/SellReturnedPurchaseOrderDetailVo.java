package com.imall.iportal.core.shop.vo;

import java.util.Date;
import java.util.List;

/**
 * Created by HWJ on 2017/8/10.
 */
public class SellReturnedPurchaseOrderDetailVo {

    /**
     * 退货单号
     */
    private String sellReturnedPurchaseOrderNum;

    /**
     * 创建时间/退货时间
     */
    private Date createDate;

    /**
     * 销售订单号
     */
    private String sellPurchaseOrderNum;

    /**
     * 退货原因
     */
    private String remark;

    /**
     * 营业员
     */
    private String cashierNm;

    /**
     * 审核人名称
     */
    private String approveManNm;

    /**
     * 金额总计
     */
    private Double refundTotalAmount;

    /**
     * 退货订单项
     */
    private List<SellReturnedPurchaseOrderItemPageVo> itemList;

    public String getSellReturnedPurchaseOrderNum() {
        return sellReturnedPurchaseOrderNum;
    }

    public void setSellReturnedPurchaseOrderNum(String sellReturnedPurchaseOrderNum) {
        this.sellReturnedPurchaseOrderNum = sellReturnedPurchaseOrderNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSellPurchaseOrderNum() {
        return sellPurchaseOrderNum;
    }

    public void setSellPurchaseOrderNum(String sellPurchaseOrderNum) {
        this.sellPurchaseOrderNum = sellPurchaseOrderNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCashierNm() {
        return cashierNm;
    }

    public void setCashierNm(String cashierNm) {
        this.cashierNm = cashierNm;
    }

    public String getApproveManNm() {
        return approveManNm;
    }

    public void setApproveManNm(String approveManNm) {
        this.approveManNm = approveManNm;
    }

    public Double getRefundTotalAmount() {
        return refundTotalAmount;
    }

    public void setRefundTotalAmount(Double refundTotalAmount) {
        this.refundTotalAmount = refundTotalAmount;
    }

    public List<SellReturnedPurchaseOrderItemPageVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<SellReturnedPurchaseOrderItemPageVo> itemList) {
        this.itemList = itemList;
    }
}
