package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.valid.SysExceptionCodeSaveValid;
import com.imall.iportal.core.main.valid.SysExceptionCodeUpdateValid;
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
@RequestMapping("/sysExceptionCode")
public class SysExceptionCodeController extends BaseRestSpringController {


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(ModelMap model,@PageableDefault(page = 0, size = 10) Pageable pageable,String code,String exceptionMsg){
        return ServiceManager.sysExceptionCodeService.query(pageable,code, exceptionMsg);
    }

    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id){
        return ServiceManager.sysExceptionCodeService.findOne(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody SysExceptionCodeSaveValid sysExceptionCodeSaveValid){
        ServiceManager.sysExceptionCodeService.save(sysExceptionCodeSaveValid);
        return getSuccess();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody SysExceptionCodeUpdateValid sysExceptionCodeUpdateValid){
        ServiceManager.sysExceptionCodeService.update(sysExceptionCodeUpdateValid);
        return getSuccess();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public  Object delete(@RequestBody List<Long> ids){
        ServiceManager.sysExceptionCodeService.delete(ids);
        return getSuccess();
    }

}
