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
import com.imall.iportal.core.shop.entity.DrugReleaseNotice;
import com.imall.iportal.core.shop.entity.GoodsBatch;
import com.imall.iportal.core.shop.entity.ReleaseGoodsInf;
import com.imall.iportal.core.shop.repository.DrugReleaseNoticeRepository;
import com.imall.iportal.core.shop.service.DrugReleaseNoticeService;
import com.imall.iportal.core.shop.vo.*;
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
public class DrugReleaseNoticeServiceImpl extends AbstractBaseService<DrugReleaseNotice, Long> implements DrugReleaseNoticeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private DrugReleaseNoticeRepository getDrugReleaseNoticeRepository() {
        return (DrugReleaseNoticeRepository) baseRepository;
    }

    @Override
    public ReleaseNoticeDetailVo findDetail(Long id, Long shopId) {
        DrugReleaseNotice drugReleaseNotice = findOne(id);
        if (drugReleaseNotice == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }

        if (!shopId.equals(drugReleaseNotice.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }

        ReleaseNoticeDetailVo releaseNoticeDetailVo = CommonUtil.copyProperties(drugReleaseNotice, new ReleaseNoticeDetailVo());
        SysUser sysUser = ServiceManager.sysUserService.findOne(drugReleaseNotice.getApproveManId());
        releaseNoticeDetailVo.setApproveManName(sysUser.getRealName());
        List<ReleaseGoodsInf> releaseGoodsInfs = ServiceManager.releaseGoodsInfService.findByDrugReleaseNoticeId(id);
        List<ReleaseGoodsInfVo> releaseGoodsInfVos = new ArrayList<>();
        if (releaseGoodsInfs != null) {
            for (ReleaseGoodsInf releaseGoodsInf : releaseGoodsInfs) {
                ReleaseGoodsInfVo releaseGoodsInfVo = CommonUtil.copyProperties(releaseGoodsInf, new ReleaseGoodsInfVo());
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(releaseGoodsInf.getDosageForm());
                if(sysDictItem!=null){
                    releaseGoodsInfVo.setDosageFormName(sysDictItem.getDictItemNm());
                }
                releaseGoodsInfVos.add(releaseGoodsInfVo);
            }
        }
        releaseNoticeDetailVo.setReleaseGoodsInfVos(releaseGoodsInfVos);
        return releaseNoticeDetailVo;
    }

    @Transactional
    @Override
    public Long saveReleaseNotice(ReleaseSaleNoticeSaveVo releaseSaleNoticeSaveVo) {
        DrugReleaseNotice drugReleaseNotice = CommonUtil.copyProperties(releaseSaleNoticeSaveVo, new DrugReleaseNotice());
        drugReleaseNotice.setShopId(releaseSaleNoticeSaveVo.getShopId());
        drugReleaseNotice.setReleaseNum(DateSerialGenerator.genSerialCode(DateSerialGenerator.DRUG_RELEASE_NOTICE_PREFIX));
        drugReleaseNotice.setDocMakeTime(new Date());
        Long saveId = save(drugReleaseNotice).getId();
        List<ReleaseGoodsInfSaveVo> releaseNoticeSave = releaseSaleNoticeSaveVo.getReleaseGoodsInfSaveVos();
        List<Long> ids = new ArrayList<>();
        for (ReleaseGoodsInfSaveVo releaseGoodsInfSaveVo : releaseNoticeSave) {
            ReleaseGoodsInf releaseGoodsInf = CommonUtil.copyProperties(releaseGoodsInfSaveVo, new ReleaseGoodsInf());
            releaseGoodsInf.setDrugReleaseNoticeId(saveId);
            ServiceManager.releaseGoodsInfService.save(releaseGoodsInf);
            //将该批次商品解停
            GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(releaseGoodsInfSaveVo.getBatchId());
            if(GoodsBatchStateCodeEnum.fromCode(goodsBatch.getBatchState()) == GoodsBatchStateCodeEnum.NORMAL){
                throw new BusinessException(ResGlobal.COMMON_ERROR, "该批次"+goodsBatch.getBatch()+"已经被解停!");
            }
            goodsBatch.setBatchState(GoodsBatchStateCodeEnum.NORMAL.toCode());
            ServiceManager.goodsBatchService.save(goodsBatch);
            ids.add(goodsBatch.getId());
        }
        IndexBuilder.commitImmediately(ids, IndexTypeCodeEnum.GOODS_BATCH);
        return saveId;
    }

    @Override
    public Page<ReleaseNoticePageVo> query(Pageable pageable, ReleaseNoticeSearchParam releaseNoticeSearchParam, Long shopId) throws ParseException {
        Page<DrugReleaseNotice> drugReleaseNoticePage = getDrugReleaseNoticeRepository().query(pageable, releaseNoticeSearchParam, shopId);
        List<ReleaseNoticePageVo> releaseNoticePageVos = new ArrayList<>();
        for (DrugReleaseNotice drugReleaseNotice : drugReleaseNoticePage.getContent()) {
            releaseNoticePageVos.add(buildPageVo(drugReleaseNotice));
        }
        return new PageImpl<ReleaseNoticePageVo>(releaseNoticePageVos, pageable, drugReleaseNoticePage.getTotalElements());
    }

    private ReleaseNoticePageVo buildPageVo(DrugReleaseNotice drugReleaseNotice) {
        ReleaseNoticePageVo releaseNoticePageVo = CommonUtil.copyProperties(drugReleaseNotice, new ReleaseNoticePageVo());
        SysUser sysUser = ServiceManager.sysUserService.findOne(drugReleaseNotice.getApproveManId());
        releaseNoticePageVo.setApproveManName(sysUser.getRealName());
        return releaseNoticePageVo;
    }
}