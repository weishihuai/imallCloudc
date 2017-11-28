
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.StockCheck;
import com.imall.iportal.core.shop.vo.StockCheckSearchParam;
import com.imall.iportal.core.shop.vo.StockPostingSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface StockCheckRepositoryCustom {

    /**
     * 库存盘点列表
     *
     * @param pageable              分页对象
     * @param stockCheckSearchParam 搜索参数
     * @return
     */
    Page<StockCheck> query(Pageable pageable, StockCheckSearchParam stockCheckSearchParam);

    /**
     * 盘点过账列表
     *
     * @param pageable              分页对象
     * @param stockPostingSearchParam 搜索参数
     * @return
     */
    Page<StockCheck> queryStockPosting(Pageable pageable, StockPostingSearchParam stockPostingSearchParam);

}

