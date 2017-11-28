package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by ou on 2017/7/10.
 */
public class DrugLockUpdateVo {
    /**
     * 药品锁定id
     */
    @NotNull
    private Long id;
    /**
     *  处理 时间
     */
    private String processTimeString;
    /**
     *  处理 原因
     */
    private String processReason;
    /**
     *  审核 人 ID
     */
    @NotNull
    private Long approveManId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessTimeString() {
        return processTimeString;
    }

    public void setProcessTimeString(String processTimeString) {
        this.processTimeString = processTimeString;
    }

    public String getProcessReason() {
        return processReason;
    }

    public void setProcessReason(String processReason) {
        this.processReason = processReason;
    }

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }
}
