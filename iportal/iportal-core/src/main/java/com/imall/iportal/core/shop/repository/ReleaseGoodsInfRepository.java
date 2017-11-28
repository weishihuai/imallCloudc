
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
public interface ReleaseGoodsInfRepository extends  IBaseRepository<ReleaseGoodsInf, Long>,ReleaseGoodsInfRepositoryCustom {


    /**
     * 通过解停单id 查找解停单商品批次信息
     * @param id
     * @return
     */
    List<ReleaseGoodsInf> findByDrugReleaseNoticeId(Long id);
}

