package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.DrugCuringStateCodeEnum;
import com.imall.commons.dicts.DrugCuringTypeCodeEnum;

import java.util.Date;

/**
 * Created by frt on 2017/7/15.
 */
public class DrugCuringPageVo {
    private Long id;

    /**
     * 门店 ID
     */
    private Long shopId;

    /**
     * 养护 单据 号码
     */
    private String curingDocumentNum;

    /**
     * 计划 养护 时间
     */
    private java.util.Date planCuringTime;

    /**
     * 养护 类型 代码
     */
    private String curingTypeCode;

    /**
     * 养护 类型 代码
     */
    private String curingStateCode;

    /**
     * 养护 完成 时间
     */
    private java.util.Date curingFinishTime;

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

    public String getCuringDocumentNum() {
        return curingDocumentNum;
    }

    public void setCuringDocumentNum(String curingDocumentNum) {
        this.curingDocumentNum = curingDocumentNum;
    }

    public Date getPlanCuringTime() {
        return planCuringTime;
    }

    public void setPlanCuringTime(Date planCuringTime) {
        this.planCuringTime = planCuringTime;
    }

    public String getCuringTypeCode() {
        return curingTypeCode;
    }

    public void setCuringTypeCode(String curingTypeCode) {
        this.curingTypeCode = curingTypeCode;
    }

    public String getCuringStateCode() {
        return curingStateCode;
    }

    public void setCuringStateCode(String curingStateCode) {
        this.curingStateCode = curingStateCode;
    }

    public Date getCuringFinishTime() {
        return curingFinishTime;
    }

    public void setCuringFinishTime(Date curingFinishTime) {
        this.curingFinishTime = curingFinishTime;
    }

    public String getPlanCuringTimeString(){
        if(this.getPlanCuringTime()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getPlanCuringTime());
        }
    }

    public String getCuringTypeName(){
        return DrugCuringTypeCodeEnum.fromCode(this.getCuringTypeCode()).toName();
    }

    public String getCuringStateName(){
        return DrugCuringStateCodeEnum.fromCode(this.getCuringStateCode()).toName();
    }

    public String getCuringFinishTimeString(){
        if(this.getCuringFinishTime()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getCuringFinishTime());
        }
    }
}
