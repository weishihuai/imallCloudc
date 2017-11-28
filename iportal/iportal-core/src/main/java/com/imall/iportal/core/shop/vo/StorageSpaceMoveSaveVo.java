package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by wsh on 2017/7/25.
 * 货位移动保存 VO对象
 */
public class StorageSpaceMoveSaveVo {
    /**
     * 移动单号
     */
    private String moveNum;
    /**
     * 移动人姓名
     */
    @NotBlank
    @Length(max = 32)
    private String moveManName;
    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;
    /**
     * 移动时间
     */
    @NotBlank
    private String moveTimeString;
    /**
     *  审核 人 ID
     */
    @NotNull
    private Long approveManId;
    /**
     * 备注
     */
    private String remark;
    /**
     *  保存 商品
     */
    @NotEmpty
    private List<StorageSpaceMoveGoodsSaveVo> storageSpaceMoveGoodsSaveVoList;

    public String getMoveManName() {
        return moveManName;
    }

    public void setMoveManName(String moveManName) {
        this.moveManName = moveManName;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getMoveTimeString() {
        return moveTimeString;
    }

    public void setMoveTimeString(String moveTimeString) {
        this.moveTimeString = moveTimeString;
    }

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<StorageSpaceMoveGoodsSaveVo> getStorageSpaceMoveGoodsSaveVoList() {
        return storageSpaceMoveGoodsSaveVoList;
    }

    public void setStorageSpaceMoveGoodsSaveVoList(List<StorageSpaceMoveGoodsSaveVo> storageSpaceMoveGoodsSaveVoList) {
        this.storageSpaceMoveGoodsSaveVoList = storageSpaceMoveGoodsSaveVoList;
    }

    public String getMoveNum() {
        return moveNum;
    }

    public void setMoveNum(String moveNum) {
        this.moveNum = moveNum;
    }
}
