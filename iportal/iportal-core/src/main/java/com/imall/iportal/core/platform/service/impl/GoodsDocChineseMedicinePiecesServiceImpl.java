package com.imall.iportal.core.platform.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.iportal.core.platform.entity.GoodsDocChineseMedicinePieces;
import com.imall.iportal.core.platform.repository.GoodsDocChineseMedicinePiecesRepository;
import com.imall.iportal.core.platform.service.GoodsDocChineseMedicinePiecesService;
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
public class GoodsDocChineseMedicinePiecesServiceImpl extends AbstractBaseService<GoodsDocChineseMedicinePieces, Long> implements GoodsDocChineseMedicinePiecesService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private GoodsDocChineseMedicinePiecesRepository getGoodsDocChineseMedicinePiecesRepository() {
        return (GoodsDocChineseMedicinePiecesRepository) baseRepository;
    }

    @Override
    public GoodsDocChineseMedicinePieces findByGoodsDocId(Long id) {
        return getGoodsDocChineseMedicinePiecesRepository().findByGoodsDocId(id);
    }
}