
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.vo.DrugCuringPageVo;
import com.imall.iportal.core.shop.vo.DrugCuringSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugCuringRepositoryCustom{

    /**
     * 分页查询药品养护信息
     * @param pageable
     * @param drugCuringSearchParam
     * @return
     */
    Page<DrugCuringPageVo> query(Pageable pageable, DrugCuringSearchParam drugCuringSearchParam);


}

