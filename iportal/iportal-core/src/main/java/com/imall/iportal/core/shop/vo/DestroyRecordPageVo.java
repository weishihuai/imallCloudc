package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.entity.DestroyRecord;

/**
 * Created by frt on 2017/7/7.
 */
public class DestroyRecordPageVo extends DestroyRecord {
    /**
     * 审核 人
     */
    private String approveMan;

    /**
     * 复核人
     */
    private String reviewer;

    /**
     * 生产日期
     */
    private String produceDateString;

    /**
     * 有效期
     */
    private String validDateString;

    /**
     * 销毁时间
     */
    private String destroyTimeString;

    public String getApproveMan() {
        return approveMan;
    }

    public void setApproveMan(String approveMan) {
        this.approveMan = approveMan;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    @Override
    public void setApproveManId(Long value) {
        if(value!=null){
            SysUser sysUser = ServiceManager.sysUserService.findOne(value);
            this.setApproveMan(sysUser.getUserName());
        }

        super.setApproveManId(value);
    }

    @Override
    public void setReviewerId(Long value) {
        if(value!=null){
            SysUser sysUser = ServiceManager.sysUserService.findOne(value);
            this.setReviewer(sysUser.getUserName());
        }
        super.setReviewerId(value);
    }

    public String getProduceDateString() {
        return produceDateString;
    }

    public void setProduceDateString(String produceDateString) {
        this.produceDateString = produceDateString;
    }

    public String getValidDateString() {
        return validDateString;
    }

    public void setValidDateString(String validDateString) {
        this.validDateString = validDateString;
    }

    public String getDestroyTimeString() {
        return destroyTimeString;
    }

    public void setDestroyTimeString(String destroyTimeString) {
        this.destroyTimeString = destroyTimeString;
    }
}
