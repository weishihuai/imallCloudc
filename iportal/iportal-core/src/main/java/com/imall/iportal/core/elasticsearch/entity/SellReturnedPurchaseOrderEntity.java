package com.imall.iportal.core.elasticsearch.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by ygw on 2017/7/31.
 */
public class SellReturnedPurchaseOrderEntity {

    public static final String ID = "id";
    /**
     * 订单ID
     */
    public static final String ORDER_ID = "orderId";
    /**
     * 门店
     */
    public static final String SHOP_ID = "shopId";

    /**
     * 应退 总 金额
     */
    public static final String REFUND_TOTAL_AMOUNT = "refundTotalAmount";


    /**
     * 实退 现金 金额
     */
    public static final String REAL_RETURN_CASH_AMOUNT = "realReturnCashAmount";


    /**
     * 找零
     */
    public static final String RETURN_SMALL = "returnSmall";

    /**
     * 是否 整单 退货
     */
    public static final String IS_OVERALL_RETURNED_PURCHASE = "isOverallReturnedPurchase";

    /**
     * 收款员
     */
    public static final String CASHIER_ID = "cashierId";

    /**
     * 审核 人 ID
     */
    public static final String APPROVE_MAN_ID = "approveManId";
    /**
     * 备注
     */
    public static final String REMARK = "remark";

    /**
     * 退货 时间
     */
    public static final String RETURNED_PURCHASE_TIME = "returnedPurchaseTime";

    /**
     * 销售退货订单编号
     */
    public static final String SELL_RETURNED_PURCHASE_ORDER_NUM = "sellReturnedPurchaseOrderNum";


    /**
     * 会员 卡 号码
     */
    public static final String MEMBER_CARD_NUM = "memberCardNum";

    /**
     * 会员名称
     */
    public static final String NAME = "name";




    private Long id;
    private Long orderId;
    private Long shopId;
    private Double refundTotalAmount;
    private Double realReturnCashAmount;
    private Double returnSmall;
    private Long cashierId;
    private String isOverallReturnedPurchase;
    private Long approveManId;
    private String remark;
    private String sellReturnedPurchaseOrderNum;
    private Date returnedPurchaseTime;
    private List<SellReturnedPurchaseOrderItemEntity> itemList;
    private String name;
    private String memberCardNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberCardNum() {
        return memberCardNum;
    }

    public void setMemberCardNum(String memberCardNum) {
        this.memberCardNum = memberCardNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Double getRefundTotalAmount() {
        return refundTotalAmount;
    }

    public void setRefundTotalAmount(Double refundTotalAmount) {
        this.refundTotalAmount = refundTotalAmount;
    }

    public Double getRealReturnCashAmount() {
        return realReturnCashAmount;
    }

    public void setRealReturnCashAmount(Double realReturnCashAmount) {
        this.realReturnCashAmount = realReturnCashAmount;
    }

    public String getSellReturnedPurchaseOrderNum() {
        return sellReturnedPurchaseOrderNum;
    }

    public void setSellReturnedPurchaseOrderNum(String sellReturnedPurchaseOrderNum) {
        this.sellReturnedPurchaseOrderNum = sellReturnedPurchaseOrderNum;
    }

    public List<SellReturnedPurchaseOrderItemEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<SellReturnedPurchaseOrderItemEntity> itemList) {
        this.itemList = itemList;
    }

    public Double getReturnSmall() {
        return returnSmall;
    }

    public void setReturnSmall(Double returnSmall) {
        this.returnSmall = returnSmall;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    public String getIsOverallReturnedPurchase() {
        return isOverallReturnedPurchase;
    }

    public void setIsOverallReturnedPurchase(String isOverallReturnedPurchase) {
        this.isOverallReturnedPurchase = isOverallReturnedPurchase;
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

    public Date getReturnedPurchaseTime() {
        return returnedPurchaseTime;
    }

    public void setReturnedPurchaseTime(Date returnedPurchaseTime) {
        this.returnedPurchaseTime = returnedPurchaseTime;
    }
}
