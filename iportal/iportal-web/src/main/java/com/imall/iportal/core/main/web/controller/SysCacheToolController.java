package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.valid.EntityCacheInfoValid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/sysCacheTool")
public class SysCacheToolController extends BaseRestSpringController {

    @RequestMapping(value = "/clear",method = RequestMethod.POST)
    @ResponseBody
    public  Object delete(String  pattern){
        ServiceManager.sysCacheToolService.evictCache(pattern);
        return getSuccess();
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, String cacheEntityName){
        return ServiceManager.sysCacheToolService.query(pageable,cacheEntityName);
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody EntityCacheInfoValid entityCacheInfoValid){
        ServiceManager.sysCacheToolService.updateCache(entityCacheInfoValid);
        return getSuccess();
    }
}
