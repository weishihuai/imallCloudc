package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.valid.SysDictItemSaveValid;
import com.imall.iportal.core.main.valid.SysDictItemUpdateValid;
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
@RequestMapping("/sysDictItem")
public class SysDictItemController extends BaseRestSpringController {

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(ModelMap model,@PageableDefault(page = 0, size = 10)Pageable pageable,String dictItemNm,String dictItemCode,Long dataDictId){
       return ServiceManager.sysDictItemService.query(pageable, dictItemNm,dictItemCode,dataDictId);
    }

    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id){
        return  ServiceManager.sysDictItemService.findOne(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody SysDictItemSaveValid sysDictItemSaveValid){
        ServiceManager.sysDictItemService.save(sysDictItemSaveValid);
        return getSuccess();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody SysDictItemUpdateValid sysDictItemUpdateValid){
        ServiceManager.sysDictItemService.update(sysDictItemUpdateValid);
        return getSuccess();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public  Object delete(@RequestBody List<Long> ids){
        ServiceManager.sysDictItemService.delete(ids);
        return getSuccess();
    }

    @RequestMapping(value = "/findByDictType",method = RequestMethod.GET)
    @ResponseBody
    public  Object findByDictType(String dictType){
       return ServiceManager.sysDictItemService.findByDictType(dictType);
    }

    @RequestMapping(value = "/findByAvaiAndDictType",method = RequestMethod.GET)
    @ResponseBody
    public  Object findByAvailableAndDictType(String dictType){
        return ServiceManager.sysDictItemService.findByAvailableAndDictType(dictType);
    }
}
