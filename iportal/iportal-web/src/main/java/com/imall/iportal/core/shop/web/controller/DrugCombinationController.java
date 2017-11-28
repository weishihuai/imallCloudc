package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.DrugCombinationSaveVo;
import com.imall.iportal.core.shop.vo.DrugCombinationSearchParam;
import com.imall.iportal.core.shop.vo.DrugCombinationUpdateVo;
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
@RequestMapping("/drugCombination")
public class DrugCombinationController extends BaseRestSpringController {

	@RequestMapping(value = "/findById",method = RequestMethod.GET)
	@ResponseBody
	public Object findById(Long id){
		return ServiceManager.drugCombinationService.findById(id);
	}

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody DrugCombinationSaveVo drugCombinationSaveVo, @CurrUser CurrUserVo currUserVo){
		drugCombinationSaveVo.setOrgId(currUserVo.getOrgId());
		ServiceManager.drugCombinationService.save(drugCombinationSaveVo);
		return getSuccess();
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, DrugCombinationSearchParam drugCombinationSearchParam, @CurrUser CurrUserVo currUserVo){
		drugCombinationSearchParam.setOrgId(currUserVo.getOrgId());
		return ServiceManager.drugCombinationService.query(pageable, drugCombinationSearchParam);
	}

	@RequestMapping(value = "/listAllCategory", method = RequestMethod.GET)
	@ResponseBody
	public Object listAllCategory(ModelMap model, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.drugCombinationCategoryService.listAllByOrgId(currUserVo.getOrgId());
	}

	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public Object delete(@RequestBody Long id, @CurrUser CurrUserVo currUserVo){
		ServiceManager.drugCombinationService.delete(currUserVo.getOrgId(), id);
		return getSuccess();
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(@RequestBody DrugCombinationUpdateVo drugCombinationUpdateVo, @CurrUser CurrUserVo currUserVo){
		drugCombinationUpdateVo.setOrgId(currUserVo.getOrgId());
		ServiceManager.drugCombinationService.update(drugCombinationUpdateVo);
		return getSuccess();
	}
}

