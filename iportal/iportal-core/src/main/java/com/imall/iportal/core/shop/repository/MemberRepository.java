
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.Member;
import org.springframework.data.jpa.repository.Query;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface MemberRepository extends IBaseRepository<Member, Long>, MemberRepositoryCustom {

    /**
     * 根据会员手机号或会员卡号查询会员信息
     *
     * @param searchText 搜索参数(会员手机号码、会员卡号)
     * @return
     */
    @Query("select m from Member m where (m.memberCardNum = ?1 or m.mobile = ?1) and m.shopId = ?2")
    Member findByMobileOrCardNum(String searchText, Long shopId);

    /**
     * 检查会员是否已存在
     *
     * @param mobile 会员手机号码
     * @return
     */
    @Query("SELECT m FROM Member m WHERE m.mobile = ?1 and m.shopId = ?2")
    Member findByMobileAndShopId(String mobile, Long shopId);

}

