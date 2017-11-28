
package com.imall.iportal.core.platform.repository;

import com.imall.iportal.core.platform.entity.*;
import com.imall.commons.base.dao.IBaseRepository;
import org.springframework.data.jpa.repository.Query;
/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface GoodsDocChineseMedicinePiecesRepository extends  IBaseRepository<GoodsDocChineseMedicinePieces, Long>,GoodsDocChineseMedicinePiecesRepositoryCustom {


    /**
     * 通过商品档案ID查找
     * @param id
     * @return
     */
    GoodsDocChineseMedicinePieces findByGoodsDocId(Long id);
}

