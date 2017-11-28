package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by wsh on 2017/7/12.
 * 商品清斗保存 VO对象
 */
public class DrugClearBucketSaveVo {

    /**
     * 清斗 人 姓名
     */
    @NotBlank
    @Length(max = 32)
    private String clearBucketManName;
    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     * 清斗时间
     */
    private String clearBucketTimeString;
    /**
     *  审核 人 ID
     */
    @NotNull
    private Long approveManId;

    /**
     *  保存 商品
     */
    @NotEmpty
    private List<DrugClearBucketGoodsSaveVo> drugClearBucketGoodsSaveVoList;

    public List<DrugClearBucketGoodsSaveVo> getDrugClearBucketGoodsSaveVoList() {
        return drugClearBucketGoodsSaveVoList;
    }

    public void setDrugClearBucketGoodsSaveVoList(List<DrugClearBucketGoodsSaveVo> drugClearBucketGoodsSaveVoList) {
        this.drugClearBucketGoodsSaveVoList = drugClearBucketGoodsSaveVoList;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }

    public String getClearBucketManName() {
        return clearBucketManName;
    }

    public void setClearBucketManName(String clearBucketManName) {
        this.clearBucketManName = clearBucketManName;
    }

    public String getClearBucketTimeString() {
        return clearBucketTimeString;
    }

    public void setClearBucketTimeString(String clearBucketTimeString) {
        this.clearBucketTimeString = clearBucketTimeString;
    }
}
