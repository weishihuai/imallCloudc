
package com.imall.iportal.core.platform.repository;

import com.imall.iportal.core.platform.entity.Shop;
import com.imall.iportal.core.platform.vo.ShopSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ShopRepositoryCustom{

    Page<Shop> query(Pageable pageable, ShopSearchParam shopSearchParam) throws ParseException;
}

