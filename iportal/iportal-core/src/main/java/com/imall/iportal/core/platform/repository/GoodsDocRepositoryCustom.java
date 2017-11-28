
package com.imall.iportal.core.platform.repository;

import com.imall.iportal.core.platform.entity.GoodsDoc;
import com.imall.iportal.core.platform.vo.GoodsDocListSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface GoodsDocRepositoryCustom {


    /**
     * 商品档案列表
     * @param pageable
     * @param goodsDocListSearchParam
     * @return
     */
    Page<GoodsDoc> query(Pageable pageable, GoodsDocListSearchParam goodsDocListSearchParam);
}

