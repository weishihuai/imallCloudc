package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.main.web.vo.CategoryParamVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sysFileCategory")
public class SysFileCategoryController extends BaseRestSpringController {

	/**
	 * 获取文件分类树
	 */
	@RequestMapping(value = "/findFileCategoryTree",method = RequestMethod.POST)
	@ResponseBody
	public Object findFileCategoryTree(ModelMap model, Long id, @CurrUser CurrUserVo currUserVo) {
		return ServiceManager.sysFileCategoryService.buildFileCategoryTree(id, true, currUserVo.getOrgId());//使用0用于空判断
	}

	/**
	 * 新增文件分类
	 * @return
     */
	@RequestMapping(value = "/saveCategory",method = RequestMethod.POST)
	@ResponseBody
	public Object saveCategory(@RequestBody CategoryParamVo categoryParamVo, @CurrUser CurrUserVo currUserVo){
		ServiceManager.sysFileCategoryService.saveCategory(categoryParamVo.getId(),categoryParamVo.getCategoryName(), currUserVo.getOrgId());
		return getSuccess();
	}

	/**
	 * 修改文件分类
	 * @return
	 */
	@RequestMapping(value = "/updateCategory",method = RequestMethod.POST)
	@ResponseBody
	public Object updateCategory(@RequestBody CategoryParamVo categoryParamVo, @CurrUser CurrUserVo currUserVo){
		ServiceManager.sysFileCategoryService.updateCategory(categoryParamVo.getId(),categoryParamVo.getCategoryName(), currUserVo.getOrgId());
		return getSuccess();
	}

	/**
	 * 删除文件分类
	 * @param id
	 * @return
     */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public  Object delete(@RequestBody Long id, @CurrUser CurrUserVo currUserVo){
		ServiceManager.sysFileCategoryService.deleteFileCategory(id, currUserVo.getOrgId());
		return getSuccess();
	}

}

