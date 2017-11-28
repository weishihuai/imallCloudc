
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.*;
import com.imall.commons.base.dao.IBaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 微门店(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface WeShopRepository extends  IBaseRepository<WeShop, Long>,WeShopRepositoryCustom {

    //根据门店ID找到微门店
    WeShop findByShopId(Long shopId);

    WeShop findByIdAndShopId(Long id, Long shopId);

    @Query("select w, count(distinct w.shopZoneParent) from WeShop w group by w.shopZoneParent")
    List<Object> findDistinctShopZoneParent();

    List<WeShop> findByShopZoneParentName(String shopZoneParentName);
}

