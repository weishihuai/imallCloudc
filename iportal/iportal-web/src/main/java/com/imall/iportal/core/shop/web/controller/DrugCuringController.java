package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.DrugCuringSaveVo;
import com.imall.iportal.core.shop.vo.DrugCuringSearchParam;
import com.imall.iportal.core.shop.vo.DrugCuringUpdateVo;
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
@RequestMapping("/drugCuring")
public class DrugCuringController extends BaseRestSpringController {

	@RequestMapping(value = "/findById",method = RequestMethod.GET)
	@ResponseBody
	public Object findById(Long id, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.drugCuringService.findById(currUserVo.getShopId(), id);
	}

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Object save(ModelMap model, @RequestBody DrugCuringSaveVo drugCuringSaveVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
		drugCuringSaveVo.setShopId(currUserVo.getShopId());
		ServiceManager.drugCuringService.save(drugCuringSaveVo);
		return getSuccess();
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(ModelMap model, @RequestBody DrugCuringUpdateVo drugCuringUpdateVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
		drugCuringUpdateVo.setShopId(currUserVo.getShopId());
		ServiceManager.drugCuringService.update(drugCuringUpdateVo);
		return getSuccess();
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model,@PageableDefault(page = 0, size = 10) Pageable pageable, DrugCuringSearchParam drugCuringSearchParam, @CurrUser CurrUserVo currUserVo) {
		drugCuringSearchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.drugCuringService.query(pageable, drugCuringSearchParam);
	}

}

