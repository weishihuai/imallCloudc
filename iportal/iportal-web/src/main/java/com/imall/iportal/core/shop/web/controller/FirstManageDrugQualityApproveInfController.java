package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.FirstManageDrugQualityApproveInfSaveVo;
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
@RequestMapping("/firstManageDrugQualityApproveInf")
public class FirstManageDrugQualityApproveInfController extends BaseRestSpringController {

	@RequestMapping(value = "/findByFirstManageDrugQualityApproveId",method = RequestMethod.GET)
	@ResponseBody
	public Object findOne(Long id, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.firstManageDrugQualityApproveInfService.findApprovalDetail(id,currUserVo.getShopId());
	}

	@RequestMapping(value = "/approveFirstManageDrugQualityInf",method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(@RequestBody FirstManageDrugQualityApproveInfSaveVo firstManageDrugQualityApproveInfSaveVo,@CurrUser CurrUserVo currUserVo) throws ParseException {
        firstManageDrugQualityApproveInfSaveVo.setShopId(currUserVo.getShopId());
	    return ServiceManager.firstManageDrugQualityApproveInfService.saveFirstManageDrugQualityInf(firstManageDrugQualityApproveInfSaveVo);
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model,@PageableDefault(page = 0, size = 10) Pageable pageable){
		return null;
	}

}

