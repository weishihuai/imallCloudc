package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.MeasureDeviceUseStateCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.MeasureRecord;
import com.imall.iportal.core.shop.entity.MeasuringDeviceAccounts;
import com.imall.iportal.core.shop.repository.MeasureRecordRepository;
import com.imall.iportal.core.shop.service.MeasureRecordService;
import com.imall.iportal.core.shop.vo.MeasureRecordDetailVo;
import com.imall.iportal.core.shop.vo.MeasureRecordPageVo;
import com.imall.iportal.core.shop.vo.MeasureRecordSearchParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
public class MeasureRecordServiceImpl extends AbstractBaseService<MeasureRecord, Long> implements MeasureRecordService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private MeasureRecordRepository getMeasureRecordRepository() {
        return (MeasureRecordRepository) baseRepository;
    }

    @Override
    public Page<MeasureRecordPageVo> query(Pageable pageable, MeasureRecordSearchParam measureRecordSearchParam) {
        Page<MeasureRecord> measureRecordPage = getMeasureRecordRepository().query(pageable, measureRecordSearchParam);
        List<MeasureRecordPageVo> measureRecordPageVoList = new ArrayList<>();
        for (MeasureRecord measureRecord : measureRecordPage.getContent()) {
            measureRecordPageVoList.add(buildMeasureRecordPageVo(measureRecord));
        }
        return new PageImpl<>(measureRecordPageVoList, pageable, measureRecordPage.getTotalElements());
    }

    private MeasureRecordPageVo buildMeasureRecordPageVo(MeasureRecord measureRecord) {
        MeasureRecordPageVo measureRecordPageVo = CommonUtil.copyProperties(measureRecord, new MeasureRecordPageVo());
        measureRecordPageVo.setMeasureDateString(measureRecord.getMeasureDateString());
        MeasuringDeviceAccounts measuringDeviceAccounts = ServiceManager.measuringDeviceAccountsService.findOne(measureRecord.getMeasuringDeviceAccountsId());
        measureRecordPageVo.setMeasuringDeviceNum(measuringDeviceAccounts.getMeasuringDeviceNum());
        measureRecordPageVo.setManufacturingNum(measuringDeviceAccounts.getManufacturingNum());
        measureRecordPageVo.setDeviceNm(measuringDeviceAccounts.getDeviceNm());
        measureRecordPageVo.setModel(measuringDeviceAccounts.getModel());
        measureRecordPageVo.setProduceManufacturer(measuringDeviceAccounts.getProduceManufacturer());
        measureRecordPageVo.setCategoryNum(measuringDeviceAccounts.getCategoryNum());
        measureRecordPageVo.setMeasureRange(measuringDeviceAccounts.getMeasureRange());
        measureRecordPageVo.setPrecisionLevel(measuringDeviceAccounts.getPrecisionLevel());
        measureRecordPageVo.setResponseMan(measuringDeviceAccounts.getResponseMan());
        measureRecordPageVo.setMeasurePeriod(measuringDeviceAccounts.getMeasurePeriod());
        measureRecordPageVo.setUseStateCode(StringUtils.isNotBlank(measuringDeviceAccounts.getUseStateCode()) ? MeasureDeviceUseStateCodeEnum.fromCode(measuringDeviceAccounts.getUseStateCode()).toCode() : "");
        return measureRecordPageVo;
    }

    @Override
    public MeasureRecordDetailVo findById(Long shopId, Long id) {
        MeasureRecord measureRecord = this.findOne(id);
        if (measureRecord == null || !measureRecord.getShopId().equals(shopId)) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        return buildMeasureRecordDetailVo(measureRecord);
    }

    /**
     * 构建检测记录 详情对象
     *
     * @param measureRecord 检测记录对象
     * @return
     */
    private MeasureRecordDetailVo buildMeasureRecordDetailVo(MeasureRecord measureRecord) {
        MeasureRecordDetailVo measureRecordDetailVo = CommonUtil.copyProperties(measureRecord, new MeasureRecordDetailVo());
        MeasuringDeviceAccounts measuringDeviceAccounts = ServiceManager.measuringDeviceAccountsService.findOne(measureRecord.getMeasuringDeviceAccountsId());
        measureRecordDetailVo.setMeasuringDeviceNum(measuringDeviceAccounts.getMeasuringDeviceNum());
        measureRecordDetailVo.setManufacturingNum(measuringDeviceAccounts.getManufacturingNum());
        measureRecordDetailVo.setDeviceNm(measuringDeviceAccounts.getDeviceNm());
        measureRecordDetailVo.setModel(measuringDeviceAccounts.getModel());
        measureRecordDetailVo.setProduceManufacturer(measuringDeviceAccounts.getProduceManufacturer());
        measureRecordDetailVo.setCategoryNum(measuringDeviceAccounts.getCategoryNum());
        measureRecordDetailVo.setMeasureRange(measuringDeviceAccounts.getMeasureRange());
        measureRecordDetailVo.setPrecisionLevel(measuringDeviceAccounts.getPrecisionLevel());
        measureRecordDetailVo.setResponseMan(measuringDeviceAccounts.getResponseMan());
        measureRecordDetailVo.setMeasurePeriod(measuringDeviceAccounts.getMeasurePeriod());
        measureRecordDetailVo.setMeasureDateString(measureRecord.getMeasureDateString());
        measureRecordDetailVo.setStartTimeString(measureRecord.getStartTimeString());
        measureRecordDetailVo.setEndTimeString(measureRecord.getEndTimeString());
        return measureRecordDetailVo;
    }

}