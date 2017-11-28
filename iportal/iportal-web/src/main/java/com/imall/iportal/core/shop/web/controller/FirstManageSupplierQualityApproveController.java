package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.FirstManageSupplierQualityApproveSaveVo;
import com.imall.iportal.core.shop.vo.FirstManageSupplierQualityApproveSearchParam;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

@Controller
@RequestMapping("/firstManageSupplierQualityApprove")
public class FirstManageSupplierQualityApproveController extends BaseRestSpringController {

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Object findById(Long id,@CurrUser CurrUserVo currUserVo) {

        return ServiceManager.firstManageSupplierQualityApproveService.findById(id,currUserVo.getShopId());
    }


    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Object query(ModelMap model,@CurrUser CurrUserVo currUserVo, @PageableDefault(page = 0, size = 10) Pageable pageable, FirstManageSupplierQualityApproveSearchParam firstManageSupplierQualityApproveSearchParam) throws ParseException {
        firstManageSupplierQualityApproveSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.firstManageSupplierQualityApproveService.queryFirstManageSupplierQualityApprove(pageable, firstManageSupplierQualityApproveSearchParam);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody FirstManageSupplierQualityApproveSaveVo firstManageSupplierQualityApproveSaveVo, @CurrUser CurrUserVo currUserVo) {
        firstManageSupplierQualityApproveSaveVo.setShopId(currUserVo.getShopId());
        return ServiceManager.firstManageSupplierQualityApproveService.saveFirstManageSupplierQualityApprove( firstManageSupplierQualityApproveSaveVo);
    }
}

