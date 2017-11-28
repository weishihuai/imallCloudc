package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.DrugCheckStateCodeEnum;
import com.imall.commons.dicts.DrugCheckTypeCodeEnum;

import java.util.Date;

/**
 * Created by frt on 2017/7/15.
 */
public class DrugCheckPageVo {
    private Long id;

    /**
     * 门店 ID
     */
    private Long shopId;

    /**
     * 检查 单据 号码
     */
    private String checkDocumentNum;

    /**
     * 计划 检查 时间
     */
    private java.util.Date planCheckTime;

    /**
     * 检查 类型 代码
     */
    private String checkTypeCode;

    /**
     * 检查 状态 代码
     */
    private String checkStateCode;

    /**
     * 检查 完成 时间
     */
    private java.util.Date checkFinishTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getCheckDocumentNum() {
        return checkDocumentNum;
    }

    public void setCheckDocumentNum(String checkDocumentNum) {
        this.checkDocumentNum = checkDocumentNum;
    }

    public Date getPlanCheckTime() {
        return planCheckTime;
    }

    public void setPlanCheckTime(Date planCheckTime) {
        this.planCheckTime = planCheckTime;
    }

    public String getCheckTypeCode() {
        return checkTypeCode;
    }

    public void setCheckTypeCode(String checkTypeCode) {
        this.checkTypeCode = checkTypeCode;
    }

    public String getCheckStateCode() {
        return checkStateCode;
    }

    public void setCheckStateCode(String checkStateCode) {
        this.checkStateCode = checkStateCode;
    }

    public Date getCheckFinishTime() {
        return checkFinishTime;
    }

    public void setCheckFinishTime(Date checkFinishTime) {
        this.checkFinishTime = checkFinishTime;
    }

    public String getPlanCheckTimeString(){
        if(this.getPlanCheckTime()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getPlanCheckTime());
        }
    }

    public String getCheckTypeName(){
        return DrugCheckTypeCodeEnum.fromCode(this.getCheckTypeCode()).toName();
    }

    public String getCheckStateName(){
        return DrugCheckStateCodeEnum.fromCode(this.getCheckStateCode()).toName();
    }

    public String getCheckFinishTimeString(){
        if(this.getCheckFinishTime()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getCheckFinishTime());
        }
    }
}
