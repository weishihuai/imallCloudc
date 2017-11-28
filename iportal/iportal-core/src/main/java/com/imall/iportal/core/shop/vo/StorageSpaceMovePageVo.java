package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.StorageSpaceMove;

/**
 * Created by wsh on 2017/7/25.
 * 货位移动列表VO对象
 */
public class StorageSpaceMovePageVo extends StorageSpaceMove {
    /**
     * 移动时间
     */
    private String moveTimeString;

    public String getMoveTimeString() {
        return moveTimeString;
    }

    public void setMoveTimeString(String moveTimeString) {
        this.moveTimeString = moveTimeString;
    }
}
