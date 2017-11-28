package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.DestroyRecord;
import com.imall.iportal.core.shop.repository.DestroyRecordRepository;
import com.imall.iportal.core.shop.service.DestroyRecordService;
import com.imall.iportal.core.shop.vo.DestroyRecordPageVo;
import com.imall.iportal.core.shop.vo.DestroyRecordSearchParam;
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
public class DestroyRecordServiceImpl extends AbstractBaseService<DestroyRecord, Long> implements DestroyRecordService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private DestroyRecordRepository getDestroyRecordRepository() {
        return (DestroyRecordRepository) baseRepository;
    }

    @Override
    public Page<DestroyRecordPageVo> query(Pageable pageable, DestroyRecordSearchParam destroyRecordSearchParam) {
        Page<DestroyRecord> destroyRecordPage = getDestroyRecordRepository().query(pageable, destroyRecordSearchParam);
        List<DestroyRecordPageVo> destroyRecordPageVoList = new ArrayList<>();
        for (DestroyRecord destroyRecord : destroyRecordPage.getContent()) {
            destroyRecordPageVoList.add(buildDestroyRecordPageVo(destroyRecord));
        }
        return new PageImpl<>(destroyRecordPageVoList, pageable, destroyRecordPage.getTotalElements());
    }

    /**
     * 构建销毁记录 列表对象
     *
     * @param destroyRecord 销毁记录
     * @return
     */
    private DestroyRecordPageVo buildDestroyRecordPageVo(DestroyRecord destroyRecord) {
        DestroyRecordPageVo destroyRecordPageVo = CommonUtil.copyProperties(destroyRecord, new DestroyRecordPageVo());
        destroyRecordPageVo.setProduceDateString(destroyRecord.getProduceDateString());
        destroyRecordPageVo.setValidDateString(destroyRecord.getValidDateString());
        destroyRecordPageVo.setDestroyTimeString(destroyRecord.getDestroyTimeString());
        return destroyRecordPageVo;
    }

}