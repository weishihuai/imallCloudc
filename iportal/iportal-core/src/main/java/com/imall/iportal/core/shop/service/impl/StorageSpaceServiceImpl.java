package com.imall.iportal.core.shop.service.impl;

import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.StorageSpaceTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.StorageSpace;
import com.imall.iportal.core.shop.repository.StorageSpaceRepository;
import com.imall.iportal.core.shop.service.StorageSpaceService;
import com.imall.iportal.core.shop.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class StorageSpaceServiceImpl extends AbstractBaseService<StorageSpace, Long> implements StorageSpaceService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String storageSpaceNm = "";

    @Value("${app.shop.storageSpace.storageSpaceNm}")
    public void setStorageSpaceNm(String storageSpaceNm) {
        this.storageSpaceNm = storageSpaceNm;
    }

    @SuppressWarnings("unused")
    private StorageSpaceRepository getStorageSpaceRepository() {
        return (StorageSpaceRepository) baseRepository;
    }

    @Override
    @Transactional
    public void saveForShopCreate(Long shopId) {
        String[] storageSpaceNms = this.storageSpaceNm.split(",|，");
        for (String name : storageSpaceNms) {
            String[] storageSpaceValue = name.split("-");
            StorageSpace storageSpace = new StorageSpace();
            storageSpace.setStorageSpaceNm(storageSpaceValue[0]);
            storageSpace.setIsEnable(BoolCodeEnum.YES.toCode());
            storageSpace.setStorageSpaceType(StorageSpaceTypeCodeEnum.fromCode(storageSpaceValue[1]).toCode());
            storageSpace.setShopId(shopId);
            storageSpace.setIsBuiltIn(BoolCodeEnum.YES.toCode());
            storageSpace.setIsVirtualWarehouse(BoolCodeEnum.fromCode(storageSpaceValue[2]).toCode());
            this.save(storageSpace);
        }
    }

    @Override
    @Transactional
    public Long save(StorageSpaceSaveVo storageSpaceSaveVo) {
        StorageSpace storageSpace = CommonUtil.copyProperties(storageSpaceSaveVo, new StorageSpace());
        storageSpace.setStorageSpaceNm(storageSpaceSaveVo.getStorageSpaceNm());
        storageSpace.setIsEnable(BoolCodeEnum.fromCode(storageSpaceSaveVo.getIsEnable()).toCode());
        storageSpace.setStorageSpaceType(StorageSpaceTypeCodeEnum.fromCode(storageSpaceSaveVo.getStorageSpaceType()).toCode());
        storageSpace.setShopId(storageSpaceSaveVo.getShopId());
        storageSpace.setIsBuiltIn(BoolCodeEnum.NO.toCode());
        storageSpace.setIsVirtualWarehouse(BoolCodeEnum.NO.toCode());
        return save(storageSpace).getId();
    }

    @Override
    @Transactional
    public Long update(StorageSpaceUpdateVo storageSpaceUpdateVo) {
        StorageSpace storageSpace = this.findOne(storageSpaceUpdateVo.getId());
        if (storageSpace == null || !storageSpaceUpdateVo.getShopId().equals(storageSpace.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"货位"}));
        }
        storageSpace.setStorageSpaceNm(storageSpaceUpdateVo.getStorageSpaceNm());
        storageSpace.setIsEnable(BoolCodeEnum.fromCode(storageSpaceUpdateVo.getIsEnable()).toCode());
        storageSpace.setStorageSpaceType(StorageSpaceTypeCodeEnum.fromCode(storageSpaceUpdateVo.getStorageSpaceType()).toCode());
        return update(storageSpace).getId();
    }

    @Override
    public Page<StorageSpaceVo> query(Pageable pageable, StorageSpaceSearchParam storageSpaceSearchParam) {
        Page<StorageSpace> storageSpacePage = getStorageSpaceRepository().query(pageable, storageSpaceSearchParam);
        List<StorageSpaceVo> storageSpaceVoArrayList = new ArrayList<>();
        for (StorageSpace storageSpace : storageSpacePage.getContent()) {
            storageSpaceVoArrayList.add(buildStorageSpaceVo(storageSpace));
        }
        return new PageImpl<>(storageSpaceVoArrayList, new PageRequest(storageSpacePage.getNumber(), storageSpacePage.getSize()), storageSpacePage.getTotalElements());
    }

    private StorageSpaceVo buildStorageSpaceVo(StorageSpace storageSpace) {
        StorageSpaceVo storageSpaceVo = CommonUtil.copyProperties(storageSpace, new StorageSpaceVo());
        storageSpaceVo.setStorageSpaceType(StorageSpaceTypeCodeEnum.fromCode(storageSpace.getStorageSpaceType()).toCode());
        storageSpaceVo.setIsEnable(storageSpace.getIsEnable());
        return storageSpaceVo;
    }

    @Override
    public StorageSpaceVo findById(Long shopId, Long id) {
        StorageSpace storageSpace = this.findOne(id);
        if (storageSpace == null || !storageSpace.getShopId().equals(shopId)) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"货位"}));
        }
        return CommonUtil.copyProperties(storageSpace, new StorageSpaceVo());
    }

    @Override
    public List<StorageSpaceSimpleVo> listForGoodsList(Long shopId) {
        List<StorageSpace> storageSpaces = getStorageSpaceRepository().findByShopIdAndIsEnable(shopId,BoolCodeEnum.YES.toCode());
        List<StorageSpaceSimpleVo> storageSpaceSimpleVos = new ArrayList<>();
        if (storageSpaces != null) {
            for (StorageSpace storageSpace : storageSpaces) {
                StorageSpaceSimpleVo storageSpaceSimpleVo = CommonUtil.copyProperties(storageSpace, new StorageSpaceSimpleVo());
                storageSpaceSimpleVos.add(storageSpaceSimpleVo);
            }
        }
        return storageSpaceSimpleVos;
    }

    @Override
    public Boolean checkStorageSpaceNmIsExist(String storageSpaceNm, Long id, Long shopId) {
        StorageSpace storageSpace = getStorageSpaceRepository().findByStorageSpaceNmAndShopId(storageSpaceNm, shopId);
        return (storageSpace == null && id == null) || storageSpace == null || storageSpace.getId().equals(id);
    }

    @Override
    public List<StorageSpaceVo> listAllEnableStorageSpace(Long shopId) {
        List<StorageSpaceVo> storageSpaceVoList = new ArrayList<>();
        List<StorageSpace> storageSpaceList = getStorageSpaceRepository().findByShopIdAndIsEnable(shopId, BoolCodeEnum.YES.toCode());
        for(StorageSpace storageSpace : storageSpaceList){
            StorageSpaceVo storageSpaceVo = new StorageSpaceVo();
            CommonUtil.copyProperties(storageSpace, storageSpaceVo);
            storageSpaceVoList.add(storageSpaceVo);
        }

        return storageSpaceVoList;
    }

    @Override
    public List<StorageSpace> findByShopIdAndOthers(Long shopId, String isVirtualWarehouse, String storageSpaceType, String isEnable) {
        return getStorageSpaceRepository().findByShopIdAndIsVirtualWarehouseAndStorageSpaceTypeAndIsEnable(shopId, isVirtualWarehouse , StorageSpaceTypeCodeEnum.fromCode(storageSpaceType).toCode(), BoolCodeEnum.fromCode(isEnable).toCode());
    }
}