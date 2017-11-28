package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysRole;
import com.imall.iportal.core.main.valid.SysRoleSaveValid;
import com.imall.iportal.core.main.valid.SysRoleUpdateValid;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Donie on 2015/9/21.
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseRestSpringController {


    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Object list(ModelMap model, @CurrUser CurrUserVo currUserVo, @PageableDefault(page = 0, size = 10) Pageable pageable){
        return ServiceManager.sysRoleService.query(pageable, currUserVo.getOrgId());
    }

    @ResponseBody
    @RequestMapping(value = "/findByOrgId",method = RequestMethod.GET)
    public Object findByOrgId(ModelMap model, @CurrUser CurrUserVo currUserVo){
        return ServiceManager.sysRoleService.findByOrgId( currUserVo.getOrgId());
    }


    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id){
        return ServiceManager.sysRoleService.findOne(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save( @CurrUser CurrUserVo currUserVo, @RequestBody SysRoleSaveValid sysRoleSaveValid){
        sysRoleSaveValid.setOrgId(currUserVo.getOrgId());
        sysRoleSaveValid.setIsDefaultAdmin(BoolCodeEnum.NO.toCode());
        ServiceManager.sysRoleService.save(sysRoleSaveValid);
        return getSuccess();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update( @CurrUser CurrUserVo currUserVo, @RequestBody SysRoleUpdateValid sysRoleUpdateValid){
        sysRoleUpdateValid.setOrgId(currUserVo.getOrgId());
        ServiceManager.sysRoleService.update(sysRoleUpdateValid);
        return getSuccess();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public  Object delete(@RequestBody List<Long> ids){
        ServiceManager.sysRoleService.delete(ids);
        return getSuccess();
    }

    @RequestMapping(value = "/checkRoleCodeIsExist", method = RequestMethod.POST)
    @ResponseBody
    public Object checkRoleCodeIsExist(@RequestBody SysRole sysRole) {
        return ServiceManager.sysRoleService.checkRoleCodeIsExist(sysRole.getRoleCode(), sysRole.getId());
    }

}
