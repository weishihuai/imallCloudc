
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.*;
import com.imall.commons.base.dao.IBaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 收货 地址(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ReceiverAddrRepository extends  IBaseRepository<ReceiverAddr, Long>,ReceiverAddrRepositoryCustom {

    List<ReceiverAddr> findByWeChatUserId(Long weChatUserId);

    @Query("select r from ReceiverAddr r where r.weChatUserId = ?1 and r.isDefault = 'Y'")
    ReceiverAddr findByWeChatUserIdAndIsDefault(Long weChatUserId);

    void deleteByIdAndWeChatUserId(Long id, Long weChatUserId);

    ReceiverAddr findByIdAndWeChatUserId(Long id, Long weChatUserId);
}

