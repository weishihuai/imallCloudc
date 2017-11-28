package com.imall.iportal.core.platform.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.DrugCombinationCategorySaveVo;
import com.imall.iportal.core.shop.vo.DrugCombinationCategorySearchParam;
import com.imall.iportal.core.shop.vo.DrugCombinationCategoryUpdateVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value = "PlatformDrugCombinationCategoryController")
@RequestMapping("/platform/drugCombinationCategory")
public class DrugCombinationCategoryController extends BaseRestSpringController {

	@RequestMapping(value = "/findById",method = RequestMethod.GET)
	@ResponseBody
	public Object findById(Long id){
		return ServiceManager.drugCombinationCategoryService.findById(id);
	}

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody DrugCombinationCategorySaveVo drugCombinationCategorySaveVo, @CurrUser CurrUserVo currUserVo){
		drugCombinationCategorySaveVo.setOrgId(currUserVo.getOrgId());
		ServiceManager.drugCombinationCategoryService.save(drugCombinationCategorySaveVo);
		return getSuccess();
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(@RequestBody DrugCombinationCategoryUpdateVo drugCombinationCategoryUpdateVo){
		ServiceManager.drugCombinationCategoryService.update(drugCombinationCategoryUpdateVo);
		return getSuccess();
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model,@PageableDefault(page = 0, size = 10) Pageable pageable, DrugCombinationCategorySearchParam drugCombinationCategorySearchParam, @CurrUser CurrUserVo currUserVo){
		drugCombinationCategorySearchParam.setOrgId(currUserVo.getOrgId());
		return ServiceManager.drugCombinationCategoryService.query(pageable, drugCombinationCategorySearchParam);
	}

	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(@RequestBody Long id, @CurrUser CurrUserVo currUserVo){
		ServiceManager.drugCombinationCategoryService.delete(currUserVo.getOrgId(), id);
		return getSuccess();
	}

	@RequestMapping(value = "/checkByCategoryNm", method = RequestMethod.GET)
	@ResponseBody
	public Object checkByCategoryNm(Long id, String categoryNm, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.drugCombinationCategoryService.checkByCategoryNm(currUserVo.getOrgId(), id, categoryNm);
	}
}

