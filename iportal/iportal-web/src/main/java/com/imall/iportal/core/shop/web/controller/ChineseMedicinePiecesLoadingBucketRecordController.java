package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.ChineseMedicinePiecesLoadingBucketRecordSearchParam;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

@Controller
@RequestMapping("/chineseMedicinePiecesLoadingBucketRecord")
public class ChineseMedicinePiecesLoadingBucketRecordController extends BaseRestSpringController {

	@RequestMapping(value = "/findOne",method = RequestMethod.GET)
	@ResponseBody
	public Object findOne(Long id){
		return null;
	}

	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(){
		return null;
	}

	@RequestMapping(value = "/queryList", method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, ChineseMedicinePiecesLoadingBucketRecordSearchParam chineseMedicinePiecesLoadingBucketRecordSearchParam, @CurrUser CurrUserVo currUserVo) throws ParseException {
		chineseMedicinePiecesLoadingBucketRecordSearchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.chineseMedicinePiecesLoadingBucketRecordService.queryChineseMedicinePiecesLoadingBucketRecord(pageable,chineseMedicinePiecesLoadingBucketRecordSearchParam);
	}
}

