package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.global.Global;
import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.valid.SysResourceSaveValid;
import com.imall.iportal.core.main.valid.SysResourceUpdateValid;
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
 * Created by Administrator on 2015/9/21.
 */
@Controller
@RequestMapping("/sysResource")
public class SysResourceController extends BaseRestSpringController {

    /**
     * 获取资源列表
     * @param model
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(ModelMap model,@PageableDefault(page = 0, size = 10) Pageable pageable,Long parentId ,String resourceName,String permissionCode,String routerKey,String isAvailable) {
        parentId = parentId == null ? Global.TREE_DEFAULT_ID : parentId;
        return ServiceManager.sysResourceService.query(pageable, parentId, resourceName, permissionCode, routerKey, isAvailable);
    }

    /**
     * 新增资源
     * @param model
     * @param sysResourceSaveValid
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(ModelMap model,@RequestBody SysResourceSaveValid sysResourceSaveValid) {
        if(sysResourceSaveValid.getParentId()==null){
            sysResourceSaveValid.setParentId(Global.TREE_DEFAULT_ID);
        }
        ServiceManager.sysResourceService.save(sysResourceSaveValid);
        return getSuccess();
    }

    /**
     * 修改资源
     * @param model
     * @param sysResourceUpdateValid
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(ModelMap model,@RequestBody SysResourceUpdateValid sysResourceUpdateValid) {
        if(sysResourceUpdateValid.getParentId()==null){
            sysResourceUpdateValid.setParentId(Global.TREE_DEFAULT_ID);
        }
        ServiceManager.sysResourceService.update(sysResourceUpdateValid);
        return getSuccess();
    }

    /**
     * 删除资源
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Object delete(ModelMap model,@RequestBody List<Long> ids) {
        ServiceManager.sysResourceService.deleteResource(ids);
        return getSuccess();
    }

    /**
     * 获取资源
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(ModelMap model,Long id) {
        if(id!=null){
            return ServiceManager.sysResourceService.findOne(id);
        }
        return getSuccess();
    }


    /**
     * 获取资源树
     */
    @RequestMapping(value = "/findResourceTree",method = RequestMethod.POST)
    @ResponseBody
    public Object findResourceTree(ModelMap model,Long id) {
        return ServiceManager.sysResourceService.buildResourceTree(id==null?Global.TREE_DEFAULT_ID:id,true);
    }

    /**
     * 获取资源树通过资源类型查询
     */
    @RequestMapping(value = "/buildResourceTreeByResourceType",method = RequestMethod.POST)
    @ResponseBody
    public Object buildResourceTreeByResourceType(ModelMap model,Long id,String resourceType) {
        return ServiceManager.sysResourceService.buildResourceTreeByResourceType(id==null?Global.TREE_DEFAULT_ID:id, resourceType, false);
    }

    /**
     *  查找菜单对应资源的子资源
     * @param model
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/buildResourceTreeByMenu",method = RequestMethod.POST)
    @ResponseBody
    public Object buildResourceTreeByMenu(ModelMap model,Long roleId,Long menuId) {
        return ServiceManager.sysResourceService.buildResourceTreeCheckByRole(roleId, menuId);
    }
}
