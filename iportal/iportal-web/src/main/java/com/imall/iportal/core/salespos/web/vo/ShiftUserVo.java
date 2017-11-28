package com.imall.iportal.core.salespos.web.vo;

import com.imall.iportal.core.main.entity.SysUser;

import java.util.List;

/**
 * Created by ygw on 2017/8/2.
 */
public class ShiftUserVo {

    private Long succeedWhoId;

    private List<SysUser> userList;

    public Long getSucceedWhoId() {
        return succeedWhoId;
    }

    public void setSucceedWhoId(Long succeedWhoId) {
        this.succeedWhoId = succeedWhoId;
    }

    public List<SysUser> getUserList() {
        return userList;
    }

    public void setUserList(List<SysUser> userList) {
        this.userList = userList;
    }
}
