package com.imall.iportal.core.shop.vo;

/**
 * Created by lxh on 2017/8/2.
 * 订单统计VO
 */
public class OrderStatVo {
    //应收金额
    private Double amountReceivable;
    //实收金额
    private Double amountReceived;
    //毛利
    private Double profit;
    //商品数量总计
    private Long goodsTotalNum;

    public Double getAmountReceivable() {
        return amountReceivable;
    }

    public void setAmountReceivable(Double amountReceivable) {
        this.amountReceivable = amountReceivable;
    }

    public Double getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(Double amountReceived) {
        this.amountReceived = amountReceived;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Long getGoodsTotalNum() {
        return goodsTotalNum;
    }

    public void setGoodsTotalNum(Long goodsTotalNum) {
        this.goodsTotalNum = goodsTotalNum;
    }
}
