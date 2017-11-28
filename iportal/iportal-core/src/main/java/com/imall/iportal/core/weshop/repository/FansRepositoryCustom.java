
package com.imall.iportal.core.weshop.repository;

import com.imall.iportal.core.weshop.entity.Fans;
import com.imall.iportal.core.weshop.vo.FansSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface FansRepositoryCustom {

    /**
     * 粉丝列表
     *
     * @param pageable        分页对象
     * @param fansSearchParam 搜索参数
     * @return
     */
    Page<Fans> query(Pageable pageable, FansSearchParam fansSearchParam);

}

