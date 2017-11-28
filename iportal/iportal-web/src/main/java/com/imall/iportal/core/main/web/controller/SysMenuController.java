package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.global.Global;
import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.valid.SysMenuSaveValid;
import com.imall.iportal.core.main.valid.SysMenuUpdateValid;
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
 * Created by Donie on 2015/9/24.
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController extends BaseRestSpringController {

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(@PageableDefault(page = 0, size = 10) Pageable pageable,Long parentId,String menuName){
        parentId = parentId == null ? Global.TREE_DEFAULT_ID : parentId;
        return ServiceManager.sysMenuService.query(pageable, parentId,menuName);

    }

    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id){
        return ServiceManager.sysMenuService.findById(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody SysMenuSaveValid sysMenuSaveValid){
        if(sysMenuSaveValid.getParentId()==null){
            sysMenuSaveValid.setParentId(Global.TREE_DEFAULT_ID);
        }
        ServiceManager.sysMenuService.save(sysMenuSaveValid);
        return getSuccess();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody SysMenuUpdateValid sysMenuUpdateValid){
        if(sysMenuUpdateValid.getParentId()==null){
            sysMenuUpdateValid.setParentId(Global.TREE_DEFAULT_ID);
        }
        ServiceManager.sysMenuService.update(sysMenuUpdateValid);
        return getSuccess();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public  Object delete(@RequestBody List<Long> ids){
        ServiceManager.sysMenuService.deleteMenu(ids);
        return getSuccess();
    }

    /**
     * 获取菜单树
     */
    @RequestMapping(value = "/findMenuTree",method = RequestMethod.POST)
    @ResponseBody
    public Object findMenuTree(ModelMap model,Long id) {
        return ServiceManager.sysMenuService.buildMenuTree(id==null?Global.TREE_DEFAULT_ID:id,true);
    }

    @RequestMapping(value = "/buildMenuTreeByRole",method = RequestMethod.POST)
    @ResponseBody
    public Object buildMenuTreeByRole(ModelMap model, @CurrUser CurrUserVo currUserVo, Long id, Long roleId) {
        return ServiceManager.sysMenuService.buildMenuTreeCheckByRole(roleId,id==null?Global.TREE_DEFAULT_ID:id,true,currUserVo.getJobId());
    }

    @RequestMapping(value = "/buildMenuTreeFilterByRole",method = RequestMethod.POST)
    @ResponseBody
    public Object buildMenuTreeFilterByRole(ModelMap model, @CurrUser CurrUserVo currUserVo, Long id,Long roleId) {
        return ServiceManager.sysMenuService.buildMenuTreeFilterByRole(roleId,id==null?Global.TREE_DEFAULT_ID:id, currUserVo.getJobId());
    }
}
