
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.BadReactionRep;
import com.imall.iportal.core.shop.vo.BadReactionRegSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface BadReactionRepRepositoryCustom {

    /**
     * 不良反应报告列表
     *
     * @param pageable                  分页对象
     * @param badReactionRegSearchParam 查询参数
     * @return
     */
    Page<BadReactionRep> query(Pageable pageable, BadReactionRegSearchParam badReactionRegSearchParam);

}

