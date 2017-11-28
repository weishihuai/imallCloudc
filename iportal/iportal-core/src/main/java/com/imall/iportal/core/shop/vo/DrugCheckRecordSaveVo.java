package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by frt on 2017/8/4.
 */
public class DrugCheckRecordSaveVo {
    /**
     *门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     * 检查 单码
     */
    @NotBlank
    @Length(max = 32)
    private String checkNum;

    /**
     *检查 时间
     */
    @NotNull
    private java.util.Date checkTime;

    /**
     *审核人 ID
     */
    @NotNull
    private Long approveManId;

    /**
     * 商品 编码
     */
    @NotBlank
    @Length(max = 128)
    private String goodsCode;

    /**
     * 商品 名称
     */
    @NotBlank
    @Length(max = 128)
    private String goodsNm;

    /**
     * 商品 名称 首拼
     */
    @NotBlank
    @Length(max = 64)
    private String goodsNmFirstSpell;

    /**
     * 通用 名称
     */
    @NotBlank
    @Length(max = 64)
    private String commonNm;

    /**
     * 规格
     */
    @NotBlank
    @Length(max = 32)
    private String spec;

    /**
     * 剂型
     */
    @Length(max = 32)
    private String dosageForm;

    /**
     * 单位
     */
    @NotBlank
    @Length(max = 32)
    private String unit;

    /**
     * 生产 厂商
     */
    @NotBlank
    @Length(max = 64)
    private String manufacture;

    /**
     * 批准文号
     */
    @Length(max = 64)
    private String approvalNumber;

    /**
     * 产地
     */
    @Length(max = 32)
    private String productionPlace;

    /**
     * 批号
     */
    @NotBlank
    @Length(max = 32)
    private String batch;

    /**
     *生产 时间
     */
    @NotNull
    private java.util.Date produceTime;

    /**
     *有效期至
     */
    @NotNull
    private java.util.Date validDate;

    /**
     * 货位
     */
    @NotBlank
    @Length(max = 32)
    private String storageSpaceNm;

    /**
     *库存 数量
     */
    @NotNull
    private Long stockQuantity;

    /**
     *检查 数量
     */
    @NotNull
    private Long checkQuantity;

    /**
     * 检查 项目
     */
    @Length(max = 128)
    private String checkPrj;

    /**
     *不合格 数量
     */
    @NotNull
    private Long disqualificationQuantity;

    /**
     * 处理 意见
     */
    @Length(max = 128)
    private String processSuggest;

    /**
     * 结论
     */
    @Length(max = 128)
    private String verdict;

    /**
     * 备注
     */
    @Length(max = 256)
    private String remark;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public String getGoodsNmFirstSpell() {
        return goodsNmFirstSpell;
    }

    public void setGoodsNmFirstSpell(String goodsNmFirstSpell) {
        this.goodsNmFirstSpell = goodsNmFirstSpell;
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

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
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

    public Date getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Date produceTime) {
        this.produceTime = produceTime;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getStorageSpaceNm() {
        return storageSpaceNm;
    }

    public void setStorageSpaceNm(String storageSpaceNm) {
        this.storageSpaceNm = storageSpaceNm;
    }

    public Long getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Long getCheckQuantity() {
        return checkQuantity;
    }

    public void setCheckQuantity(Long checkQuantity) {
        this.checkQuantity = checkQuantity;
    }

    public String getCheckPrj() {
        return checkPrj;
    }

    public void setCheckPrj(String checkPrj) {
        this.checkPrj = checkPrj;
    }

    public Long getDisqualificationQuantity() {
        return disqualificationQuantity;
    }

    public void setDisqualificationQuantity(Long disqualificationQuantity) {
        this.disqualificationQuantity = disqualificationQuantity;
    }

    public String getProcessSuggest() {
        return processSuggest;
    }

    public void setProcessSuggest(String processSuggest) {
        this.processSuggest = processSuggest;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
