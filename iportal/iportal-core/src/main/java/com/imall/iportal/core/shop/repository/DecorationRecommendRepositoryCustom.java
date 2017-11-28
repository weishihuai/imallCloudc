
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.DecorationRecommend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DecorationRecommendRepositoryCustom{

    Page<DecorationRecommend> query(Pageable pageable, Long shopId, Long groupId);
}

