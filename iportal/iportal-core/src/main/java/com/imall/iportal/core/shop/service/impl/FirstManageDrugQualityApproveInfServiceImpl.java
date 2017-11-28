package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.dicts.ApproveStateCodeEnum;
import com.imall.commons.dicts.FirstApproveTypeCodeEnum;
import com.imall.commons.dicts.GoodsApproveStateCodeEnum;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.elasticsearch.IndexBuilder;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.FirstManageDrugQualityApprove;
import com.imall.iportal.core.shop.entity.FirstManageDrugQualityApproveInf;
import com.imall.iportal.core.shop.entity.Goods;
import com.imall.iportal.core.shop.repository.FirstManageDrugQualityApproveInfRepository;
import com.imall.iportal.core.shop.service.FirstManageDrugQualityApproveInfService;
import com.imall.iportal.core.shop.vo.FirstManageDrugQualityApproveInfSaveVo;
import com.imall.iportal.core.shop.vo.FirstManageDrugQualityApproveInfVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class FirstManageDrugQualityApproveInfServiceImpl extends AbstractBaseService<FirstManageDrugQualityApproveInf, Long> implements FirstManageDrugQualityApproveInfService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private FirstManageDrugQualityApproveInfRepository getFirstManageDrugQualityApproveInfRepository() {
        return (FirstManageDrugQualityApproveInfRepository) baseRepository;
    }

    @Override
    public FirstManageDrugQualityApproveInfVo findApprovalDetail(Long id, Long shopId) {
        FirstManageDrugQualityApprove firstManageDrugQualityApprove = ServiceManager.firstManageDrugQualityApproveService.findOne(id);
        if (firstManageDrugQualityApprove == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        if (!shopId.equals(firstManageDrugQualityApprove.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        FirstManageDrugQualityApproveInfVo firstManageDrugQualityApproveInfVo = new FirstManageDrugQualityApproveInfVo();
        List<FirstManageDrugQualityApproveInf> firstManageDrugQualityApproveInfs = getFirstManageDrugQualityApproveInfRepository().findByFirstManageDrugQualityApproveId(id);
        if (firstManageDrugQualityApproveInfs != null) {
            for (FirstManageDrugQualityApproveInf firstManageDrugQualityApproveInf : firstManageDrugQualityApproveInfs) {
                if (FirstApproveTypeCodeEnum.fromCode(firstManageDrugQualityApproveInf.getApproveTypeCode()) == FirstApproveTypeCodeEnum.QUALITY_APPROVE) {
                    firstManageDrugQualityApproveInfVo.setQualityApproveManId(firstManageDrugQualityApproveInf.getApproveManId());
                    firstManageDrugQualityApproveInfVo.setQualityApproveRemark(firstManageDrugQualityApproveInf.getApproveRemark());
                    firstManageDrugQualityApproveInfVo.setQualityApproveStateCode(firstManageDrugQualityApproveInf.getApproveStateCode());
                    firstManageDrugQualityApproveInfVo.setQualityApproveTimeStr(firstManageDrugQualityApproveInf.getApproveTimeStr());
                    SysUser sysUser = ServiceManager.sysUserService.findOne(firstManageDrugQualityApproveInf.getApproveManId());
                    firstManageDrugQualityApproveInfVo.setQualityApproveManName(sysUser.getRealName());
                }

                if (FirstApproveTypeCodeEnum.fromCode(firstManageDrugQualityApproveInf.getApproveTypeCode()) == FirstApproveTypeCodeEnum.ENT_APPROVE) {
                    firstManageDrugQualityApproveInfVo.setEnterpriseApproveManId(firstManageDrugQualityApproveInf.getApproveManId());
                    firstManageDrugQualityApproveInfVo.setEnterpriseApproveRemark(firstManageDrugQualityApproveInf.getApproveRemark());
                    firstManageDrugQualityApproveInfVo.setEnterpriseApproveStateCode(firstManageDrugQualityApproveInf.getApproveStateCode());
                    firstManageDrugQualityApproveInfVo.setEnterpriseApproveTimeStr(firstManageDrugQualityApproveInf.getApproveTimeStr());
                    SysUser sysUser = ServiceManager.sysUserService.findOne(firstManageDrugQualityApproveInf.getApproveManId());
                    firstManageDrugQualityApproveInfVo.setEnterpriseApproveManName(sysUser.getRealName());
                }

                if (FirstApproveTypeCodeEnum.fromCode(firstManageDrugQualityApproveInf.getApproveTypeCode()) == FirstApproveTypeCodeEnum.PURCHASE_APPROVE) {
                    firstManageDrugQualityApproveInfVo.setPurchaseApproveManId(firstManageDrugQualityApproveInf.getApproveManId());
                    firstManageDrugQualityApproveInfVo.setPurchaseApproveRemark(firstManageDrugQualityApproveInf.getApproveRemark());
                    firstManageDrugQualityApproveInfVo.setPurchaseApproveStateCode(firstManageDrugQualityApproveInf.getApproveStateCode());
                    firstManageDrugQualityApproveInfVo.setPurchaseApproveTimeStr(firstManageDrugQualityApproveInf.getApproveTimeStr());
                    SysUser sysUser = ServiceManager.sysUserService.findOne(firstManageDrugQualityApproveInf.getApproveManId());
                    firstManageDrugQualityApproveInfVo.setPurchaseApproveManName(sysUser.getRealName());
                }
            }
        }

        return firstManageDrugQualityApproveInfVo;
    }

    @Transactional
    @Override
    public List<Long> saveFirstManageDrugQualityInf(FirstManageDrugQualityApproveInfSaveVo firstManageDrugQualityApproveInfSaveVo) throws ParseException {
        FirstManageDrugQualityApprove firstManageDrugQualityApprove = ServiceManager.firstManageDrugQualityApproveService.findOne(firstManageDrugQualityApproveInfSaveVo.getFirstManageDrugQualityApproveId());
        if (firstManageDrugQualityApprove == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        List<Long> ids = new ArrayList<>();
        if (firstManageDrugQualityApproveInfSaveVo.getEnterpriseApproveManId() != null && StringUtils.isNotBlank(firstManageDrugQualityApproveInfSaveVo.getEnterpriseApproveStateCode())) {
            FirstManageDrugQualityApproveInf enterpriseApproveInf = new FirstManageDrugQualityApproveInf();
            enterpriseApproveInf.setApproveManId(firstManageDrugQualityApproveInfSaveVo.getEnterpriseApproveManId());
            enterpriseApproveInf.setApproveStateCode(firstManageDrugQualityApproveInfSaveVo.getEnterpriseApproveStateCode());
            if (StringUtils.isNotBlank(firstManageDrugQualityApproveInfSaveVo.getEnterpriseApproveTimeStr())) {
                enterpriseApproveInf.setApproveTimeStr(firstManageDrugQualityApproveInfSaveVo.getEnterpriseApproveTimeStr());
            }
            enterpriseApproveInf.setApproveRemark(firstManageDrugQualityApproveInfSaveVo.getEnterpriseApproveRemark());
            enterpriseApproveInf.setApproveTypeCode(FirstApproveTypeCodeEnum.ENT_APPROVE.toCode());
            enterpriseApproveInf.setShopId(firstManageDrugQualityApproveInfSaveVo.getShopId());
            enterpriseApproveInf.setGoodsId(firstManageDrugQualityApprove.getGoodsId());
            enterpriseApproveInf.setFirstManageDrugQualityApproveId(firstManageDrugQualityApproveInfSaveVo.getFirstManageDrugQualityApproveId());
            save(enterpriseApproveInf);
            //如果审核状态是驳回  首营商品状态改为已驳回 关联的首营商品状态改为已拒绝
            if (ApproveStateCodeEnum.fromCode(firstManageDrugQualityApproveInfSaveVo.getEnterpriseApproveStateCode()) == ApproveStateCodeEnum.REJECTED) {
                updateGoodsApproveStateCode(firstManageDrugQualityApprove.getGoodsId(), GoodsApproveStateCodeEnum.DENY_APPROVE.toCode());
                firstManageDrugQualityApprove.setQualityApproveTime(new Date());
                firstManageDrugQualityApprove.setApproveStateCode(ApproveStateCodeEnum.REJECTED.toCode());
                ServiceManager.firstManageDrugQualityApproveService.save(firstManageDrugQualityApprove);
            }
        }

        if (firstManageDrugQualityApproveInfSaveVo.getQualityApproveManId() != null && StringUtils.isNotBlank(firstManageDrugQualityApproveInfSaveVo.getQualityApproveStateCode())) {
            FirstManageDrugQualityApproveInf qualityApproveInf = new FirstManageDrugQualityApproveInf();
            qualityApproveInf.setApproveManId(firstManageDrugQualityApproveInfSaveVo.getQualityApproveManId());
            qualityApproveInf.setApproveStateCode(firstManageDrugQualityApproveInfSaveVo.getQualityApproveStateCode());
            if (StringUtils.isNotBlank(firstManageDrugQualityApproveInfSaveVo.getQualityApproveTimeStr())) {
                qualityApproveInf.setApproveTimeStr(firstManageDrugQualityApproveInfSaveVo.getQualityApproveTimeStr());
            }
            qualityApproveInf.setApproveRemark(firstManageDrugQualityApproveInfSaveVo.getQualityApproveRemark());
            qualityApproveInf.setApproveTypeCode(FirstApproveTypeCodeEnum.QUALITY_APPROVE.toCode());
            qualityApproveInf.setShopId(firstManageDrugQualityApproveInfSaveVo.getShopId());
            qualityApproveInf.setGoodsId(firstManageDrugQualityApprove.getGoodsId());
            qualityApproveInf.setFirstManageDrugQualityApproveId(firstManageDrugQualityApproveInfSaveVo.getFirstManageDrugQualityApproveId());
            save(qualityApproveInf);
            ids.add(qualityApproveInf.getId());
            if (ApproveStateCodeEnum.fromCode(firstManageDrugQualityApproveInfSaveVo.getQualityApproveStateCode()) == ApproveStateCodeEnum.REJECTED) {
                updateGoodsApproveStateCode(firstManageDrugQualityApprove.getGoodsId(), GoodsApproveStateCodeEnum.DENY_APPROVE.toCode());
                firstManageDrugQualityApprove.setQualityApproveTime(new Date());
                firstManageDrugQualityApprove.setApproveStateCode(ApproveStateCodeEnum.REJECTED.toCode());
                ServiceManager.firstManageDrugQualityApproveService.save(firstManageDrugQualityApprove);
            }
        }

        if (firstManageDrugQualityApproveInfSaveVo.getPurchaseApproveManId() != null && StringUtils.isNotBlank(firstManageDrugQualityApproveInfSaveVo.getPurchaseApproveStateCode())) {
            FirstManageDrugQualityApproveInf purchaseApproveInf = new FirstManageDrugQualityApproveInf();
            purchaseApproveInf.setApproveManId(firstManageDrugQualityApproveInfSaveVo.getPurchaseApproveManId());
            purchaseApproveInf.setApproveStateCode(firstManageDrugQualityApproveInfSaveVo.getPurchaseApproveStateCode());
            if (StringUtils.isNotBlank(firstManageDrugQualityApproveInfSaveVo.getPurchaseApproveTimeStr())) {
                purchaseApproveInf.setApproveTimeStr(firstManageDrugQualityApproveInfSaveVo.getPurchaseApproveTimeStr());
            }
            purchaseApproveInf.setApproveRemark(firstManageDrugQualityApproveInfSaveVo.getPurchaseApproveRemark());
            purchaseApproveInf.setApproveTypeCode(FirstApproveTypeCodeEnum.PURCHASE_APPROVE.toCode());
            purchaseApproveInf.setShopId(firstManageDrugQualityApproveInfSaveVo.getShopId());
            purchaseApproveInf.setGoodsId(firstManageDrugQualityApprove.getGoodsId());
            purchaseApproveInf.setFirstManageDrugQualityApproveId(firstManageDrugQualityApproveInfSaveVo.getFirstManageDrugQualityApproveId());
            save(purchaseApproveInf);
            ids.add(purchaseApproveInf.getId());
            if (ApproveStateCodeEnum.fromCode(firstManageDrugQualityApproveInfSaveVo.getPurchaseApproveStateCode()) == ApproveStateCodeEnum.REJECTED) {
                updateGoodsApproveStateCode(firstManageDrugQualityApprove.getGoodsId(), GoodsApproveStateCodeEnum.DENY_APPROVE.toCode());
                firstManageDrugQualityApprove.setQualityApproveTime(new Date());
                firstManageDrugQualityApprove.setApproveStateCode(ApproveStateCodeEnum.REJECTED.toCode());
                ServiceManager.firstManageDrugQualityApproveService.save(firstManageDrugQualityApprove);
            }
        }


        List<FirstManageDrugQualityApproveInf> firstManageDrugQualityApproveInfs = getFirstManageDrugQualityApproveInfRepository().findByFirstManageDrugQualityApproveId(firstManageDrugQualityApproveInfSaveVo.getFirstManageDrugQualityApproveId());

        //审核次数没达到三  或者有一个驳回都不能改成审核通过
        if (firstManageDrugQualityApproveInfs.size() != 3) {
            return ids;
        }
        for (FirstManageDrugQualityApproveInf firstManageDrugQualityApproveInf : firstManageDrugQualityApproveInfs) {
            if (ApproveStateCodeEnum.fromCode(firstManageDrugQualityApproveInf.getApproveStateCode()) != ApproveStateCodeEnum.PASS_APPROVE) {
                return ids;
            }
        }

        firstManageDrugQualityApprove.setQualityApproveTime(new Date());
        firstManageDrugQualityApprove.setApproveStateCode(ApproveStateCodeEnum.PASS_APPROVE.toCode());
        ServiceManager.firstManageDrugQualityApproveService.save(firstManageDrugQualityApprove);
        updateGoodsApproveStateCode(firstManageDrugQualityApprove.getGoodsId(), GoodsApproveStateCodeEnum.PASS_APPROVE.toCode());
        return ids;
    }

    private void updateGoodsApproveStateCode(Long goodsId, String code) {
        Goods goods = ServiceManager.goodsService.findOne(goodsId);
        goods.setApproveStateCode(code);
        ServiceManager.goodsService.save(goods);
        IndexBuilder.commitImmediately(goodsId, IndexTypeCodeEnum.GOODS);
    }

    @Override
    public List<FirstManageDrugQualityApproveInf> findByFirstManageDrugQualityApproveId(Long id) {
        return getFirstManageDrugQualityApproveInfRepository().findByFirstManageDrugQualityApproveId(id);
    }
}