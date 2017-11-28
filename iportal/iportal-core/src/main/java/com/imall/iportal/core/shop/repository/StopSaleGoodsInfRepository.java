
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.*;
import com.imall.commons.base.dao.IBaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface StopSaleGoodsInfRepository extends  IBaseRepository<StopSaleGoodsInf, Long>,StopSaleGoodsInfRepositoryCustom {

    /**
     * 通过停售单id 查找
     * @param id
     * @return
     */
    List<StopSaleGoodsInf> findByDrugStopSaleNoticeId(Long id);
}

