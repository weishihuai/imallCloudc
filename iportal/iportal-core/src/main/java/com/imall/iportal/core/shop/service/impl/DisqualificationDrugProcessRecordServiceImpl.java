package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.DisqualificationDrugProcessRecord;
import com.imall.iportal.core.shop.repository.DisqualificationDrugProcessRecordRepository;
import com.imall.iportal.core.shop.service.DisqualificationDrugProcessRecordService;
import com.imall.iportal.core.shop.vo.DisqualificationDrugProcessRecordPageVo;
import com.imall.iportal.core.shop.vo.DisqualificationDrugProcessRecordSearchParam;
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
public class DisqualificationDrugProcessRecordServiceImpl extends AbstractBaseService<DisqualificationDrugProcessRecord, Long> implements DisqualificationDrugProcessRecordService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private DisqualificationDrugProcessRecordRepository getDisqualificationDrugProcessRecordRepository() {
        return (DisqualificationDrugProcessRecordRepository) baseRepository;
    }

    @Override
    public Page<DisqualificationDrugProcessRecordPageVo> query(Pageable pageable, DisqualificationDrugProcessRecordSearchParam disqualificationDrugProcessRecordSearchParam) {
        Page<DisqualificationDrugProcessRecord> disqualificationDrugProcessRecordPage = getDisqualificationDrugProcessRecordRepository().query(pageable, disqualificationDrugProcessRecordSearchParam);
        List<DisqualificationDrugProcessRecordPageVo> disqualificationDrugProcessRecordPageVoList = new ArrayList<>();
        for (DisqualificationDrugProcessRecord disqualificationDrugProcessRecord : disqualificationDrugProcessRecordPage.getContent()) {
            disqualificationDrugProcessRecordPageVoList.add(buildDisqualificationDrugProcessRecordPageVo(disqualificationDrugProcessRecord));
        }
        return new PageImpl<>(disqualificationDrugProcessRecordPageVoList, pageable, disqualificationDrugProcessRecordPage.getTotalElements());
    }

    private DisqualificationDrugProcessRecordPageVo buildDisqualificationDrugProcessRecordPageVo(DisqualificationDrugProcessRecord disqualificationDrugProcessRecord) {
        DisqualificationDrugProcessRecordPageVo disqualificationDrugProcessRecordPageVo = CommonUtil.copyProperties(disqualificationDrugProcessRecord, new DisqualificationDrugProcessRecordPageVo());
        disqualificationDrugProcessRecordPageVo.setRecordDateString(disqualificationDrugProcessRecord.getRecordDateString());
        disqualificationDrugProcessRecordPageVo.setValidDateString(disqualificationDrugProcessRecord.getValidDateString());
        return disqualificationDrugProcessRecordPageVo;
    }


}