package com.imall.iportal.core.platform.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.iportal.core.platform.entity.GoodsDocOther;
import com.imall.iportal.core.platform.repository.GoodsDocOtherRepository;
import com.imall.iportal.core.platform.service.GoodsDocOtherService;
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
public class GoodsDocOtherServiceImpl extends AbstractBaseService<GoodsDocOther, Long> implements GoodsDocOtherService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private GoodsDocOtherRepository getGoodsDocOtherRepository() {
        return (GoodsDocOtherRepository) baseRepository;
    }

    @Override
    public GoodsDocOther findByGoodsDocId(Long id) {
        return getGoodsDocOtherRepository().findByGoodsDocId(id);
    }
}