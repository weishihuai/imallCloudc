package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.MeasureDeviceUseStateCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.MeasureRecord;
import com.imall.iportal.core.shop.entity.MeasuringDeviceAccounts;
import com.imall.iportal.core.shop.repository.MeasuringDeviceAccountsRepository;
import com.imall.iportal.core.shop.service.MeasuringDeviceAccountsService;
import com.imall.iportal.core.shop.vo.*;
import org.apache.commons.lang3.StringUtils;
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
public class MeasuringDeviceAccountsServiceImpl extends AbstractBaseService<MeasuringDeviceAccounts, Long> implements MeasuringDeviceAccountsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private MeasuringDeviceAccountsRepository getMeasuringDeviceAccountsRepository() {
        return (MeasuringDeviceAccountsRepository) baseRepository;
    }

    @Override
    public Page<MeasuringDeviceAccountsPageVo> query(Pageable pageable, MeasuringDeviceAccountsSearchParam measuringDeviceAccountsSearchParam) throws ParseException {
        Page<MeasuringDeviceAccounts> measuringDeviceAccountsPage = getMeasuringDeviceAccountsRepository().query(pageable, measuringDeviceAccountsSearchParam);
        List<MeasuringDeviceAccountsPageVo> measuringDeviceAccountsPageVoList = new ArrayList<>();
        for (MeasuringDeviceAccounts measuringDeviceAccounts : measuringDeviceAccountsPage.getContent()) {
            measuringDeviceAccountsPageVoList.add(buildMeasuringDeviceAccountsPageVo(measuringDeviceAccounts));
        }
        return new PageImpl<>(measuringDeviceAccountsPageVoList,  new PageRequest(measuringDeviceAccountsPage.getNumber(),measuringDeviceAccountsPage.getSize()), measuringDeviceAccountsPage.getTotalElements());
    }

    private MeasuringDeviceAccountsPageVo buildMeasuringDeviceAccountsPageVo(MeasuringDeviceAccounts measuringDeviceAccounts) throws ParseException {
        //由于copyProperties Date类型的字段(允许为空)为空的时候会报空指针，只能手动set值
        MeasuringDeviceAccountsPageVo measuringDeviceAccountsPageVo = new MeasuringDeviceAccountsPageVo();
        measuringDeviceAccountsPageVo.setId(measuringDeviceAccounts.getId());
        measuringDeviceAccountsPageVo.setShopId(measuringDeviceAccounts.getShopId());
        measuringDeviceAccountsPageVo.setMeasuringDeviceNum(measuringDeviceAccounts.getMeasuringDeviceNum());
        measuringDeviceAccountsPageVo.setManufacturingNum(measuringDeviceAccounts.getManufacturingNum());
        measuringDeviceAccountsPageVo.setDeviceNm(measuringDeviceAccounts.getDeviceNm());
        measuringDeviceAccountsPageVo.setModel(measuringDeviceAccounts.getModel());
        measuringDeviceAccountsPageVo.setProduceManufacturer(measuringDeviceAccounts.getProduceManufacturer());
        measuringDeviceAccountsPageVo.setCategoryNum(measuringDeviceAccounts.getCategoryNum());
        measuringDeviceAccountsPageVo.setMeasureRange(measuringDeviceAccounts.getMeasureRange());
        measuringDeviceAccountsPageVo.setPrecisionLevel(measuringDeviceAccounts.getPrecisionLevel());
        measuringDeviceAccountsPageVo.setResponseMan(measuringDeviceAccounts.getResponseMan());
        measuringDeviceAccountsPageVo.setMeasurePeriod(measuringDeviceAccounts.getMeasurePeriod());
        measuringDeviceAccountsPageVo.setPurchasePrice(measuringDeviceAccounts.getPurchasePrice() == null ? 0D : measuringDeviceAccounts.getPurchasePrice());
        if (StringUtils.isNotBlank(measuringDeviceAccounts.getPurchaseDateString())) {
            measuringDeviceAccountsPageVo.setPurchaseDate(DateTimeUtils.convertStringToDate(measuringDeviceAccounts.getPurchaseDateString()));
        }
        if (StringUtils.isNotBlank(measuringDeviceAccounts.getEnableTimeString())) {
            measuringDeviceAccountsPageVo.setEnableTime(DateTimeUtils.convertStringToDate(measuringDeviceAccounts.getEnableTimeString()));
        }
        measuringDeviceAccountsPageVo.setPurchasePlace(measuringDeviceAccounts.getPurchasePlace());
        measuringDeviceAccountsPageVo.setApplication(measuringDeviceAccounts.getApplication());
        measuringDeviceAccountsPageVo.setServiceLife(measuringDeviceAccounts.getServiceLife() == null ? 0 : measuringDeviceAccounts.getServiceLife());
        measuringDeviceAccountsPageVo.setUseStateCode(StringUtils.isNotBlank(measuringDeviceAccounts.getUseStateCode()) ? MeasureDeviceUseStateCodeEnum.fromCode(measuringDeviceAccounts.getUseStateCode()).toCode() : "");
        measuringDeviceAccountsPageVo.setRemark(measuringDeviceAccounts.getRemark());
        return measuringDeviceAccountsPageVo;
    }

    @Override
    @Transactional
    public Long save(MeasuringDeviceAccountsSaveVo measuringDeviceAccountsSaveVo) throws ParseException {
        MeasuringDeviceAccounts measuringDeviceAccounts = new MeasuringDeviceAccounts();
        measuringDeviceAccounts.setShopId(measuringDeviceAccountsSaveVo.getShopId());
        measuringDeviceAccounts.setMeasuringDeviceNum(measuringDeviceAccountsSaveVo.getMeasuringDeviceNum());
        measuringDeviceAccounts.setManufacturingNum(measuringDeviceAccountsSaveVo.getManufacturingNum());
        measuringDeviceAccounts.setDeviceNm(measuringDeviceAccountsSaveVo.getDeviceNm());
        measuringDeviceAccounts.setModel(measuringDeviceAccountsSaveVo.getModel());
        measuringDeviceAccounts.setProduceManufacturer(measuringDeviceAccountsSaveVo.getProduceManufacturer());
        measuringDeviceAccounts.setCategoryNum(measuringDeviceAccountsSaveVo.getCategoryNum());
        measuringDeviceAccounts.setMeasureRange(measuringDeviceAccountsSaveVo.getMeasureRange());
        measuringDeviceAccounts.setPrecisionLevel(measuringDeviceAccountsSaveVo.getPrecisionLevel());
        measuringDeviceAccounts.setResponseMan(measuringDeviceAccountsSaveVo.getResponseMan());
        measuringDeviceAccounts.setMeasurePeriod(measuringDeviceAccountsSaveVo.getMeasurePeriod());
        //如果前台传过来的采购价格为空的话则给默认值0
        measuringDeviceAccounts.setPurchasePrice(measuringDeviceAccountsSaveVo.getPurchasePrice() == null ? 0D : measuringDeviceAccountsSaveVo.getPurchasePrice());
        if (StringUtils.isNotBlank(measuringDeviceAccountsSaveVo.getPurchaseDateString())) {
            measuringDeviceAccounts.setPurchaseDate(DateTimeUtils.convertStringToDate(measuringDeviceAccountsSaveVo.getPurchaseDateString()));
        }
        if (StringUtils.isNotBlank(measuringDeviceAccountsSaveVo.getEnableTimeString())) {
            measuringDeviceAccounts.setEnableTime(DateTimeUtils.convertStringToDate(measuringDeviceAccountsSaveVo.getEnableTimeString()));
        }
        measuringDeviceAccounts.setPurchasePlace(measuringDeviceAccountsSaveVo.getPurchasePlace());
        measuringDeviceAccounts.setApplication(measuringDeviceAccountsSaveVo.getApplication());
        //如果前台传过来的使用年限为空的话则给默认值0
        measuringDeviceAccounts.setServiceLife(measuringDeviceAccountsSaveVo.getServiceLife() == null ? 0 : measuringDeviceAccountsSaveVo.getServiceLife());
        measuringDeviceAccounts.setUseStateCode(StringUtils.isNotBlank(measuringDeviceAccountsSaveVo.getUseStateCode()) ? MeasureDeviceUseStateCodeEnum.fromCode(measuringDeviceAccountsSaveVo.getUseStateCode()).toCode() : "");
        measuringDeviceAccounts.setRemark(measuringDeviceAccountsSaveVo.getRemark());
        return save(measuringDeviceAccounts).getId();
    }

    @Override
    @Transactional
    public Long update(MeasuringDeviceAccountsUpdateVo measuringDeviceAccountsUpdateVo) throws ParseException {
        MeasuringDeviceAccounts measuringDeviceAccounts = this.findOne(measuringDeviceAccountsUpdateVo.getId());
        if (measuringDeviceAccounts == null || !measuringDeviceAccountsUpdateVo.getShopId().equals(measuringDeviceAccounts.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"计量器具"}));
        }
        measuringDeviceAccounts.setMeasuringDeviceNum(measuringDeviceAccountsUpdateVo.getMeasuringDeviceNum());
        measuringDeviceAccounts.setManufacturingNum(measuringDeviceAccountsUpdateVo.getManufacturingNum());
        measuringDeviceAccounts.setDeviceNm(measuringDeviceAccountsUpdateVo.getDeviceNm());
        measuringDeviceAccounts.setModel(measuringDeviceAccountsUpdateVo.getModel());
        measuringDeviceAccounts.setProduceManufacturer(measuringDeviceAccountsUpdateVo.getProduceManufacturer());
        measuringDeviceAccounts.setCategoryNum(measuringDeviceAccountsUpdateVo.getCategoryNum());
        measuringDeviceAccounts.setMeasureRange(measuringDeviceAccountsUpdateVo.getMeasureRange());
        measuringDeviceAccounts.setPrecisionLevel(measuringDeviceAccountsUpdateVo.getPrecisionLevel());
        measuringDeviceAccounts.setResponseMan(measuringDeviceAccountsUpdateVo.getResponseMan());
        measuringDeviceAccounts.setMeasurePeriod(measuringDeviceAccountsUpdateVo.getMeasurePeriod());
        measuringDeviceAccounts.setPurchasePrice(measuringDeviceAccountsUpdateVo.getPurchasePrice() == null ? 0D : measuringDeviceAccountsUpdateVo.getPurchasePrice());
        if (StringUtils.isNotBlank(measuringDeviceAccountsUpdateVo.getPurchaseDateString())) {
            measuringDeviceAccounts.setPurchaseDate(DateTimeUtils.convertStringToDate(measuringDeviceAccountsUpdateVo.getPurchaseDateString()));
        }
        if (StringUtils.isNotBlank(measuringDeviceAccountsUpdateVo.getEnableTimeString())) {
            measuringDeviceAccounts.setEnableTime(DateTimeUtils.convertStringToDate(measuringDeviceAccountsUpdateVo.getEnableTimeString()));
        }
        measuringDeviceAccounts.setPurchasePlace(measuringDeviceAccountsUpdateVo.getPurchasePlace());
        measuringDeviceAccounts.setApplication(measuringDeviceAccountsUpdateVo.getApplication());
        measuringDeviceAccounts.setServiceLife(measuringDeviceAccountsUpdateVo.getServiceLife() == null ? 0 : measuringDeviceAccountsUpdateVo.getServiceLife());
        measuringDeviceAccounts.setUseStateCode(StringUtils.isNotBlank(measuringDeviceAccountsUpdateVo.getUseStateCode()) ? MeasureDeviceUseStateCodeEnum.fromCode(measuringDeviceAccountsUpdateVo.getUseStateCode()).toCode() : "");
        measuringDeviceAccounts.setRemark(measuringDeviceAccountsUpdateVo.getRemark());
        return update(measuringDeviceAccounts).getId();
    }

    @Override
    @Transactional
    public Long measuringDeviceCheck(Long id, MeasuringDeviceAccountsCheckVo measuringDeviceAccountsCheckVo) throws ParseException {
        MeasureRecord measureRecord = new MeasureRecord();
        measureRecord.setShopId(measuringDeviceAccountsCheckVo.getShopId());
        measureRecord.setMeasuringDeviceAccountsId(id);
        measureRecord.setMeasureDate(DateTimeUtils.convertStringToDate(measuringDeviceAccountsCheckVo.getMeasureDateString()));
        measureRecord.setStartTime(DateTimeUtils.convertStringToDate(measuringDeviceAccountsCheckVo.getStartTimeString()));
        measureRecord.setEndTime(DateTimeUtils.convertStringToDate(measuringDeviceAccountsCheckVo.getEndTimeString()));
        measureRecord.setIdentifyPrj(measuringDeviceAccountsCheckVo.getIdentifyPrj());
        measureRecord.setSkillRequirement(measuringDeviceAccountsCheckVo.getSkillRequirement());
        measureRecord.setMeasureMethod(measuringDeviceAccountsCheckVo.getMeasureMethod());
        measureRecord.setIdentifyConclusion(measuringDeviceAccountsCheckVo.getIdentifyConclusion());
        measureRecord.setMeasureMan(measuringDeviceAccountsCheckVo.getMeasureMan());
        measureRecord.setReviewMan(measuringDeviceAccountsCheckVo.getReviewMan());
        measureRecord.setRemark(measuringDeviceAccountsCheckVo.getRemark());
        return ServiceManager.measureRecordService.save(measureRecord).getId();
    }

}