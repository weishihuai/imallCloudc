package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by ou on 2017/7/10.
 */
public class DrugLockDealUpdateVo {
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;
    /**
     * 处理 时间
     */
    private String processTimeString;
    /**
     * 处理 原因
     */
    private String processReason;
    /**
     * 审核 人 ID
     */
    @NotNull
    private Long approveManId;
    /**
     * 处理结果
     */
    private String processResultCode;
    /**
     * 商品信息集合
     */
    @NotEmpty
    private List<DrugLockDealIdsVo> drugLockDealGoodsVoList;

    public String getProcessResultCode() {
        return processResultCode;
    }

    public void setProcessResultCode(String processResultCode) {
        this.processResultCode = processResultCode;
    }

    public String getProcessTimeString() {
        return processTimeString;
    }

    public void setProcessTimeString(String processTimeString) {
        this.processTimeString = processTimeString;
    }

    public String getProcessReason() {
        return processReason;
    }

    public void setProcessReason(String processReason) {
        this.processReason = processReason;
    }

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<DrugLockDealIdsVo> getDrugLockDealGoodsVoList() {
        return drugLockDealGoodsVoList;
    }

    public void setDrugLockDealGoodsVoList(List<DrugLockDealIdsVo> drugLockDealGoodsVoList) {
        this.drugLockDealGoodsVoList = drugLockDealGoodsVoList;
    }
}
