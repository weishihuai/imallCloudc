package com.imall.iportal.core.main.vo;

import java.util.List;

/**
 * Created by ygw on 2017/7/21.
 */
public class SysUserBatchStateVo {

    private List<Long> ids;
    private String isEnable;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }
}
