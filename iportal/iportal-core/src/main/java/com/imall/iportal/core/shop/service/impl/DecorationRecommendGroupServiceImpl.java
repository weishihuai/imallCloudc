package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.DecorationRecommendGroup;
import com.imall.iportal.core.shop.repository.DecorationRecommendGroupRepository;
import com.imall.iportal.core.shop.service.DecorationRecommendGroupService;
import com.imall.iportal.core.shop.vo.DecorationRecommendGroupVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class DecorationRecommendGroupServiceImpl extends AbstractBaseService<DecorationRecommendGroup, Long> implements DecorationRecommendGroupService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private DecorationRecommendGroupRepository getDecorationRecommendGroupRepository() {
        return (DecorationRecommendGroupRepository) baseRepository;
    }

    @Override
    public List<DecorationRecommendGroupVo> findByShopId(Long shopId) {
        List<DecorationRecommendGroup> list = getDecorationRecommendGroupRepository().findByShopId(shopId);
        List<DecorationRecommendGroupVo> voList = new ArrayList<>();
        for (DecorationRecommendGroup group : list) {
            DecorationRecommendGroupVo vo = CommonUtil.copyProperties(group, new DecorationRecommendGroupVo());
            vo.setGoodsCount(ServiceManager.decorationRecommendService.countByDecorationGroupId(group.getId()));
            voList.add(vo);
        }
        return voList;
    }

    @Override
    @Transactional
    public void deleteByIdAndShopId(Long id, Long shopId) {
        getDecorationRecommendGroupRepository().deleteByIdAndShopId(id, shopId);
        ServiceManager.decorationRecommendService.deleteByDecorationGroupId(id);
    }

    @Override
    public DecorationRecommendGroupVo findByIdAndShopId(Long id, Long shopId) {
        DecorationRecommendGroup group = this.findOne(id);
        if (group == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"装修分组"}));
        }
        DecorationRecommendGroupVo vo = CommonUtil.copyProperties(group, new DecorationRecommendGroupVo());
        vo.setRecommendList(ServiceManager.decorationRecommendService.findByDecorationGroupIdAndShopId(id, shopId));
        return vo;
    }

    @Override
    @Transactional
    public Long saveDecorationRecommendGroup(Long shopId, String groupNm) {
        DecorationRecommendGroup decorationRecommendGroup = getDecorationRecommendGroupRepository().findByShopIdAndGroupNm(shopId, groupNm);
        if (decorationRecommendGroup != null) {
            throw new BusinessException(ResGlobal.COMMON_ERROR, "分组" + groupNm + "已存在");
        }
        DecorationRecommendGroup group = new DecorationRecommendGroup();
        group.setShopId(shopId);
        group.setGroupNm(groupNm);
        group.setDisplayPosition(1L);
        return this.save(group).getId();
    }

    @Override
    @Transactional
    public Long updateDecorationRecommendGroup(Long shopId, Long id, String groupNm) {
        DecorationRecommendGroup decorationRecommendGroup = getDecorationRecommendGroupRepository().findByShopIdAndGroupNm(shopId, groupNm);
        if (decorationRecommendGroup == null) {
            decorationRecommendGroup = getDecorationRecommendGroupRepository().findByIdAndShopId(id, shopId);
            decorationRecommendGroup.setGroupNm(groupNm);
            this.save(decorationRecommendGroup);
        } else if (!id.equals(decorationRecommendGroup.getId())) {
            throw new BusinessException(ResGlobal.COMMON_ERROR, "分组" + groupNm + "已存在");
        }
        return id;
    }

    @Override
    public List<DecorationRecommendGroup> getByShopId(Long shopId) {
        return getDecorationRecommendGroupRepository().findByShopId(shopId);
    }
}