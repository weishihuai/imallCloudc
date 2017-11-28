package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.valid.SysAppSaveValid;
import com.imall.iportal.core.main.valid.SysAppUpdateValid;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/sysApp")
public class SysAppController extends BaseRestSpringController {

    private String appKey = "";
    private String appSecret = "";

    //@Value("${sso.client.appKey:}")
    @Value("c1ebe466-1cdc-4bd3-ab69-77c3561b9dee")
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    //@Value("${sso.client.appSecret:}")
    @Value("d8346ea2-6017-43ed-ad68-19c0f971738b")
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(ModelMap model, @CurrUser CurrUserVo currUserVo, @PageableDefault(page = 0, size = 10) Pageable pageable, String appName, String appCname, String hostname, String isAvailable){
        return ServiceManager.sysAppService.query(pageable, appName,appCname,hostname,isAvailable);
    }


//    @Cacheable(value = "sysAppsCache", key="#id")
    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id){
        return ServiceManager.sysAppService.findOne(id);
    }

//    @CacheEvict(value = "sysAppsCache", key="#id")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdate(Long id, @RequestBody SysAppSaveValid sysAppSaveValid){ //注意，一定要把Long id 写在前面，作为第一个参数
        ServiceManager.sysAppService.save(sysAppSaveValid);
        return getSuccess();
    }

//    @CacheEvict(value = "sysAppsCache", key="#id")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(Long id, @RequestBody SysAppUpdateValid sysAppUpdateValid){ //注意，一定要把Long id 写在前面，作为第一个参数
        ServiceManager.sysAppService.update(sysAppUpdateValid);
        return getSuccess();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public  Object delete(@RequestBody List<Long> ids){
        ServiceManager.sysAppService.deleteSysApp(ids);
        return getSuccess();
    }


    @RequestMapping(value = "/findAbleApp",method = RequestMethod.GET)
    @ResponseBody
    public Object findAbleApp(ModelMap model){
        return ServiceManager.sysAppService.findByIsAvailable(BoolCodeEnum.YES.toCode());
    }
}
