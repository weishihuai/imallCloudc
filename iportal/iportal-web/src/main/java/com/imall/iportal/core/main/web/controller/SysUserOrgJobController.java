package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUserOrgJob;
import com.imall.iportal.core.main.valid.SysUserOrgJobUpdateValid;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.main.vo.SysUserOrgJobVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Donie on 2015/10/08.
 */
@Controller
@RequestMapping("/sysUserOrgJob")
public class SysUserOrgJobController extends BaseRestSpringController {


    @RequestMapping(value = "/findByUserId",method = RequestMethod.GET)
    @ResponseBody
    public Object findByUserId(ModelMap model, Long userId){
        if(userId==null){
            return new ArrayList<SysUserOrgJobVo>();
        }
        return ServiceManager.sysUserOrgJobService.findVoByUserId(userId);
    }

    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(ModelMap model,Long id) {
        if(id!=null){
            return ServiceManager.sysUserOrgJobService.findOneVo(id);
        }
        return getSuccess();
    }
    @RequestMapping(value = "/findByOrgId",method = RequestMethod.GET)
    @ResponseBody
    public Object findByOrgId(ModelMap model,@CurrUser CurrUserVo currUserVo) {
        return ServiceManager.sysUserOrgJobService.findByOrgId( currUserVo.getOrgId());

    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(ModelMap model,@RequestBody List<Long> jobIds, Long userId, Long orgId) {
        for(Long jobId:jobIds){
            ServiceManager.sysUserOrgJobService.saveOrUpdate(userId, orgId, jobId, BoolCodeEnum.NO.toCode());
        }
        return getSuccess();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(ModelMap model, @RequestBody SysUserOrgJobUpdateValid userOrgJobUpdateValid) {
        ServiceManager.sysUserOrgJobService.update(userOrgJobUpdateValid);
        return getSuccess();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Object delete(ModelMap model,@RequestBody List<Long> ids) {
        ServiceManager.sysUserOrgJobService.delete(ids);
        return getSuccess();
    }

    @RequestMapping(value = "/updateJobIsmain",method = RequestMethod.POST)
    @ResponseBody
    public Object updateJobIsmain(ModelMap model,@RequestBody Long id) {
        SysUserOrgJob userOrgJob = ServiceManager.sysUserOrgJobService.findOne(id);
        ServiceManager.sysUserOrgJobService.updateJobIsmain(userOrgJob.getUserId(), userOrgJob.getJobId());
        return getSuccess();
    }
}
