package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;

import java.util.Date;

/**
 * Created by frt on 2017/8/4.
 */
public class DrugCuringRecordPageVo {
    /**
     * 养护 计划 单号
     */
    private String curingPlanNum;

    /**
     *养护 日期
     */
    private java.util.Date curingDate;

    /**
     *养护 人
     */
    private Long curingManId;

    /**
     * 商品 编码
     */
    private String goodsCode;

    /**
     * 商品 名称
     */
    private String goodsNm;

    /**
     * 通用 名称
     */
    private String commonNm;

    /**
     * 规格
     */
    private String spec;

    /**
     * 剂型
     */
    private String dosageForm;

    /**
     * 单位
     */
    private String unit;

    /**
     * 生产 厂商
     */
    private String manufacture;

    /**
     * 产地
     */
    private String productionPlace;

    /**
     * 批准文号
     */
    private String approvalNumber;

    /**
     * 批号
     */
    private String batch;

    /**
     *生产 时间
     */
    private java.util.Date produceTime;

    /**
     *有效期至
     */
    private java.util.Date validDate;

    /**
     * 货位
     */
    private String storageSpaceNm;

    /**
     *库存 数量
     */
    private Long stockQuantity;

    /**
     *养护 数量
     */
    private Long curingQuantity;

    /**
     * 养护 项目
     */
    private String curingPrj;

    /**
     *不合格 数量
     */
    private Long disqualificationQuantity;

    /**
     * 处理 意见
     */
    private String processSuggest;

    /**
     * 结论
     */
    private String verdict;

    /**
     * 备注
     */
    private String remark;

    public String getCuringPlanNum() {
        return curingPlanNum;
    }

    public void setCuringPlanNum(String curingPlanNum) {
        this.curingPlanNum = curingPlanNum;
    }

    public Date getCuringDate() {
        return curingDate;
    }

    public void setCuringDate(Date curingDate) {
        this.curingDate = curingDate;
    }

    public String getCuringDateString(){
        if(this.getCuringDate()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getCuringDate());
        }
    }

    public Long getCuringManId() {
        return curingManId;
    }

    public void setCuringManId(Long curingManId) {
        this.curingManId = curingManId;
    }

    public String getCuringMan(){
        if(this.getCuringManId()==null){
            return "";
        }else{
            SysUser sysUser = ServiceManager.sysUserService.findOne(this.getCuringManId());
            if(sysUser==null){
                return "";
            }else{
                return sysUser.getRealName();
            }
        }
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

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
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

    public String getProduceTimeString(){
        if(this.getProduceTime()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getProduceTime());
        }
    }

    public void setProduceTime(Date produceTime) {
        this.produceTime = produceTime;
    }

    public Date getValidDate() {
        return validDate;
    }

    public String getValidDateString(){
        if(this.getValidDate()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getValidDate());
        }
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

    public Long getCuringQuantity() {
        return curingQuantity;
    }

    public void setCuringQuantity(Long curingQuantity) {
        this.curingQuantity = curingQuantity;
    }

    public String getCuringPrj() {
        return curingPrj;
    }

    public void setCuringPrj(String curingPrj) {
        this.curingPrj = curingPrj;
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
