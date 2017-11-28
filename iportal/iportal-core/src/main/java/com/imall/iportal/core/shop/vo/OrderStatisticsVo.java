package com.imall.iportal.core.shop.vo;

/**
 * 订单统计（微店全部订单列表）
 */
public class OrderStatisticsVo {
    /**
     * 统计天数
     */
    private Integer statisticsDays;

    /**
     * 下单数量
     */
    private Integer orderQuantity;

    /**
     * 成交金额
     */
    private Double totalAmount;

    /**
     * 待发货订单数量
     */
    private Integer waitSendQuantity;

    /**
     * 已发货数量
     */
    private Integer alreadySendedQuantity;

    public Integer getStatisticsDays() {
        return statisticsDays;
    }

    public void setStatisticsDays(Integer statisticsDays) {
        this.statisticsDays = statisticsDays;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getWaitSendQuantity() {
        return waitSendQuantity;
    }

    public void setWaitSendQuantity(Integer waitSendQuantity) {
        this.waitSendQuantity = waitSendQuantity;
    }

    public Integer getAlreadySendedQuantity() {
        return alreadySendedQuantity;
    }

    public void setAlreadySendedQuantity(Integer alreadySendedQuantity) {
        this.alreadySendedQuantity = alreadySendedQuantity;
    }
}
