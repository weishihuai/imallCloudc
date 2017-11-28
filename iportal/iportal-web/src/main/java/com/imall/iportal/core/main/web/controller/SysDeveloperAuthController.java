package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.valid.SysDeveloperAuthSaveValid;
import com.imall.iportal.core.main.valid.SysDeveloperAuthUpdateValid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sysDeveloperAuth")
public class SysDeveloperAuthController extends BaseRestSpringController {


    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id){
        return ServiceManager.sysDeveloperAuthService.findOne(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(Long id, @RequestBody SysDeveloperAuthSaveValid sysDeveloperAuthSaveValid){
        ServiceManager.sysDeveloperAuthService.save(sysDeveloperAuthSaveValid);
        return getSuccess();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(Long id, @RequestBody SysDeveloperAuthUpdateValid sysDeveloperAuthUpdateValid){
        ServiceManager.sysDeveloperAuthService.update(sysDeveloperAuthUpdateValid);
        return getSuccess();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public  Object delete(@RequestBody List<Long> ids){
        ServiceManager.sysDeveloperAuthService.delete(ids);
        return getSuccess();
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(ModelMap model,@PageableDefault(page = 0, size = 10) Pageable pageable, String userName, String isAvailable){
        return ServiceManager.sysDeveloperAuthService.query(pageable, userName, isAvailable);
    }

}

