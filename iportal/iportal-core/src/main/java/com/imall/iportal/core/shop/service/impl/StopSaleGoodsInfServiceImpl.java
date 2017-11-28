package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.iportal.core.shop.entity.StopSaleGoodsInf;
import com.imall.iportal.core.shop.repository.StopSaleGoodsInfRepository;
import com.imall.iportal.core.shop.service.StopSaleGoodsInfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class StopSaleGoodsInfServiceImpl extends AbstractBaseService<StopSaleGoodsInf, Long> implements StopSaleGoodsInfService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private StopSaleGoodsInfRepository getStopSaleGoodsInfRepository() {
        return (StopSaleGoodsInfRepository) baseRepository;
    }

    @Override
    public List<StopSaleGoodsInf> findByDrugStopSaleNoticeId(Long id) {
        return getStopSaleGoodsInfRepository().findByDrugStopSaleNoticeId(id);
    }
}