package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.StorageSpaceMove;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsh on 2017/7/25.
 * 货位移动详情信息
 */
public class StorageSpaceMoveDetailVo extends StorageSpaceMove {
    /**
     * 审核人名称
     */
    private String approveManName;
    /**
     * 移动时间
     */
    private String moveTimeString;
    /**
     * 商品信息集合
     */
    private List<StorageSpaceMoveGoodsVo> storageSpaceMoveGoodsVoList = new ArrayList<>();

    public List<StorageSpaceMoveGoodsVo> getStorageSpaceMoveGoodsVoList() {
        return storageSpaceMoveGoodsVoList;
    }

    public void setStorageSpaceMoveGoodsVoList(List<StorageSpaceMoveGoodsVo> storageSpaceMoveGoodsVoList) {
        this.storageSpaceMoveGoodsVoList = storageSpaceMoveGoodsVoList;
    }

    public String getApproveManName() {
        return approveManName;
    }

    public void setApproveManName(String approveManName) {
        this.approveManName = approveManName;
    }

    @Override
    public String getMoveTimeString() {
        return moveTimeString;
    }

    @Override
    public void setMoveTimeString(String moveTimeString) {
        this.moveTimeString = moveTimeString;
    }
}
