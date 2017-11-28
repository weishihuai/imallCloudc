package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.FacilityAndDeviceAccountsSaveVo;
import com.imall.iportal.core.shop.vo.FacilityAndDeviceAccountsSearchParam;
import com.imall.iportal.core.shop.vo.FacilityAndDeviceAccountsUpdateVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/facilityAndDeviceAccounts")
public class FacilityAndDeviceAccountsController extends BaseRestSpringController {

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody FacilityAndDeviceAccountsSaveVo saveVo, @CurrUser CurrUserVo currUserVo){
		saveVo.setShopId(currUserVo.getShopId());
		ServiceManager.facilityAndDeviceAccountsService.save(saveVo);
		return getSuccess();
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(@RequestBody FacilityAndDeviceAccountsUpdateVo updateVo, @CurrUser CurrUserVo currUserVo){
		updateVo.setShopId(currUserVo.getShopId());
		ServiceManager.facilityAndDeviceAccountsService.update(updateVo);
		return getSuccess();
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, FacilityAndDeviceAccountsSearchParam searchParam, @CurrUser CurrUserVo currUserVo){
		searchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.facilityAndDeviceAccountsService.query(pageable, searchParam);
	}

	@RequestMapping(value = "/checkDeviceNum", method = RequestMethod.GET)
	@ResponseBody
	public Object checkDeviceNum(Long id, String deviceNum, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.facilityAndDeviceAccountsService.checkDeviceNum(currUserVo.getShopId(), id, deviceNum);
	}
}

