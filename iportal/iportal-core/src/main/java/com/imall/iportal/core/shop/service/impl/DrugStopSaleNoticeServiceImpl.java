package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.GoodsBatchStateCodeEnum;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.elasticsearch.IndexBuilder;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.DrugStopSaleNotice;
import com.imall.iportal.core.shop.entity.GoodsBatch;
import com.imall.iportal.core.shop.entity.StopSaleGoodsInf;
import com.imall.iportal.core.shop.repository.DrugStopSaleNoticeRepository;
import com.imall.iportal.core.shop.service.DrugStopSaleNoticeService;
import com.imall.iportal.core.shop.vo.*;
import org.elasticsearch.common.recycler.Recycler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
public class DrugStopSaleNoticeServiceImpl extends AbstractBaseService<DrugStopSaleNotice, Long> implements DrugStopSaleNoticeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private DrugStopSaleNoticeRepository getDrugStopSaleNoticeRepository() {
        return (DrugStopSaleNoticeRepository) baseRepository;
    }

    @Override
    public Page<DrugStopSaleNoticePageVo> query(Pageable pageable, DrugStopSaleNoticeSearchParam drugStopSaleNoticeSearchParam, Long shopId) throws ParseException {
        Page<DrugStopSaleNotice> drugStopSaleNotices = getDrugStopSaleNoticeRepository().query(pageable, drugStopSaleNoticeSearchParam, shopId);
        List<DrugStopSaleNoticePageVo> drugStopSaleNoticePageVos = new ArrayList<>();
        for (DrugStopSaleNotice drugStopSaleNotice : drugStopSaleNotices.getContent()) {
            drugStopSaleNoticePageVos.add(buildPageVo(drugStopSaleNotice));
        }
        return new PageImpl<DrugStopSaleNoticePageVo>(drugStopSaleNoticePageVos, pageable, drugStopSaleNotices.getTotalElements());
    }

    private DrugStopSaleNoticePageVo buildPageVo(DrugStopSaleNotice drugStopSaleNotice) {
        DrugStopSaleNoticePageVo drugStopSaleNoticePageVo = CommonUtil.copyProperties(drugStopSaleNotice, new DrugStopSaleNoticePageVo());
        SysUser sysUser = ServiceManager.sysUserService.findOne(drugStopSaleNotice.getApproveManId());
        drugStopSaleNoticePageVo.setApproveManName(sysUser.getRealName());
        return drugStopSaleNoticePageVo;
    }

    @Transactional
    @Override
    public Long saveDrugStopSaleNotice(DrugStopSaleNoticeSaveVo drugStopSaleNoticeSaveVo) {
        DrugStopSaleNotice drugStopSaleNotice = CommonUtil.copyProperties(drugStopSaleNoticeSaveVo, new DrugStopSaleNotice());
        drugStopSaleNotice.setShopId(drugStopSaleNoticeSaveVo.getShopId());
        drugStopSaleNotice.setStopSaleNum(DateSerialGenerator.genSerialCode(DateSerialGenerator.DRUG_SHOP_SALE_NOTICE_PREFIX));
        drugStopSaleNotice.setMakeTime(new Date());
        Long saveId = super.save(drugStopSaleNotice).getId();
        List<StopSaleGoodsInfSaveVo> stopSaleGoodsInfSaveVoList = drugStopSaleNoticeSaveVo.getStopSaleGoodsInfSaveVoList();
        List<Long> ids = new ArrayList<>();
        for (StopSaleGoodsInfSaveVo stopSaleGoodsInfSaveVo : stopSaleGoodsInfSaveVoList) {
            StopSaleGoodsInf stopSaleGoodsInf = CommonUtil.copyProperties(stopSaleGoodsInfSaveVo, new StopSaleGoodsInf());
            stopSaleGoodsInf.setDrugStopSaleNoticeId(saveId);
            ServiceManager.stopSaleGoodsInfService.save(stopSaleGoodsInf);
            //将该批次商品改成停售
            GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(stopSaleGoodsInfSaveVo.getBatchId());
            if(GoodsBatchStateCodeEnum.fromCode(goodsBatch.getBatchState()) == GoodsBatchStateCodeEnum.STOP_SALE){
                throw new BusinessException(ResGlobal.COMMON_ERROR, "该批次"+goodsBatch.getBatch()+"已经被停售!");
            }
            goodsBatch.setBatchState(GoodsBatchStateCodeEnum.STOP_SALE.toCode());
            ServiceManager.goodsBatchService.save(goodsBatch);
        }
        IndexBuilder.commitImmediately(ids, IndexTypeCodeEnum.GOODS_BATCH);
        return saveId;
    }

    @Override
    public DrugStopSaleNoticeDetailVo findDetail(Long id, Long shopId) {
        DrugStopSaleNotice drugStopSaleNotice = findOne(id);
        if(drugStopSaleNotice == null){
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }

        if(!shopId.equals(drugStopSaleNotice.getShopId())){
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }

        DrugStopSaleNoticeDetailVo drugStopSaleNoticeDetailVo = CommonUtil.copyProperties(drugStopSaleNotice,new DrugStopSaleNoticeDetailVo());
        SysUser sysUser = ServiceManager.sysUserService.findOne(drugStopSaleNotice.getApproveManId());
        drugStopSaleNoticeDetailVo.setApproveManName(sysUser.getRealName());
        List<StopSaleGoodsInf> stopSaleGoodsInfs = ServiceManager.stopSaleGoodsInfService.findByDrugStopSaleNoticeId(id);
        List<StopSaleGoodsInfVo> stopSaleGoodsInfVos = new ArrayList<>();
        if(stopSaleGoodsInfs != null){
            for(StopSaleGoodsInf stopSaleGoodsInf:stopSaleGoodsInfs){
                StopSaleGoodsInfVo stopSaleGoodsInfVo = CommonUtil.copyProperties(stopSaleGoodsInf,new StopSaleGoodsInfVo());
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(stopSaleGoodsInf.getDosageForm());
                if(sysDictItem!=null){
                    stopSaleGoodsInfVo.setDosageFormName(sysDictItem.getDictItemNm());
                }
                stopSaleGoodsInfVos.add(stopSaleGoodsInfVo);
            }
        }
        drugStopSaleNoticeDetailVo.setStopSaleGoodsInfVos(stopSaleGoodsInfVos);
        return drugStopSaleNoticeDetailVo;
    }
}