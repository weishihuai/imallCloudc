package com.imall.iportal.core.weshop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.weshop.vo.FansRemarkUpdateVo;
import com.imall.iportal.core.weshop.vo.FansSearchParam;
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
@RequestMapping("/fans")
public class FansController extends BaseRestSpringController {

    @RequestMapping(value = "/countFansTotal", method = RequestMethod.GET)
    @ResponseBody
    public Object countFansTotal(@CurrUser CurrUserVo currUserVo) {
        return ServiceManager.fansService.countFansTotal(currUserVo.getShopId());
    }

    @RequestMapping(value = "/updateRemark", method = RequestMethod.POST)
    @ResponseBody
    public Object updateFansRemark(@RequestBody FansRemarkUpdateVo fansRemarkUpdateVo, @CurrUser CurrUserVo currUserVo) {
        fansRemarkUpdateVo.setShopId(currUserVo.getShopId());
        ServiceManager.fansService.updateRemark(fansRemarkUpdateVo);
        return getSuccess();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, FansSearchParam fansSearchParam, @CurrUser CurrUserVo currUserVo) {
        fansSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.fansService.query(pageable, fansSearchParam);
    }

}

