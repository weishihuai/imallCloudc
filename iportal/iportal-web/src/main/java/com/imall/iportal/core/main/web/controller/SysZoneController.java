package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Donie on 2015/10/13.
 */
@Controller
@RequestMapping("/sysZone")
public class SysZoneController extends BaseRestSpringController {

    //@Cacheable("sysZoneCache")
    @RequestMapping(value = "/findSysZoneTree",method = RequestMethod.GET)
    @ResponseBody
    public Object findSysZoneTree(ModelMap model){
        return  ServiceManager.sysZoneService.findSysZoneTree();
    }

    @RequestMapping(value = "/findByParentId",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object findByParentId(ModelMap model, Long id, Integer subMaxLayer){
        Long parentId = id;
        if(parentId==null){
            parentId = 1l;
        }
        return ServiceManager.sysZoneService.findByParentId(parentId, subMaxLayer);
    }

    @RequestMapping(value = "/findSysZoneTreeByZoneId",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object findSysZoneTreeByZoneId(ModelMap model, Long zoneId){
        if(zoneId==null){
            zoneId = 1l;
        }
        return ServiceManager.sysZoneService.findSysZoneTreeByZoneId(zoneId);
    }

}
