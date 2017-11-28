package com.imall.iportal.core.shop.vo;

/**
 * Created by ou on 2017/7/7.
 */
public class ChineseMedicinePiecesLoadingBucketRecordPageVo {
    /**
     * 记录 id
     */
    private String id;

    /**
     * 装斗 人 姓名
     */
    private String loadingBucketManNm;
    /**
     * 装斗 数量
     */
    private Long loadingBucketQuantity;
    /**
     * 装斗 日期
     */
    private String loadingBucketDateString;
    /**
     * 审核人 姓名
     */
    private String approveManName;
    /**
     * 商品名称
     */
    private String goodsNm;
    /**
     * 通用名称
     */
    private String commonNm;
    /**
     * 规格
     */
    private String spec;

    /**
     * 生产厂商
     */
    private String produceManufacturer;

    /**
     * 产地
     */
    private String productionPlace;
    /**
     * 批号
     */
    private String batch;
    /**
     * 生产日期
     */
    private String produceTimeString;
    /**
     *有效期至
     */
    private String validDateString;
    /**
     *原 货位
     */
    private String originalStorageSpaceNm;
    /**
     *目标 货位
     */
    private String targetStorageSpaceNm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoadingBucketManNm() {
        return loadingBucketManNm;
    }

    public void setLoadingBucketManNm(String loadingBucketManNm) {
        this.loadingBucketManNm = loadingBucketManNm;
    }

    public Long getLoadingBucketQuantity() {
        return loadingBucketQuantity;
    }

    public void setLoadingBucketQuantity(Long loadingBucketQuantity) {
        this.loadingBucketQuantity = loadingBucketQuantity;
    }

    public String getLoadingBucketDateString() {
        return loadingBucketDateString;
    }

    public void setLoadingBucketDateString(String loadingBucketDateString) {
        this.loadingBucketDateString = loadingBucketDateString;
    }

    public String getApproveManName() {
        return approveManName;
    }

    public void setApproveManName(String approveManName) {
        this.approveManName = approveManName;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public String getCommonNm() {
        return commonNm;
    }

    public void setCommonNm(String commonNm) {
        this.commonNm = commonNm;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getProduceManufacturer() {
        return produceManufacturer;
    }

    public void setProduceManufacturer(String produceManufacturer) {
        this.produceManufacturer = produceManufacturer;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getProduceTimeString() {
        return produceTimeString;
    }

    public void setProduceTimeString(String produceTimeString) {
        this.produceTimeString = produceTimeString;
    }

    public String getValidDateString() {
        return validDateString;
    }

    public void setValidDateString(String validDateString) {
        this.validDateString = validDateString;
    }

    public String getOriginalStorageSpaceNm() {
        return originalStorageSpaceNm;
    }

    public void setOriginalStorageSpaceNm(String originalStorageSpaceNm) {
        this.originalStorageSpaceNm = originalStorageSpaceNm;
    }

    public String getTargetStorageSpaceNm() {
        return targetStorageSpaceNm;
    }

    public void setTargetStorageSpaceNm(String targetStorageSpaceNm) {
        this.targetStorageSpaceNm = targetStorageSpaceNm;
    }
}
