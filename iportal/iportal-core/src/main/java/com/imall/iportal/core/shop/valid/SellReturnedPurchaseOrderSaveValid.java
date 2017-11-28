package com.imall.iportal.core.shop.valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by ygw on 2017/8/7.
 * 销售POS前端--退货：结算提交
 */
public class SellReturnedPurchaseOrderSaveValid {

    @NotNull
    private Long cashierId;

    @NotNull
    private Long orderId;

    @NotNull
    private Long approveManId;

    @Length(max = 128)
    private String remark;

    @NotNull
    private Double refundTotalAmount;

    @NotNull
    private Double realReturnCashAmount;

    @NotNull
    private Double returnSmall;

    @NotBlank
    @Length(max = 1)
    private String isOverallReturnedPurchase;

    @Valid
    @NotNull
    @NotEmpty
    private List<ReturnedOrderItem> returnedOrderItems;

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Double getReturnSmall() {
        return returnSmall;
    }

    public void setReturnSmall(Double returnSmall) {
        this.returnSmall = returnSmall;
    }

    public String getIsOverallReturnedPurchase() {
        return isOverallReturnedPurchase;
    }

    public void setIsOverallReturnedPurchase(String isOverallReturnedPurchase) {
        this.isOverallReturnedPurchase = isOverallReturnedPurchase;
    }

    public List<ReturnedOrderItem> getReturnedOrderItems() {
        return returnedOrderItems;
    }

    public void setReturnedOrderItems(List<ReturnedOrderItem> returnedOrderItems) {
        this.returnedOrderItems = returnedOrderItems;
    }

    public static class ReturnedOrderItem{

        @NotNull
        private Long orderItemId;

        @NotNull
        private Long returnedQuantity;

        private Long orderSendOutBatchId;

        public Long getOrderItemId() {
            return orderItemId;
        }

        public void setOrderItemId(Long orderItemId) {
            this.orderItemId = orderItemId;
        }

        public Long getReturnedQuantity() {
            return returnedQuantity;
        }

        public void setReturnedQuantity(Long returnedQuantity) {
            this.returnedQuantity = returnedQuantity;
        }

        public Long getOrderSendOutBatchId() {
            return orderSendOutBatchId;
        }

        public void setOrderSendOutBatchId(Long orderSendOutBatchId) {
            this.orderSendOutBatchId = orderSendOutBatchId;
        }
    }


}
