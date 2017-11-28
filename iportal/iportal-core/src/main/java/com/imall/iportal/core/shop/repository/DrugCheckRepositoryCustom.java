
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.vo.DrugCheckPageVo;
import com.imall.iportal.core.shop.vo.DrugCheckSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugCheckRepositoryCustom{

    /**
     * 分页查询药品检查信息
     * @param pageable
     * @param drugCheckSearchParam
     * @return
     */
    Page<DrugCheckPageVo> query(Pageable pageable, DrugCheckSearchParam drugCheckSearchParam);

}

