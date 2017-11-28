package com.imall.iportal.core.weshop.vo;

import java.util.List;

/**
 * 发货订单项详情
 */
public class OrderItemSendDetailVo {
    private Long id;

    /**
     * 通用 名称
     */
    private String commonNm;

    /**
     * 数量
     */
    private Long quantity;

    /**
     * 是否动态新增的发货信息
     */
    private Boolean isAdd;

    /**
     * 当前 库存
     */
    private Long currentStock;

    /**
     * 安全 库存
     */
    private Long securityStock;

    /**
     * 批次信息（在售、当前库存 > 0）
     */
    private List<GoodsBatchDetailVo> batchList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommonNm() {
        return commonNm;
    }

    public void setCommonNm(String commonNm) {
        this.commonNm = commonNm;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Boolean getAdd() {
        return isAdd;
    }

    public void setAdd(Boolean add) {
        isAdd = add;
    }

    public List<GoodsBatchDetailVo> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<GoodsBatchDetailVo> batchList) {
        this.batchList = batchList;
    }

    public Long getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Long currentStock) {
        this.currentStock = currentStock;
    }

    public Long getSecurityStock() {
        return securityStock;
    }

    public void setSecurityStock(Long securityStock) {
        this.securityStock = securityStock;
    }
}
