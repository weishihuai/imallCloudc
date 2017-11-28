
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.OtherInStock;
import com.imall.iportal.core.shop.vo.OtherInStockSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface OtherInStockRepositoryCustom {

    /**
     * 其他入库列表
     *
     * @param pageable                分页对象
     * @param otherInStockSearchParam 搜索参数
     * @return
     */
    Page<OtherInStock> query(Pageable pageable, OtherInStockSearchParam otherInStockSearchParam);

}

