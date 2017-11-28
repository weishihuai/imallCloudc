package com.imall.iportal.core.shop.vo;

/**
 * 销售退货
 * Created by HWJ on 2017/8/10
 */
public class SellReturnedPurchaseOrderPageVo {

    /**
     * id
     */
    private Long id;

    /**
     * 创建时间
     */
    private String createDateString;

    /**
     * 退货单号
     */
    private String sellReturnedPurchaseOrderNum;

    /**
     * 销售订单号
     */
    private String sellPurchaseOrderNum;

    /**
     * 退货金额
     */
    private Double realReturnCashAmount;

    /**
     * 金额总计
     */
    private Double refundTotalAmount;

    /**
     * 营业员
     */
    private String cashierNm;

    /**
     * 审核人名称
     */
    private String approveManNm;

    /**
     * 退货原因
     */
    private String remark;

    /**
     * 会员卡号码
     */
    private String memberCardNum;

    /**
     * 会员名称
     */
    private String name;

    public String getMemberCardNum() {
        return memberCardNum;
    }

    public void setMemberCardNum(String memberCardNum) {
        this.memberCardNum = memberCardNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateDateString() {
        return createDateString;
    }

    public void setCreateDateString(String createDateString) {
        this.createDateString = createDateString;
    }

    public String getSellReturnedPurchaseOrderNum() {
        return sellReturnedPurchaseOrderNum;
    }

    public void setSellReturnedPurchaseOrderNum(String sellReturnedPurchaseOrderNum) {
        this.sellReturnedPurchaseOrderNum = sellReturnedPurchaseOrderNum;
    }

    public String getSellPurchaseOrderNum() {
        return sellPurchaseOrderNum;
    }

    public void setSellPurchaseOrderNum(String sellPurchaseOrderNum) {
        this.sellPurchaseOrderNum = sellPurchaseOrderNum;
    }

    public Double getRealReturnCashAmount() {
        return realReturnCashAmount;
    }

    public void setRealReturnCashAmount(Double realReturnCashAmount) {
        this.realReturnCashAmount = realReturnCashAmount;
    }

    public Double getRefundTotalAmount() {
        return refundTotalAmount;
    }

    public void setRefundTotalAmount(Double refundTotalAmount) {
        this.refundTotalAmount = refundTotalAmount;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
