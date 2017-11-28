package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by lxh on 2017/7/17.
 * 快速收货项保存VO
 */
public class FastReceiveItemSaveVo {
    //商品ID
    @NotNull
    private Long goodsId;
    //商品批号
    @NotNull
    @Length(max = 32)
    private String goodsBatch;
    //商品批次ID
    private Long goodsBatchId;
    //生产日期
    @NotNull
    private String productionDateString;
    //有效期至
    @NotNull
    private String validityString;
    //到货数量
    @NotNull
    @Min(1)
    private Long goodsArrivalQuantity;
    //采购单价
    @NotNull
    @Min(0)
    private Double purchaseUnitPrice;
    //合格数量
    @NotNull
    @Min(0)
    private Long qualifiedQuantity;
    //抽样数量
    @NotNull
    @Min(0)
    private Long sampleQuantity;
    //零售价
    @NotNull
    @Min(0)
    private Double retailPrice;
    //灭菌批次
    private String sterilizationBatch;
    //验收报告
    private String acceptanceRep;
    //货位
    private String goodsAllocation;
    //验收结论
    private String acceptanceConclusion;
    //拒收理由
    private String rejectionReason;
    //货位ID
    @NotNull
    private Long storageSpaceId;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsBatch() {
        return goodsBatch;
    }

    public void setGoodsBatch(String goodsBatch) {
        this.goodsBatch = goodsBatch;
    }

    public Long getGoodsBatchId() {
        return goodsBatchId;
    }

    public void setGoodsBatchId(Long goodsBatchId) {
        this.goodsBatchId = goodsBatchId;
    }

    public String getProductionDateString() {
        return productionDateString;
    }

    public void setProductionDateString(String productionDateString) {
        this.productionDateString = productionDateString;
    }

    public String getValidityString() {
        return validityString;
    }

    public void setValidityString(String validityString) {
        this.validityString = validityString;
    }

    public Long getGoodsArrivalQuantity() {
        return goodsArrivalQuantity;
    }

    public void setGoodsArrivalQuantity(Long goodsArrivalQuantity) {
        this.goodsArrivalQuantity = goodsArrivalQuantity;
    }

    public Double getPurchaseUnitPrice() {
        return purchaseUnitPrice;
    }

    public void setPurchaseUnitPrice(Double purchaseUnitPrice) {
        this.purchaseUnitPrice = purchaseUnitPrice;
    }

    public Long getQualifiedQuantity() {
        return qualifiedQuantity;
    }

    public void setQualifiedQuantity(Long qualifiedQuantity) {
        this.qualifiedQuantity = qualifiedQuantity;
    }

    public Long getSampleQuantity() {
        return sampleQuantity;
    }

    public void setSampleQuantity(Long sampleQuantity) {
        this.sampleQuantity = sampleQuantity;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getSterilizationBatch() {
        return sterilizationBatch;
    }

    public void setSterilizationBatch(String sterilizationBatch) {
        this.sterilizationBatch = sterilizationBatch;
    }

    public String getAcceptanceRep() {
        return acceptanceRep;
    }

    public void setAcceptanceRep(String acceptanceRep) {
        this.acceptanceRep = acceptanceRep;
    }

    public String getGoodsAllocation() {
        return goodsAllocation;
    }

    public void setGoodsAllocation(String goodsAllocation) {
        this.goodsAllocation = goodsAllocation;
    }

    public String getAcceptanceConclusion() {
        return acceptanceConclusion;
    }

    public void setAcceptanceConclusion(String acceptanceConclusion) {
        this.acceptanceConclusion = acceptanceConclusion;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public Long getStorageSpaceId() {
        return storageSpaceId;
    }

    public void setStorageSpaceId(Long storageSpaceId) {
        this.storageSpaceId = storageSpaceId;
    }
}
