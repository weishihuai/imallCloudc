package com.imall.iportal.core.index.web.controller;


import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@Controller
@RequestMapping("/indexManage")
public class IndexManageController extends BaseRestSpringController {

	/**
	 * 获取索引管理列表数据
	 * @param pageable 分页
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public Object list(ModelMap model,@PageableDefault(page = 0, size = 10) Pageable pageable) throws InvocationTargetException, IllegalAccessException, SQLException, IOException, ClassNotFoundException {
		return ServiceManager.esIndexManageService.query(pageable);
	}

	/**
	 * 重建索引
	 * @param id 索引管理ID
	 */
	@RequestMapping(value = "/rebuildIndex",method = RequestMethod.GET)
	@ResponseBody
	public Object rebuildIndex(ModelMap model,Long id){
		ServiceManager.esIndexManageService.rebuildIndex(id);
		return true;
	}

	/**
	 * 重建索引
	 * @param indexTypeCode 索引类型
	 */
	@RequestMapping(value = "/rebuildIndexByIndexTypeCode",method = RequestMethod.GET)
	@ResponseBody
		public Object rebuildIndexByIndexTypeCode(ModelMap model,String indexTypeCode){
		ServiceManager.esIndexQueueService.rebuildIndex(IndexTypeCodeEnum.fromCode(indexTypeCode));
		return true;
	}
}

