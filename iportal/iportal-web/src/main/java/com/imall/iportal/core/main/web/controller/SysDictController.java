package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.valid.SysDictSaveValid;
import com.imall.iportal.core.main.valid.SysDictUpdateValid;
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
 * Created by Donie on 2015/10/19.
 */
@Controller
@RequestMapping("/sysDict")
public class SysDictController extends BaseRestSpringController {

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(ModelMap model,@PageableDefault(page = 0, size = 10)Pageable pageable,String dictNm,String dictType,String isAvailable){
        return  ServiceManager.sysDictService.query(pageable, dictNm,dictType,isAvailable);
    }

    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id){
        return  ServiceManager.sysDictService.findOne(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody SysDictSaveValid sysDictSaveValid){
        sysDictSaveValid.setIsInternal(BoolCodeEnum.NO.toCode());
        ServiceManager.sysDictService.save(sysDictSaveValid);
        return getSuccess();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody SysDictUpdateValid sysDictUpdateValid){
        sysDictUpdateValid.setIsInternal(BoolCodeEnum.NO.toCode());
        ServiceManager.sysDictService.update(sysDictUpdateValid);
        return getSuccess();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public  Object delete(@RequestBody List<Long> ids){
        ServiceManager.sysDictService.deleteSysDict(ids);
        return getSuccess();
    }

    @RequestMapping(value = "/checkDictNm",method = RequestMethod.GET)
    @ResponseBody
    public Object checkDictNm(String dictNm){
        /***唯一验证例子 TODO***/
        return false;
    }

}
