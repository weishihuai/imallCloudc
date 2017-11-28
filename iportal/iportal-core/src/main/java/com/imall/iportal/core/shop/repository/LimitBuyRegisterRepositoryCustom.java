
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.LimitBuyRegister;
import com.imall.iportal.core.shop.vo.LimitBuyRegisterSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface LimitBuyRegisterRepositoryCustom {

    /**
     * 分页查询限购登记信息
     *
     * @param pageable                    分页对象
     * @param limitBuyRegisterSearchParam 搜索参数
     * @return
     */
    Page<LimitBuyRegister> query(Pageable pageable, LimitBuyRegisterSearchParam limitBuyRegisterSearchParam);

}

