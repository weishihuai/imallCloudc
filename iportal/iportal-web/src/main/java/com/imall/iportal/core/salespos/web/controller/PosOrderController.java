package com.imall.iportal.core.salespos.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.valid.SysAppSaveValid;
import com.imall.iportal.core.main.valid.SysAppUpdateValid;
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
@RequestMapping("/salespos/order")
public class PosOrderController extends BaseRestSpringController {


    //订单下单
//    @CacheEvict(value = "sysAppsCache", key="#id")
    @RequestMapping(value = "/addOrder",method = RequestMethod.POST)
    @ResponseBody
    public Object addOrder(Long id, @RequestBody SysAppSaveValid sysAppSaveValid){ //注意，一定要把Long id 写在前面，作为第一个参数
        //ServiceManager.orderItemService.save(sysAppSaveValid);
        return getSuccess();
    }

}
