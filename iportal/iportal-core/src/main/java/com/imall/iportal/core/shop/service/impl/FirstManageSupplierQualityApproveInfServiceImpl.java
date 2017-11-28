package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.ApproveStateCodeEnum;
import com.imall.commons.dicts.FirstApproveTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.entity.FirstManageSupplierQualityApprove;
import com.imall.iportal.core.shop.entity.FirstManageSupplierQualityApproveInf;
import com.imall.iportal.core.shop.repository.FirstManageSupplierQualityApproveInfRepository;
import com.imall.iportal.core.shop.service.FirstManageSupplierQualityApproveInfService;
import com.imall.iportal.core.shop.vo.FirstManageSupplierQualityApproveInfDetailVo;
import com.imall.iportal.core.shop.vo.FirstManageSupplierQualityApproveInfSaveVo;
import com.imall.iportal.core.shop.vo.FirstManageSupplierQualityApproveInfVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class FirstManageSupplierQualityApproveInfServiceImpl extends AbstractBaseService<FirstManageSupplierQualityApproveInf, Long> implements FirstManageSupplierQualityApproveInfService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private FirstManageSupplierQualityApproveInfRepository getFirstManageSupplierQualityApproveInfRepository() {
        return (FirstManageSupplierQualityApproveInfRepository) baseRepository;
    }

    @Override
    public List<FirstManageSupplierQualityApproveInfDetailVo> findByFirstManageSupplierQualityApproveId(Long firstManageSupplierQualityApproveId) {
        List<FirstManageSupplierQualityApproveInf> firstSupplierDrugQualityApproves = getFirstManageSupplierQualityApproveInfRepository().findByFirstSupplierDrugQualityApproveId(firstManageSupplierQualityApproveId);
        List<FirstManageSupplierQualityApproveInfDetailVo> firstManageSupplierQualityApproveInfDetailVos1 = new ArrayList<>();
        for (FirstManageSupplierQualityApproveInf firstManageSupplierQualityApproveInf: firstSupplierDrugQualityApproves) {
            SysUser sysUser = ServiceManager.sysUserService.findOne(firstManageSupplierQualityApproveInf.getApproveManId());
            FirstManageSupplierQualityApproveInfDetailVo firstManageSupplierQualityApproveInfDetailVo = CommonUtil.copyProperties(firstManageSupplierQualityApproveInf, new FirstManageSupplierQualityApproveInfDetailVo());
            firstManageSupplierQualityApproveInfDetailVo.setApproveMan(sysUser.getRealName());
            firstManageSupplierQualityApproveInfDetailVo.setApproveTimeString(firstManageSupplierQualityApproveInf.getApproveTime());
            firstManageSupplierQualityApproveInfDetailVo.setApproveState(ApproveStateCodeEnum.fromCode(firstManageSupplierQualityApproveInf.getApproveStateCode()).toName());
            firstManageSupplierQualityApproveInfDetailVo.setApproveType(FirstApproveTypeCodeEnum.fromCode(firstManageSupplierQualityApproveInf.getApproveTypeCode()).toName());
            firstManageSupplierQualityApproveInfDetailVos1.add(firstManageSupplierQualityApproveInfDetailVo);
        }
        return firstManageSupplierQualityApproveInfDetailVos1;
    }

    @Override
    public FirstManageSupplierQualityApproveInfVo findById(Long firstManageSupplierQualityApproveId) {
        FirstManageSupplierQualityApproveInfVo firstManageSupplierQualityApproveInfVo = new FirstManageSupplierQualityApproveInfVo();
        FirstManageSupplierQualityApprove firstManageSupplierQualityApprove = ServiceManager.firstManageSupplierQualityApproveService.findOne(firstManageSupplierQualityApproveId);
        firstManageSupplierQualityApproveInfVo.setId(firstManageSupplierQualityApproveId);
        firstManageSupplierQualityApproveInfVo.setSupplierId(firstManageSupplierQualityApprove.getSupplierId());
        firstManageSupplierQualityApproveInfVo.setShopId(firstManageSupplierQualityApprove.getShopId());

        List<FirstManageSupplierQualityApproveInf> firstManageSupplierQualityApproveInfs = getFirstManageSupplierQualityApproveInfRepository().findByFirstSupplierDrugQualityApproveId(firstManageSupplierQualityApproveId);
        for (FirstManageSupplierQualityApproveInf firstManageSupplierQualityApproveInf :firstManageSupplierQualityApproveInfs) {
            switch (FirstApproveTypeCodeEnum.fromCode(firstManageSupplierQualityApproveInf.getApproveTypeCode())) {
                case PURCHASE_APPROVE:
                    SysUser purchaseSysUser = ServiceManager.sysUserService.findOne(firstManageSupplierQualityApproveInf.getApproveManId());
                    firstManageSupplierQualityApproveInfVo.setPurchaseId(firstManageSupplierQualityApproveInf.getId());
                    firstManageSupplierQualityApproveInfVo.setPurchaseApproveMan(purchaseSysUser.getRealName());
                    firstManageSupplierQualityApproveInfVo.setPurchaseApproveTimeString(firstManageSupplierQualityApproveInf.getApproveTime());
                    firstManageSupplierQualityApproveInfVo.setPurchaseApproveState(ApproveStateCodeEnum.fromCode(firstManageSupplierQualityApproveInf.getApproveStateCode()).toCode());
                    firstManageSupplierQualityApproveInfVo.setPurchaseApproveType(FirstApproveTypeCodeEnum.PURCHASE_APPROVE.toCode());
                    firstManageSupplierQualityApproveInfVo.setPurchaseApproveRemark(firstManageSupplierQualityApproveInf.getApproveRemark());
                    break;
                case QUALITY_APPROVE:
                    SysUser qualitySysUser = ServiceManager.sysUserService.findOne(firstManageSupplierQualityApproveInf.getApproveManId());
                    firstManageSupplierQualityApproveInfVo.setQualityId(firstManageSupplierQualityApproveInf.getId());
                    firstManageSupplierQualityApproveInfVo.setQualityApproveMan(qualitySysUser.getRealName());
                    firstManageSupplierQualityApproveInfVo.setQualityApproveTimeString(firstManageSupplierQualityApproveInf.getApproveTime());
                    firstManageSupplierQualityApproveInfVo.setQualityApproveState(ApproveStateCodeEnum.fromCode(firstManageSupplierQualityApproveInf.getApproveStateCode()).toCode());
                    firstManageSupplierQualityApproveInfVo.setQualityApproveType(FirstApproveTypeCodeEnum.QUALITY_APPROVE.toCode());
                    firstManageSupplierQualityApproveInfVo.setQualityApproveRemark(firstManageSupplierQualityApproveInf.getApproveRemark());
                    break;
                case ENT_APPROVE:
                    SysUser entSysUser = ServiceManager.sysUserService.findOne(firstManageSupplierQualityApproveInf.getApproveManId());
                    firstManageSupplierQualityApproveInfVo.setEntId(firstManageSupplierQualityApproveInf.getId());
                    firstManageSupplierQualityApproveInfVo.setEntApproveMan(entSysUser.getRealName());
                    firstManageSupplierQualityApproveInfVo.setEntApproveTimeString(firstManageSupplierQualityApproveInf.getApproveTime());
                    firstManageSupplierQualityApproveInfVo.setEntApproveState(ApproveStateCodeEnum.fromCode(firstManageSupplierQualityApproveInf.getApproveStateCode()).toCode());
                    firstManageSupplierQualityApproveInfVo.setEntApproveType(FirstApproveTypeCodeEnum.ENT_APPROVE.toCode());
                    firstManageSupplierQualityApproveInfVo.setEntApproveRemark(firstManageSupplierQualityApproveInf.getApproveRemark());
                    break;
            }
        }

        return firstManageSupplierQualityApproveInfVo;
    }

    @Override
    @Transactional
    public void saveFirstManageSupplierQualityApproveInf(FirstManageSupplierQualityApproveInfSaveVo firstManageSupplierQualityApproveInfSaveVo) throws ParseException {
        if(firstManageSupplierQualityApproveInfSaveVo.getPurchaseApproveManId()!=null){
            FirstManageSupplierQualityApproveInf firstManageSupplierQualityApproveInf=CommonUtil.copyProperties(firstManageSupplierQualityApproveInfSaveVo,new FirstManageSupplierQualityApproveInf());
            firstManageSupplierQualityApproveInf.setApproveTypeCode(FirstApproveTypeCodeEnum.PURCHASE_APPROVE.toCode());
            firstManageSupplierQualityApproveInf.setApproveManId(firstManageSupplierQualityApproveInfSaveVo.getPurchaseApproveManId());
            firstManageSupplierQualityApproveInf.setApproveStateCode(StringUtils.isNotBlank(firstManageSupplierQualityApproveInfSaveVo.getPurchaseApproveState())?ApproveStateCodeEnum.fromCode(firstManageSupplierQualityApproveInfSaveVo.getPurchaseApproveState()).toCode():null);
            firstManageSupplierQualityApproveInf.setApproveRemark(firstManageSupplierQualityApproveInfSaveVo.getPurchaseApproveRemark());
            firstManageSupplierQualityApproveInf.setApproveTime(firstManageSupplierQualityApproveInfSaveVo.getPurchaseApproveTimeString());
            save(firstManageSupplierQualityApproveInf);
        }
        if(firstManageSupplierQualityApproveInfSaveVo.getQualityApproveManId()!=null){
            FirstManageSupplierQualityApproveInf firstManageSupplierQualityApproveInf=CommonUtil.copyProperties(firstManageSupplierQualityApproveInfSaveVo,new FirstManageSupplierQualityApproveInf());
            firstManageSupplierQualityApproveInf.setApproveTypeCode(FirstApproveTypeCodeEnum.QUALITY_APPROVE.toCode());
            firstManageSupplierQualityApproveInf.setApproveManId(firstManageSupplierQualityApproveInfSaveVo.getQualityApproveManId());
            firstManageSupplierQualityApproveInf.setApproveStateCode(StringUtils.isNotBlank(firstManageSupplierQualityApproveInfSaveVo.getQualityApproveState())?ApproveStateCodeEnum.fromCode(firstManageSupplierQualityApproveInfSaveVo.getQualityApproveState()).toCode():null);
            firstManageSupplierQualityApproveInf.setApproveRemark(firstManageSupplierQualityApproveInfSaveVo.getQualityApproveRemark());
            firstManageSupplierQualityApproveInf.setApproveTime(firstManageSupplierQualityApproveInfSaveVo.getQualityApproveTimeString());
            save(firstManageSupplierQualityApproveInf);
        }
        if(firstManageSupplierQualityApproveInfSaveVo.getEntApproveManId()!=null){
            FirstManageSupplierQualityApproveInf firstManageSupplierQualityApproveInf=CommonUtil.copyProperties(firstManageSupplierQualityApproveInfSaveVo,new FirstManageSupplierQualityApproveInf());
            firstManageSupplierQualityApproveInf.setApproveTypeCode(FirstApproveTypeCodeEnum.ENT_APPROVE.toCode());
            firstManageSupplierQualityApproveInf.setApproveManId(firstManageSupplierQualityApproveInfSaveVo.getEntApproveManId());
            firstManageSupplierQualityApproveInf.setApproveStateCode(StringUtils.isNotBlank(firstManageSupplierQualityApproveInfSaveVo.getEntApproveState())?ApproveStateCodeEnum.fromCode(firstManageSupplierQualityApproveInfSaveVo.getEntApproveState()).toCode():null);
            firstManageSupplierQualityApproveInf.setApproveRemark(firstManageSupplierQualityApproveInfSaveVo.getEntApproveRemark());
            firstManageSupplierQualityApproveInf.setApproveTime(firstManageSupplierQualityApproveInfSaveVo.getEntApproveTimeString());
            save(firstManageSupplierQualityApproveInf);
        }

        this.updateSupplierState(firstManageSupplierQualityApproveInfSaveVo);


    }


    private void updateSupplierState(FirstManageSupplierQualityApproveInfSaveVo firstManageSupplierQualityApproveInf) throws ParseException {
        //企业审核杯具
        if(ApproveStateCodeEnum.REJECTED.toCode().equals(firstManageSupplierQualityApproveInf.getEntApproveState())){
            ServiceManager.supplierService.updateApproveStateCode(firstManageSupplierQualityApproveInf.getSupplierId(), ApproveStateCodeEnum.REJECTED.toCode());
           ServiceManager.firstManageSupplierQualityApproveService.updateApproveStateCode(firstManageSupplierQualityApproveInf.getFirstSupplierDrugQualityApproveId(), ApproveStateCodeEnum.REJECTED.toCode());
            ServiceManager.firstManageSupplierQualityApproveService.updateQualityApproveTime(firstManageSupplierQualityApproveInf.getFirstSupplierDrugQualityApproveId());
            return;

        }
        //质量审核杯具
        if(ApproveStateCodeEnum.REJECTED.toCode().equals(firstManageSupplierQualityApproveInf.getQualityApproveState())){
            ServiceManager.supplierService.updateApproveStateCode(firstManageSupplierQualityApproveInf.getSupplierId(), ApproveStateCodeEnum.REJECTED.toCode());
            ServiceManager.firstManageSupplierQualityApproveService.updateApproveStateCode(firstManageSupplierQualityApproveInf.getFirstSupplierDrugQualityApproveId(), ApproveStateCodeEnum.REJECTED.toCode());
             ServiceManager.firstManageSupplierQualityApproveService.updateQualityApproveTime(firstManageSupplierQualityApproveInf.getFirstSupplierDrugQualityApproveId());
            return;

        }
        //采购审核杯具
        if(ApproveStateCodeEnum.REJECTED.toCode().equals(firstManageSupplierQualityApproveInf.getPurchaseApproveState())){
            ServiceManager.supplierService.updateApproveStateCode(firstManageSupplierQualityApproveInf.getSupplierId(), ApproveStateCodeEnum.REJECTED.toCode());
            ServiceManager.firstManageSupplierQualityApproveService.updateApproveStateCode(firstManageSupplierQualityApproveInf.getFirstSupplierDrugQualityApproveId(), ApproveStateCodeEnum.REJECTED.toCode());
            ServiceManager.firstManageSupplierQualityApproveService.updateQualityApproveTime(firstManageSupplierQualityApproveInf.getFirstSupplierDrugQualityApproveId());
            return;

        }
        List<FirstManageSupplierQualityApproveInf> firstManageSupplierQualityApproveInfs = findBySupplierId(firstManageSupplierQualityApproveInf.getSupplierId());
        //已经累积三次审核，且三次没被拒绝，则通过审核
        if(firstManageSupplierQualityApproveInfs.size()>=3){
                ServiceManager.supplierService.updateApproveStateCode(firstManageSupplierQualityApproveInf.getSupplierId(), ApproveStateCodeEnum.PASS_APPROVE.toCode());
                ServiceManager.firstManageSupplierQualityApproveService.updateApproveStateCode(firstManageSupplierQualityApproveInf.getFirstSupplierDrugQualityApproveId(), ApproveStateCodeEnum.PASS_APPROVE.toCode());
                ServiceManager.firstManageSupplierQualityApproveService.updateQualityApproveTime(firstManageSupplierQualityApproveInf.getFirstSupplierDrugQualityApproveId());
        }
    }

    @Override
    public List<FirstManageSupplierQualityApproveInf> findBySupplierId(Long supplierId) {
        return getFirstManageSupplierQualityApproveInfRepository().findBySupplierId(supplierId);
    }
}
















