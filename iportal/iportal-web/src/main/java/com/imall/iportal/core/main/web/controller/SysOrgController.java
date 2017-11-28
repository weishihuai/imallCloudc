package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.global.Global;
import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.valid.SysOrgSaveValid;
import com.imall.iportal.core.main.valid.SysOrgUpdateValid;
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
 * Created by Donie on 2015/8/19.
 */
@Controller
@RequestMapping("/sysOrg")
public class SysOrgController extends BaseRestSpringController {

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(ModelMap model, @CurrUser CurrUserVo currUserVo, @PageableDefault(page = 0, size = 10) Pageable pageable, Long parentId, String orgName, String orgCode, String phone){
        parentId = parentId == null ? currUserVo.getOrgId() : parentId;                            //ice不支持Long，参数为null时需转换
        return ServiceManager.sysOrgService.query(pageable, parentId,orgName,orgCode, phone); //调用接口
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(ModelMap model,@RequestBody SysOrgSaveValid sysOrgSaveValid) {
        if(sysOrgSaveValid.getParentId()==null){
            sysOrgSaveValid.setParentId(Global.TREE_DEFAULT_ID);
        }
        ServiceManager.sysOrgService.save(sysOrgSaveValid);
        return getSuccess();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(ModelMap model, @RequestBody SysOrgUpdateValid sysOrgUpdateValid) {
        if(sysOrgUpdateValid.getParentId()==null){
            sysOrgUpdateValid.setParentId(Global.TREE_DEFAULT_ID);
        }
        ServiceManager.sysOrgService.update(sysOrgUpdateValid);
        return getSuccess();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Object delete(ModelMap model,@RequestBody List<Long> ids) {
        ServiceManager.sysOrgService.deleteSysOrg(ids);
        return getSuccess();
    }

    @RequestMapping(value = "/getOrg",method = RequestMethod.GET)
    @ResponseBody
    public Object getOrg(ModelMap model, Long id) {
        return  ServiceManager.sysOrgService.findOne(id);
    }
    /**
     * 获取组织树
     */
    @RequestMapping(value = "/findOrgTree",method = RequestMethod.POST)
    @ResponseBody
    public Object findOrgTree(ModelMap model, @CurrUser CurrUserVo currUserVo, Long id) {
        return ServiceManager.sysOrgService.buildOrgTree(currUserVo.getOrgId(),id==null?currUserVo.getOrgId():id, true); //Global.TREE_DEFAULT_ID
    }
}
