package com.imall.iportal.core.main.web.vo;

import com.imall.iportal.core.main.vo.SysFileLibVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZJC on 2016/1/4.
 */
public class FileLibResultVo {
    Long totalCount;
    int totalPage;
    List<SysFileLibVo>  sysFileLibList = new ArrayList<SysFileLibVo>();

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<SysFileLibVo> getSysFileLibList() {
        return sysFileLibList;
    }

    public void setSysFileLibList(List<SysFileLibVo> sysFileLibList) {
        this.sysFileLibList = sysFileLibList;
    }
}
