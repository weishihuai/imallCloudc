package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2015/10/12.
 */
@Controller
@RequestMapping("/sysRolePermission")
public class SysRolePermissionController extends BaseRestSpringController {

    @ResponseBody
    @RequestMapping(value = "/saveOrDeleteRolePermission",method = RequestMethod.GET)
    public Object saveOrDeleteRolePermission(Long roleId , Long id, boolean isChecked){
        ServiceManager.sysRolePermissionService.saveOrDeleteRolePermission(roleId, id, isChecked);
        return getSuccess();
    }
}
