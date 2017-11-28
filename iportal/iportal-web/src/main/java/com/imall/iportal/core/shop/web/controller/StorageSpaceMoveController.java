package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.StorageSpaceMoveSaveVo;
import com.imall.iportal.core.shop.vo.StorageSpaceMoveSearchParam;
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
@RequestMapping("/storageSpaceMove")
public class StorageSpaceMoveController extends BaseRestSpringController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object query(ModelMap model, @CurrUser CurrUserVo currUserVo, @PageableDefault(page = 0, size = 10) Pageable pageable, StorageSpaceMoveSearchParam storageSpaceMoveSearchParam) {
        storageSpaceMoveSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.storageSpaceMoveService.query(pageable, storageSpaceMoveSearchParam);
    }

    @RequestMapping(value = "/findByMoveOrderNum", method = RequestMethod.GET)
    @ResponseBody
    public Object findByMoveOrderNum(@CurrUser CurrUserVo currUserVo, String moveOrderNum) {
        return ServiceManager.storageSpaceMoveService.findByMoveOrderNum(moveOrderNum, currUserVo.getShopId());
    }

    @RequestMapping(value = "/saveStorageSpaceMove", method = RequestMethod.POST)
    @ResponseBody
    public Object saveStorageSpaceMove(@RequestBody StorageSpaceMoveSaveVo storageSpaceMoveSaveVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        storageSpaceMoveSaveVo.setShopId(currUserVo.getShopId());
        ServiceManager.storageSpaceMoveService.saveStorageSpaceMove(storageSpaceMoveSaveVo);
        return getSuccess();
    }

    @RequestMapping(value = "/generateMoveOrderNum", method = RequestMethod.GET)
    @ResponseBody
    public Object generateMoveOrderNum() {
        return ServiceManager.storageSpaceMoveService.generateMoveOrderNum();
    }

}

