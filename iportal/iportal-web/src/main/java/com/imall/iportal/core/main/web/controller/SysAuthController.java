package com.imall.iportal.core.main.web.controller;

/**
 * Created by Donie on 2015/10/10.
 */

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysAuth;
import com.imall.iportal.core.main.vo.AuthParamsVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sysAuth")
public class SysAuthController extends BaseRestSpringController {

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(ModelMap model,@PageableDefault(page = 0, size = 10) Pageable pageable){
        return  ServiceManager.sysAuthService.findAll(pageable);
    }

    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id){
        return ServiceManager.sysAuthService.findOne(id);
    }

    @RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdate(@RequestBody SysAuth sysAuth){
        ServiceManager.sysAuthService.save(sysAuth);
        return getSuccess();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public  Object delete(@RequestBody List<Long> ids){
        ServiceManager.sysAuthService.delete(ids);
        return getSuccess();
    }

    @RequestMapping(value = "/findRoleIdsByJobId",method = RequestMethod.GET)
    @ResponseBody
    public Object findRoleIdsByJobId(ModelMap model,Long jobId){
        return ServiceManager.sysAuthService.findRoleIdsByJobId(jobId);
    }

    @RequestMapping(value = "/findRolesByJobId/{jobId}",method = RequestMethod.GET)
    @ResponseBody
    public Object findRolesByJobId(ModelMap model,@PageableDefault(page = 0, size = 10) Pageable pageable,@PathVariable java.lang.Long jobId, String roleName, String roleDescription){
        AuthParamsVo authParamsVo = new AuthParamsVo();
        authParamsVo.setJobId(jobId);
        authParamsVo.setRoleName(roleName);
        authParamsVo.setRoleDescription(roleDescription);
        return  ServiceManager.sysAuthService.findRolesByJobId(pageable, authParamsVo);
    }

    @RequestMapping(value = "/saveJobRole",method = RequestMethod.POST)
    @ResponseBody
    public Object saveJobRole(Long jobId,Long[] roleIds){
        ServiceManager.sysAuthService.saveJobRole(jobId,roleIds);
        return getSuccess();
    }

    @RequestMapping(value = "/deleteJobRole",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteJobRole(Long jobId,Long[] roleIds){
        ServiceManager.sysAuthService.deleteJobRole(jobId,roleIds);
        return getSuccess();
    }

}
