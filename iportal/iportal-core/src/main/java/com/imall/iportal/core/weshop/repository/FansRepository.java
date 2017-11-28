
package com.imall.iportal.core.weshop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.weshop.entity.Fans;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface FansRepository extends IBaseRepository<Fans, Long>, FansRepositoryCustom {

    List<Fans> findByOpenIdOrderByCreateDateAsc(String openId);

    /**
     * 统计当前登录门店所有的粉丝
     *
     * @param shopId 门店ID
     * @return
     */
    @Query("select f from Fans f where f.shopId =?1")
    List<Fans> listFansByShopId(Long shopId);

    Fans findByOpenIdAndShopId(String openId, Long shopId);

    /**
     * 根据手机号码以及是否会员查询粉丝
     *
     * @param mobile 手机号码
     * @return
     */
    @Query("select f from Fans f where f.mobile =?1 and f.isMember =?2")
    List<Fans> findByMobileAndIsMember(String mobile, String isMember);

    List<Fans> findByWeChatUserId(Long weChatUserId);

    Fans findByWeChatUserIdAndShopId(Long weChatUserId, Long shopId);
}

