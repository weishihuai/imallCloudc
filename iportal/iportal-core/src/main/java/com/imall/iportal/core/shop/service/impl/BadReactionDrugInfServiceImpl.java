package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.dicts.BadReactionDrugInfTypeCodeEnum;
import com.imall.iportal.core.shop.entity.BadReactionDrugInf;
import com.imall.iportal.core.shop.repository.BadReactionDrugInfRepository;
import com.imall.iportal.core.shop.service.BadReactionDrugInfService;
import com.imall.iportal.core.shop.vo.BadReactionRepDrugInfDetailVo;
import com.imall.iportal.core.shop.vo.BlendDrugDetailVo;
import com.imall.iportal.core.shop.vo.SuspectDrugDetailVo;
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
public class BadReactionDrugInfServiceImpl extends AbstractBaseService<BadReactionDrugInf, Long> implements BadReactionDrugInfService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private BadReactionDrugInfRepository getBadReactionDrugInfRepository() {
        return (BadReactionDrugInfRepository) baseRepository;
    }

    @Override
    public List<BadReactionDrugInf> listByBadReactionRepId(Long badReactionRepId, String drugInfTypeCode) {
        return getBadReactionDrugInfRepository().listByBadReactionRepId(badReactionRepId, drugInfTypeCode);
    }

    @Override
    public BadReactionRepDrugInfDetailVo findByBadReactionRepId(Long id) {
        List<BadReactionDrugInf> suspectList = this.listByBadReactionRepId(id, BadReactionDrugInfTypeCodeEnum.SUSPECT_DRUG.toCode());
        List<BadReactionDrugInf> blendList = this.listByBadReactionRepId(id, BadReactionDrugInfTypeCodeEnum.BLEND_DRUG.toCode());
        List<SuspectDrugDetailVo> suspectDrugInfList = new ArrayList<>();
        List<BlendDrugDetailVo> blendDrugInfList = new ArrayList<>();

        for (BadReactionDrugInf badReactionDrugInf : suspectList) {
            SuspectDrugDetailVo suspectDrugDetailVo = new SuspectDrugDetailVo();
            suspectDrugDetailVo.setId(badReactionDrugInf.getId());
            suspectDrugDetailVo.setSuspectDrugInfTypeCode(BadReactionDrugInfTypeCodeEnum.fromCode(badReactionDrugInf.getDrugInfTypeCode()).toCode());
            suspectDrugDetailVo.setSuspectApprovalNumber(badReactionDrugInf.getApprovalNumber());
            suspectDrugDetailVo.setSuspectGoodsNm(badReactionDrugInf.getGoodsNm());
            suspectDrugDetailVo.setSuspectCommonNm(badReactionDrugInf.getCommonNm());
            suspectDrugDetailVo.setSuspectProduceManufacturer(badReactionDrugInf.getProduceManufacturer());
            suspectDrugDetailVo.setSuspectBatch(badReactionDrugInf.getBatch());
            suspectDrugDetailVo.setSuspectUsageAndDosage(badReactionDrugInf.getUsageAndDosage());
            suspectDrugDetailVo.setSuspectDrugUseTime(badReactionDrugInf.getDrugUseTime());
            suspectDrugDetailVo.setSuspectDrugUseReason(badReactionDrugInf.getDrugUseReason());
            suspectDrugInfList.add(suspectDrugDetailVo);
        }
        for (BadReactionDrugInf badReactionDrugInf : blendList) {
            BlendDrugDetailVo blendDrugDetailVo = new BlendDrugDetailVo();
            blendDrugDetailVo.setId(badReactionDrugInf.getId());
            blendDrugDetailVo.setBlendDrugInfTypeCode(BadReactionDrugInfTypeCodeEnum.fromCode(badReactionDrugInf.getDrugInfTypeCode()).toCode());
            blendDrugDetailVo.setBlendApprovalNumber(badReactionDrugInf.getApprovalNumber());
            blendDrugDetailVo.setBlendGoodsNm(badReactionDrugInf.getGoodsNm());
            blendDrugDetailVo.setBlendCommonNm(badReactionDrugInf.getCommonNm());
            blendDrugDetailVo.setBlendProduceManufacturer(badReactionDrugInf.getProduceManufacturer());
            blendDrugDetailVo.setBlendBatch(badReactionDrugInf.getBatch());
            blendDrugDetailVo.setBlendUsageAndDosage(badReactionDrugInf.getUsageAndDosage());
            blendDrugDetailVo.setBlendDrugUseTime(badReactionDrugInf.getDrugUseTime());
            blendDrugDetailVo.setBlendDrugUseReason(badReactionDrugInf.getDrugUseReason());
            blendDrugInfList.add(blendDrugDetailVo);
        }
        BadReactionRepDrugInfDetailVo badReactionRepDrugInfDetailVo = new BadReactionRepDrugInfDetailVo();
        badReactionRepDrugInfDetailVo.setSuspectDrugInfList(suspectDrugInfList);
        badReactionRepDrugInfDetailVo.setBlendDrugInfList(blendDrugInfList);
        return badReactionRepDrugInfDetailVo;
    }
}