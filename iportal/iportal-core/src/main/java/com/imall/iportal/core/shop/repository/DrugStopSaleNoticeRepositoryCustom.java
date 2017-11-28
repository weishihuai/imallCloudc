
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.DrugStopSaleNotice;
import com.imall.iportal.core.shop.vo.DrugStopSaleNoticeSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugStopSaleNoticeRepositoryCustom{


    /**
     * 停售单列表
     * @param pageable
     * @param drugStopSaleNoticeSearchParam
     * @param shopId
     * @return
     */
    Page<DrugStopSaleNotice> query(Pageable pageable, DrugStopSaleNoticeSearchParam drugStopSaleNoticeSearchParam, Long shopId) throws ParseException;
}

