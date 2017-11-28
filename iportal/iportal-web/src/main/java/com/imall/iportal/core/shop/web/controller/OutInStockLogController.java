package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.OutInStockLogSearchParam;
import com.imall.iportal.core.shop.vo.OutInStockStatisticsSearchParam;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/outInStockLog")
public class OutInStockLogController extends BaseRestSpringController {

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(@PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo currUserVo, OutInStockLogSearchParam searchParam){
		searchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.outInStockLogService.query(pageable, searchParam);
	}

	@RequestMapping(value = "/queryOutInStockStatistics",method = RequestMethod.GET)
	@ResponseBody
	public Object queryOutInStockStatistics(@PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo currUserVo, OutInStockStatisticsSearchParam searchParam){
		searchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.outInStockLogService.queryOutInStockStatistics(pageable, searchParam);
	}
}

