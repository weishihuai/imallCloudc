
package com.imall.iportal.core.platform.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.platform.entity.GoodsDocOther;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface GoodsDocOtherRepository extends IBaseRepository<GoodsDocOther, Long>, GoodsDocOtherRepositoryCustom {


    GoodsDocOther findByGoodsDocId(Long id);
}

