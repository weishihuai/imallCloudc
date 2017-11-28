
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.SalesCategory;
import com.imall.iportal.core.shop.vo.SalesCategoryPageVo;
import com.imall.iportal.core.shop.vo.SalesCategorySearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 销售 分类(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SalesCategoryRepositoryCustom{

    /**
     * 分页查询销售分类
     * @param pageable
     * @param salesCategorySearchParam
     * @return
     */
    Page<SalesCategoryPageVo> query(Pageable pageable, SalesCategorySearchParam salesCategorySearchParam);

    List<SalesCategory> listSalesCategory(Long shopId, String isEnable, String isFrontendShow);
}

