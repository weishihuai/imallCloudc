package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2015/10/9.
 */
@Controller
@RequestMapping("/sysRoleMenu")
public class SysRoleMenuController extends BaseRestSpringController {

    @ResponseBody
    @RequestMapping(value = "/saveOrDeleteRoleMenu",method = RequestMethod.GET)
    public Object saveOrDeleteRoleMenu(Long roleId , Long id, boolean isChecked){
        ServiceManager.sysRoleMenuService.saveAllotRoleMenu(roleId,id,isChecked);
        return getSuccess();
    }
}
