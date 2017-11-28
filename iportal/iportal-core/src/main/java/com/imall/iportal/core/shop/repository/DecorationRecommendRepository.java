
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
public interface DecorationRecommendRepository extends  IBaseRepository<DecorationRecommend, Long>,DecorationRecommendRepositoryCustom {

    List<DecorationRecommend> findByDecorationGroupIdAndShopIdOrderByDisplayPositionDesc(Long decorationGroupId, Long shopId);

    Long countByDecorationGroupId(Long decorationGroupId);

    void deleteByIdAndShopId(Long id, Long shopId);

    List<DecorationRecommend> findByGoodsIdAndDecorationGroupId(Long goodsId, Long decorationGroupId);

    DecorationRecommend findByIdAndShopId(Long id, Long shopId);

    void deleteByDecorationGroupId(Long decorationGroupId);
}

