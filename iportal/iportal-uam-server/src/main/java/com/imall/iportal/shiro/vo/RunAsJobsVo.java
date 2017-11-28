package com.imall.iportal.shiro.vo;

import com.imall.iportal.core.main.entity.SysJob;
import com.imall.iportal.core.main.vo.SysUserOrgJobVo;

import java.util.List;

/**
 * Created by yang on 2015-10-28.
 */
public class RunAsJobsVo {

    private SysJob currJob;
    private List<SysUserOrgJobVo> jobs;
    private Boolean isRunas;
    private SysJob prevJob;

    public SysJob getCurrJob() {
        return currJob;
    }

    public void setCurrJob(SysJob currJob) {
        this.currJob = currJob;
    }

    public Boolean getIsRunas() {
        return isRunas;
    }

    public void setIsRunas(Boolean isRunas) {
        this.isRunas = isRunas;
    }

    public SysJob getPrevJob() {
        return prevJob;
    }

    public void setPrevJob(SysJob prevJob) {
        this.prevJob = prevJob;
    }

    public List<SysUserOrgJobVo> getJobs() {
        return jobs;
    }

    public void setJobs(List<SysUserOrgJobVo> jobs) {
        this.jobs = jobs;
    }
}
