package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.entity.StorageSpace;
import com.imall.iportal.core.shop.vo.StorageSpaceSaveVo;
import com.imall.iportal.core.shop.vo.StorageSpaceSearchParam;
import com.imall.iportal.core.shop.vo.StorageSpaceUpdateVo;
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
@RequestMapping("/storageSpace")
public class StorageSpaceController extends BaseRestSpringController {

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody StorageSpaceSaveVo storageSpaceSaveVo, @CurrUser CurrUserVo currUserVo) {
        storageSpaceSaveVo.setShopId(currUserVo.getShopId());
        ServiceManager.storageSpaceService.save(storageSpaceSaveVo);
        return getSuccess();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody StorageSpaceUpdateVo storageSpaceUpdateVo, @CurrUser CurrUserVo currUserVo) {
        storageSpaceUpdateVo.setShopId(currUserVo.getShopId());
        ServiceManager.storageSpaceService.update(storageSpaceUpdateVo);
        return getSuccess();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, StorageSpaceSearchParam storageSpaceSearchParam, @CurrUser CurrUserVo currUserVo) {
        storageSpaceSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.storageSpaceService.query(pageable, storageSpaceSearchParam);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Object findById(@CurrUser CurrUserVo currUserVo, Long id) {
        return ServiceManager.storageSpaceService.findById(currUserVo.getShopId(), id);
    }

    @RequestMapping(value = "/listForGoodsList", method = RequestMethod.GET)
    @ResponseBody
    public Object list(ModelMap model, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.storageSpaceService.listForGoodsList(currUserVo.getShopId());
    }

    @RequestMapping(value = "/checkStorageSpaceNmIsExist", method = RequestMethod.POST)
    @ResponseBody
    public Object checkStorageSpaceNmIsExist(@RequestBody StorageSpace storageSpace, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.storageSpaceService.checkStorageSpaceNmIsExist(storageSpace.getStorageSpaceNm(), storageSpace.getId(), currUserVo.getShopId());
    }

    @RequestMapping(value = "/listAllStorageSpace", method = RequestMethod.GET)
    @ResponseBody
    public Object listAllEnableStorageSpace(@CurrUser CurrUserVo currUserVo) {
        return ServiceManager.storageSpaceService.listAllEnableStorageSpace(currUserVo.getShopId());
    }
    @RequestMapping(value = "/listAllStorageSpaceIsNotVirtualWarehouse", method = RequestMethod.GET)
    @ResponseBody
    public Object findByShopIdAndIsVirtualWarehouse(@CurrUser CurrUserVo currUserVo, String isVirtualWarehouse, String storageSpaceType, String isEnable) {
        return ServiceManager.storageSpaceService.findByShopIdAndOthers(currUserVo.getShopId(),isVirtualWarehouse,storageSpaceType,isEnable);
    }

}

