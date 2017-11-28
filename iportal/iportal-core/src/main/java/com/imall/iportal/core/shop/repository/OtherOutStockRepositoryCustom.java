
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.OtherOutStock;
import com.imall.iportal.core.shop.vo.OtherOutStockSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface OtherOutStockRepositoryCustom {

    /**
     * 其他出库列表
     *
     * @param pageable                 分页对象
     * @param otherOutStockSearchParam 搜索参数
     * @return
     */
    Page<OtherOutStock> query(Pageable pageable, OtherOutStockSearchParam otherOutStockSearchParam);


}

