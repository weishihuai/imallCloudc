package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.PrescriptionDispensingVo;
import com.imall.iportal.core.shop.vo.PrescriptionRegisterSaveVo;
import com.imall.iportal.core.shop.vo.PrescriptionRegisterSearchParam;
import com.imall.iportal.core.shop.vo.PrescriptionRegisterUpdateVo;
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
@RequestMapping("/prescriptionRegister")
public class PrescriptionRegisterController extends BaseRestSpringController {

	@RequestMapping(value = "/findById",method = RequestMethod.GET)
	@ResponseBody
	public Object findById(Long id, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.prescriptionRegisterService.findById(currUserVo.getShopId(), id);
	}

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody PrescriptionRegisterSaveVo prescriptionRegisterSaveVo, @CurrUser CurrUserVo currUserVo){
		prescriptionRegisterSaveVo.setShopId(currUserVo.getShopId());
		ServiceManager.prescriptionRegisterService.save(prescriptionRegisterSaveVo);
		return getSuccess();
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(@RequestBody PrescriptionRegisterUpdateVo prescriptionRegisterUpdateVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
		prescriptionRegisterUpdateVo.setShopId(currUserVo.getShopId());
		ServiceManager.prescriptionRegisterService.update(prescriptionRegisterUpdateVo);
		return getSuccess();
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, PrescriptionRegisterSearchParam prescriptionRegisterSearchParam, @CurrUser CurrUserVo currUserVo){
		prescriptionRegisterSearchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.prescriptionRegisterService.query(pageable, prescriptionRegisterSearchParam);
	}

	@RequestMapping(value = "/dispensingPrescription",method = RequestMethod.POST)
	@ResponseBody
	public Object dispensingPrescription(@RequestBody PrescriptionDispensingVo prescriptionDispensingVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
		prescriptionDispensingVo.setShopId(currUserVo.getShopId());
		ServiceManager.prescriptionRegisterService.dispensingPrescription(prescriptionDispensingVo);
		return getSuccess();
	}

	@RequestMapping(value = "/listItemByOrderId",method = RequestMethod.GET)
	@ResponseBody
	public Object listItemByOrderId(Long orderId, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.prescriptionRegisterService.listItemByOrderId(currUserVo.getShopId(), orderId);
	}
}

