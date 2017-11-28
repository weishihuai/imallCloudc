package com.imall.iportal.shiro.controller;

import com.imall.commons.base.global.Global;
import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.vo.ResultVo;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysJob;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.entity.SysUserOrgJob;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.main.vo.SysUserOrgJobVo;
import com.imall.iportal.core.main.vo.TagAuthVo;
import com.imall.iportal.core.platform.entity.Shop;
import com.imall.iportal.core.shop.entity.WeShop;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import com.imall.iportal.shiro.vo.RunAsJobsVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang on 2015-10-28.
 */
//TODO yang
@Controller
@RequestMapping("/runas")
public class RunAsController {

    @RequestMapping(value = "/findAllJobs",method = RequestMethod.GET)
    @ResponseBody
    public Object findAllJobs(ModelMap model, @CurrUser CurrUserVo currUser) {
        if(currUser!=null){
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            RunAsJobsVo jobsVo = new RunAsJobsVo();
            TagAuthVo tagAuthVo = (TagAuthVo)session.getAttribute(Global.SESSION_CACHE_TAG_AUTH);
            if(tagAuthVo ==null){
                //缓存失效需要重新加载
                SysUserOrgJob userOrgJob = ServiceManager.sysUserOrgJobService.getIsmainByUserId(currUser.getUserId());
                if(userOrgJob!=null){
                    tagAuthVo = ServiceManager.sysAuthService.getTagAuthVoByJobId(userOrgJob.getJobId());
                    tagAuthVo.setUserId(currUser.getUserId());
                    session.setAttribute(Global.SESSION_CACHE_TAG_AUTH, tagAuthVo);
                }
            }
            SysJob currJob = ServiceManager.sysJobService.findOne(tagAuthVo.getJobId());
            jobsVo.setCurrJob(currJob);
            jobsVo.setIsRunas(subject.isRunAs());
         /*   if(subject.isRunAs()) {
                if(tagAuthVo.getPrevJobId()!=null){
                    SysJobMsg prevJob = jobClient.findOne(tagAuthVo.getPrevJobId());
                    jobsVo.setPrevJob(prevJob);
                }
            }*/
            List<SysUserOrgJobVo> userOrgJobList = ServiceManager.sysUserOrgJobService.findVoByUserId(currUser.getUserId());
            List<SysUserOrgJobVo> jobList = new ArrayList<SysUserOrgJobVo>();
            for(SysUserOrgJobVo userOrgJob:userOrgJobList){
                //排除当前岗位和前一个岗位
                if(!userOrgJob.getJobId().equals(tagAuthVo.getJobId())){ //&& !userOrgJob.getJobId().equals(tagAuthVo.getPrevJobId())
                    jobList.add(userOrgJob);
                }
            }
            jobsVo.setJobs(jobList);
            return jobsVo;
        }
        return "success";
    }

    @RequestMapping("/switchTo")
    @ResponseBody
    public Object switchTo(ModelMap model, @CurrUser CurrUserVo currUser, Long id) { //id为SysUserOrgJob的id
        if(id==null){
            throw new BusinessException(ResGlobal.SWITCH_JOB_ID_NULL_ERROR);
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        TagAuthVo tagAuthVo = (TagAuthVo)session.getAttribute(Global.SESSION_CACHE_TAG_AUTH);
        //缓存失效需要重新加载
        SysUserOrgJob userOrgJob = ServiceManager.sysUserOrgJobService.getIsmainByUserId(currUser.getUserId());
        if(userOrgJob!=null){
            tagAuthVo = ServiceManager.sysAuthService.getTagAuthVoByJobId(userOrgJob.getJobId());
            tagAuthVo.setUserId(currUser.getUserId());
            session.setAttribute(Global.SESSION_CACHE_TAG_AUTH, tagAuthVo);
        }
     /*   if(tagAuthVo.getJobId() == id) {
            //自己不能切换到自己的身份
            throw new BusinessException(ResGlobal.DEFAULT_ERROR_CODE,"切换失败！自己不能切换到自己的岗位！！！");
            //return new ResultVo(true, "自己不能切换到自己的岗位");
        }*/
        SysUserOrgJob nextJob = ServiceManager.sysUserOrgJobService.findOne(id);
        if(!nextJob.getUserId().equals(currUser.getUserId())){
            //没有授予您身份xxx，不能切换
            throw new BusinessException(ResGlobal.SWITCH_JOB_IDENTITY_AUTH_ERROR);
            //return new ResultVo(true, "没有授予您身份xxx，不能切换");
        }

        TagAuthVo newTagAuthVo = ServiceManager.sysAuthService.getTagAuthVoByJobId(nextJob.getJobId());
        tagAuthVo.setPrevJobId(tagAuthVo.getJobId());
        tagAuthVo.setJobId(nextJob.getJobId());
        tagAuthVo.setRolesSet(newTagAuthVo.getRolesSet());
        tagAuthVo.setPermissionsSet(newTagAuthVo.getPermissionsSet());
        subject.runAs(new SimplePrincipalCollection(currUser.getUserName(), ""));
        ServiceManager.sysUserOrgJobService.updateJobIsmain(currUser.getUserId(), nextJob.getJobId());

        //缓存失效需要重新加载
        session.setAttribute(Global.SESSION_CACHE_TAG_AUTH, tagAuthVo);

        //当前的主岗位 更新到session
        CurrUserVo currUserVo = ServiceManager.sysUserService.getCurrUserVo(nextJob.getUserId(), nextJob.getId());
        session.setAttribute(Global.CURR_USER,currUserVo);

        return new ResultVo(true, "操作成功");
    }

    @RequestMapping("/switchBack")
  @ResponseBody
    public Object switchBack(ModelMap model) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isRunAs()) {
            subject.releaseRunAs();
        }
        return new ResultVo(true, "操作成功");
    }

}