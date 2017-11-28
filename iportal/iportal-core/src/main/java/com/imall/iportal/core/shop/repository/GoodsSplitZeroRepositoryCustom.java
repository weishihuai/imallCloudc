
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.vo.GoodsSplitZeroPageVo;
import com.imall.iportal.core.shop.vo.GoodsSplitZeroSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface GoodsSplitZeroRepositoryCustom{

    /**
     * 分页查询药品拆零信息
     * @param pageable
     * @param goodsSplitZeroSearchParam
     * @return
     */
    Page<GoodsSplitZeroPageVo> query(Pageable pageable, GoodsSplitZeroSearchParam goodsSplitZeroSearchParam);

}

