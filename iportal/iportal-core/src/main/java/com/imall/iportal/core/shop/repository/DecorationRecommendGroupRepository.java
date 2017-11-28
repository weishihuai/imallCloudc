
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
public interface DecorationRecommendGroupRepository extends  IBaseRepository<DecorationRecommendGroup, Long>,DecorationRecommendGroupRepositoryCustom {

    List<DecorationRecommendGroup> findByShopId(Long shopId);

    void deleteByIdAndShopId(Long id, Long shopId);

    DecorationRecommendGroup findByShopIdAndGroupNm(Long shopId, String groupNm);

    DecorationRecommendGroup findByIdAndShopId(Long id, Long shopId);
}

