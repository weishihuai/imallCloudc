
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.vo.DrugCheckRecordPageVo;
import com.imall.iportal.core.shop.vo.DrugCheckRecordSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugCheckRecordRepositoryCustom{

    /**
     * 分页查询药品检查记录
     * @param pageable
     * @param drugCheckRecordSearchParam
     * @return
     */
    Page<DrugCheckRecordPageVo> query(Pageable pageable, DrugCheckRecordSearchParam drugCheckRecordSearchParam);

}

