
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.vo.DrugCombinationPageVo;
import com.imall.iportal.core.shop.vo.DrugCombinationSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugCombinationRepositoryCustom{

    /**
     * 分页查询联合用药
     * @param pageable
     * @param drugCombinationSearchParam
     * @return
     */
    Page<DrugCombinationPageVo> query(Pageable pageable, DrugCombinationSearchParam drugCombinationSearchParam);

}

