package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.iportal.core.shop.entity.GoodsDrug;
import com.imall.iportal.core.shop.repository.GoodsDrugRepository;
import com.imall.iportal.core.shop.service.GoodsDrugService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsDrugServiceImpl extends AbstractBaseService<GoodsDrug, Long> implements GoodsDrugService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private GoodsDrugRepository getGoodsDrugRepository() {
        return (GoodsDrugRepository) baseRepository;
    }

    @Override
    public GoodsDrug findByGoodsId(Long goodsId) {

        return getGoodsDrugRepository().findByGoodsId(goodsId);
    }
}