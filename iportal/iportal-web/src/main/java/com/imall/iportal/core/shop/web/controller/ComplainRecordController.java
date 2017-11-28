package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.entity.ComplainRecord;
import com.imall.iportal.core.shop.vo.ComplainRecordSearchParam;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/complainRecord")
public class ComplainRecordController extends BaseRestSpringController {

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Object save(@CurrUser CurrUserVo currUserVo, @RequestBody ComplainRecord complainRecord){
		complainRecord.setShopId(currUserVo.getShopId());
		return ServiceManager.complainRecordService.saveComplainRecord(complainRecord);
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(@PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo currUserVo, ComplainRecordSearchParam searchParam){
		searchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.complainRecordService.query(pageable, searchParam);
	}

}

