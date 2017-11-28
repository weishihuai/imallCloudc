package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.FirstApproveTypeCodeEnum;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.commons.dicts.StorageConditionTypeCodeEnum;
import com.imall.commons.dicts.ToxicologyTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.FirstManageDrugQualityApproveRepository;
import com.imall.iportal.core.shop.service.FirstManageDrugQualityApproveService;
import com.imall.iportal.core.shop.vo.FirstManageDrugQualityApproveDetailVo;
import com.imall.iportal.core.shop.vo.FirstManageDrugQualityApprovePageVo;
import com.imall.iportal.core.shop.vo.FirstManageDrugQualityApproveSearchParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
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
public class FirstManageDrugQualityApproveServiceImpl extends AbstractBaseService<FirstManageDrugQualityApprove, Long> implements FirstManageDrugQualityApproveService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private FirstManageDrugQualityApproveRepository getFirstManageDrugQualityApproveRepository() {
        return (FirstManageDrugQualityApproveRepository) baseRepository;
    }

    @Override
    public Page<FirstManageDrugQualityApprovePageVo> query(Pageable pageable, FirstManageDrugQualityApproveSearchParam firstManageDrugQualityApproveSearchParam, Long shopId) throws ParseException {
        Page<FirstManageDrugQualityApprove> firstManageDrugQualityApprovePage = getFirstManageDrugQualityApproveRepository().query(pageable, firstManageDrugQualityApproveSearchParam, shopId);
        List<FirstManageDrugQualityApprovePageVo> firstManageDrugQualityApprovePageVos = new ArrayList<>();
        for (FirstManageDrugQualityApprove firstManageDrugQualityApprove : firstManageDrugQualityApprovePage.getContent()) {
            firstManageDrugQualityApprovePageVos.add(buildFirstManageDrugQualityApprovePage(firstManageDrugQualityApprove));
        }
        return new PageImpl<FirstManageDrugQualityApprovePageVo>(firstManageDrugQualityApprovePageVos, new PageRequest(firstManageDrugQualityApprovePage.getNumber(),firstManageDrugQualityApprovePage.getSize()), firstManageDrugQualityApprovePage.getTotalElements());
    }

    private FirstManageDrugQualityApprovePageVo buildFirstManageDrugQualityApprovePage(FirstManageDrugQualityApprove firstManageDrugQualityApprove) {
        FirstManageDrugQualityApprovePageVo firstManageDrugQualityApprovePageVo = CommonUtil.copyProperties(firstManageDrugQualityApprove, new FirstManageDrugQualityApprovePageVo());
        if (StringUtils.isNotBlank(firstManageDrugQualityApprove.getDosageForm())) {
            SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(firstManageDrugQualityApprove.getDosageForm());
            firstManageDrugQualityApprovePageVo.setDosageFormName(sysDictItem.getDictItemNm());
        }
        return firstManageDrugQualityApprovePageVo;
    }

    @Override
    public FirstManageDrugQualityApproveDetailVo findDetail(Long id, Long shopId) {
        FirstManageDrugQualityApprove firstManageDrugQualityApprove = findOne(id);

        if (firstManageDrugQualityApprove == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        if (!shopId.equals(firstManageDrugQualityApprove.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        FirstManageDrugQualityApproveDetailVo firstManageDrugQualityApproveDetailVo = CommonUtil.copyProperties(firstManageDrugQualityApprove, new FirstManageDrugQualityApproveDetailVo());
        Goods goods = ServiceManager.goodsService.findOne(firstManageDrugQualityApprove.getGoodsId());
        firstManageDrugQualityApproveDetailVo.setToxicologyCode(ToxicologyTypeCodeEnum.fromCode(goods.getToxicologyCode()).toName());
        firstManageDrugQualityApproveDetailVo.setStorageCondition(StorageConditionTypeCodeEnum.fromCode(goods.getStorageCondition()).toName());
        firstManageDrugQualityApproveDetailVo.setGoodsTypeCode(goods.getGoodsTypeCode());
        if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.DRUG) {
            GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
            SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
            firstManageDrugQualityApproveDetailVo.setDosageForm(sysDictItem.getDictItemNm());
            firstManageDrugQualityApproveDetailVo.setApprovalNumber(goodsDrug.getApprovalNumber());
            firstManageDrugQualityApproveDetailVo.setApprovalNumberTermString(goodsDrug.getApprovalNumberTermString());
        }

        if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.CHINESE_MEDICINE_PIECES) {
            GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
            SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
            firstManageDrugQualityApproveDetailVo.setDosageForm(sysDictItem.getDictItemNm());
            firstManageDrugQualityApproveDetailVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
            firstManageDrugQualityApproveDetailVo.setApprovalNumberTermString(goodsChineseMedicinePieces.getApprovalNumberTermString());
            firstManageDrugQualityApproveDetailVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
        }

        Sku sku = ServiceManager.skuService.findByGoodsId(goods.getId());
        firstManageDrugQualityApproveDetailVo.setRetailPrice(sku.getRetailPrice());
        firstManageDrugQualityApproveDetailVo.setMemberPrice(sku.getMemberPrice());

        List<FirstManageDrugQualityApproveInf> firstManageDrugQualityApproveInfs = ServiceManager.firstManageDrugQualityApproveInfService.findByFirstManageDrugQualityApproveId(id);
        if (firstManageDrugQualityApproveInfs != null) {
            for (FirstManageDrugQualityApproveInf firstManageDrugQualityApproveInf : firstManageDrugQualityApproveInfs) {
                if (FirstApproveTypeCodeEnum.fromCode(firstManageDrugQualityApproveInf.getApproveTypeCode()) == FirstApproveTypeCodeEnum.QUALITY_APPROVE) {
                    firstManageDrugQualityApproveDetailVo.setQualityApproveRemark(firstManageDrugQualityApproveInf.getApproveRemark());
                    firstManageDrugQualityApproveDetailVo.setQualityApproveStateCode(firstManageDrugQualityApproveInf.getApproveStateCode());
                    firstManageDrugQualityApproveDetailVo.setQualityApproveTimeStr(firstManageDrugQualityApproveInf.getApproveTimeStr());
                    SysUser sysUser = ServiceManager.sysUserService.findOne(firstManageDrugQualityApproveInf.getApproveManId());
                    firstManageDrugQualityApproveDetailVo.setQualityApproveManName(sysUser.getRealName());
                }

                if (FirstApproveTypeCodeEnum.fromCode(firstManageDrugQualityApproveInf.getApproveTypeCode()) == FirstApproveTypeCodeEnum.ENT_APPROVE) {
                    firstManageDrugQualityApproveDetailVo.setEnterpriseApproveRemark(firstManageDrugQualityApproveInf.getApproveRemark());
                    firstManageDrugQualityApproveDetailVo.setEnterpriseApproveStateCode(firstManageDrugQualityApproveInf.getApproveStateCode());
                    firstManageDrugQualityApproveDetailVo.setEnterpriseApproveTimeStr(firstManageDrugQualityApproveInf.getApproveTimeStr());
                    SysUser sysUser = ServiceManager.sysUserService.findOne(firstManageDrugQualityApproveInf.getApproveManId());
                    firstManageDrugQualityApproveDetailVo.setEnterpriseApproveManName(sysUser.getRealName());
                }

                if (FirstApproveTypeCodeEnum.fromCode(firstManageDrugQualityApproveInf.getApproveTypeCode()) == FirstApproveTypeCodeEnum.PURCHASE_APPROVE) {
                    firstManageDrugQualityApproveDetailVo.setPurchaseApproveRemark(firstManageDrugQualityApproveInf.getApproveRemark());
                    firstManageDrugQualityApproveDetailVo.setPurchaseApproveStateCode(firstManageDrugQualityApproveInf.getApproveStateCode());
                    firstManageDrugQualityApproveDetailVo.setPurchaseApproveTimeStr(firstManageDrugQualityApproveInf.getApproveTimeStr());
                    SysUser sysUser = ServiceManager.sysUserService.findOne(firstManageDrugQualityApproveInf.getApproveManId());
                    firstManageDrugQualityApproveDetailVo.setPurchaseApproveManName(sysUser.getRealName());
                }
            }
        }

        return firstManageDrugQualityApproveDetailVo;
    }

    @Override
    @Transactional
    public FirstManageDrugQualityApprove findByGoodsId(Long goodsId) {
        return getFirstManageDrugQualityApproveRepository().findByGoodsId(goodsId);
    }
}