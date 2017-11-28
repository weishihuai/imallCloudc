
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.Member;
import com.imall.iportal.core.shop.vo.MemberSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface MemberRepositoryCustom {

    /**
     * 会员列表
     *
     * @param pageable          分页对象
     * @param memberSearchParam 搜索参数
     * @return
     */
    Page<Member> query(Pageable pageable, MemberSearchParam memberSearchParam);

}

