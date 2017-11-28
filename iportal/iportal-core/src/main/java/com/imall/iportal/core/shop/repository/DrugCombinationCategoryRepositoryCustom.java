
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.vo.DrugCombinationCategoryPageVo;
import com.imall.iportal.core.shop.vo.DrugCombinationCategorySearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugCombinationCategoryRepositoryCustom{

    /**
     * 分页查询联合用药分类
     * @param pageable
     * @param drugCombinationCategorySearchParam
     * @return
     */
    Page<DrugCombinationCategoryPageVo> query(Pageable pageable, DrugCombinationCategorySearchParam drugCombinationCategorySearchParam);

}

